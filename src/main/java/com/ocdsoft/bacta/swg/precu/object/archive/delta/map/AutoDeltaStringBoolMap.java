package com.ocdsoft.bacta.swg.precu.object.archive.delta.map;

import com.ocdsoft.bacta.engine.buffer.ByteBufferWritable;
import com.ocdsoft.bacta.engine.utils.BufferUtil;
import com.ocdsoft.bacta.swg.precu.object.archive.delta.AutoDeltaContainer;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.map.hash.TObjectByteHashMap;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AutoDeltaStringBoolMap extends AutoDeltaContainer {
    private transient final List<Command> changes;
    private final TObjectByteMap<String> container;
    private transient int baselineCommandCount;

    public AutoDeltaStringBoolMap() {
        this.changes = new ArrayList<>(5);
        this.container = new TObjectByteHashMap<String>();
        this.baselineCommandCount = 0;
    }

    public void clear() {
        container.forEachEntry((key, value) -> {
            erase(key);
            return true;
        });
    }

    @Override
    public void clearDelta() {
        changes.clear();
    }

    public void erase(final String key) {
        final boolean value = container.get(key) != 0;


        final Command command = new Command(Command.ERASE, key, value);
        changes.add(command);
        ++baselineCommandCount;
        container.remove(key);
        touch();
        onErase(key, value);
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public boolean containsKey(final String key) {
        return container.containsKey(key);
    }

    public TObjectByteMap<String> getMap() {
        return container;
    }

    public void insert(final String key, final boolean value) {
        if (containsKey(key))
            return;

        final Command command = new Command(Command.ADD, key, value);
        container.put(key, value ? (byte) 1 : (byte) 0);
        touch();
        onInsert(key, value);
        changes.add(command);
        ++baselineCommandCount;
    }

    @Override
    public boolean isDirty() {
        return !changes.isEmpty();
    }

    public int size() {
        return container.size();
    }

    public void set(final String key, final boolean value) {
        if (!containsKey(key)) {
            //Inserting...
            final Command command = new Command(Command.ADD, key, value);
            container.put(key, value ? (byte) 1 : (byte) 0);
            touch();
            onInsert(key, value);
            changes.add(command);
            ++baselineCommandCount;
        } else {
            //Setting...
            final Command command = new Command(Command.SET, key, value);
            final boolean oldValue = container.get(key) != 0;
            container.put(key, value ? (byte) 1 : (byte) 0);
            touch();
            onSet(key, oldValue, value);
            changes.add(command);
            ++baselineCommandCount;
        }
    }

    @Override
    public void pack(final ByteBuffer buffer) {
        buffer.putInt(container.size());
        buffer.putInt(baselineCommandCount);

        container.forEachEntry((key, value) -> {
            buffer.put(Command.ADD);
            BufferUtil.put(buffer, key);
            BufferUtil.put(buffer, value);
            return true;
        });
    }

    @Override
    public void packDelta(final ByteBuffer buffer) {
        buffer.putInt(changes.size());
        buffer.putInt(baselineCommandCount);

        changes.stream().forEach(command -> {
            command.writeToBuffer(buffer);
        });

        clearDelta();
    }

    @Override
    public void unpack(final ByteBuffer buffer) {
        container.clear();
        clearDelta();

        final int commandCount = buffer.getInt();
        baselineCommandCount = buffer.getInt();

        for (int i = 0; i < commandCount; ++i) {
            final Command command = new Command(buffer);
            assert command.cmd == Command.ADD : "Only add is valid in unpack";
            container.put(command.key, command.value ? (byte) 1 : (byte) 0);
            onInsert(command.key, command.value);
        }
    }

    @Override
    public void unpackDelta(final ByteBuffer buffer) {
        int skipCount;

        final int commandCount = buffer.getInt();
        final int targetBaselineCommandCount = buffer.getInt();

        //if (commandCount + baselineCommandCount) < targetBaselineCommandCount, it
        //means that we have missed some changes and are behind; when this happens,
        //catch up by applying all the deltas that came in, and set
        //baselineCommandCont to targetBaselineCommandCount

        if ((commandCount + baselineCommandCount) > targetBaselineCommandCount)
            skipCount = commandCount + baselineCommandCount - targetBaselineCommandCount;
        else
            skipCount = 0;

        //If this fails, it means that the deltas we are receiving are relative to baselines
        //which are newer than what we currently have. This usually means either we were not
        //observing an object for a time when deltas were sent, but aren't getting new
        //baselines, or our version of the container has been modified locally.
        if (skipCount > commandCount)
            skipCount = commandCount;

        int i;
        for (i = 0; i < skipCount; ++i) {
            final byte cmd = buffer.get();
            final String key = BufferUtil.getAscii(buffer);
            final boolean value = BufferUtil.getBoolean(buffer);
        }

        for (; i < commandCount; ++i) {
            final Command command = new Command(buffer);

            switch (command.cmd) {
                case Command.ADD:
                case Command.SET:
                    set(command.key, command.value);
                    break;
                case Command.ERASE:
                    erase(command.key);
                    break;
                default:
                    assert false : "Unknown command";
                    break;
            }
        }

        //If we are behind, catch up.
        if (baselineCommandCount < targetBaselineCommandCount)
            baselineCommandCount = targetBaselineCommandCount;
    }

    private void onErase(final String key, final boolean value) {
        //callback
    }

    private void onInsert(final String key, final boolean value) {
        //callback
    }

    private void onSet(final String key, final boolean oldValue, final boolean newValue) {
        //callback
    }

    public final class Command implements ByteBufferWritable {
        private static final byte ADD = 0x0;
        private static final byte ERASE = 0x1;
        private static final byte SET = 0x2;

        public final byte cmd;
        public final String key;
        public final boolean value;

        public Command(int cmd, String key, boolean value) {
            this.cmd = (byte) cmd;
            this.key = key;
            this.value = value;
        }

        public Command(final ByteBuffer buffer) {
            this.cmd = buffer.get();
            this.key = BufferUtil.getAscii(buffer);
            this.value = BufferUtil.getBoolean(buffer);
        }

        public void writeToBuffer(final ByteBuffer buffer) {
            buffer.put(this.cmd);
            BufferUtil.put(buffer, key);
            BufferUtil.put(buffer, value);
        }
    }
}

package com.ocdsoft.bacta.swg.precu.object.archive.delta;


import com.ocdsoft.bacta.swg.network.soe.buffer.SoeByteBuf;
import com.ocdsoft.bacta.swg.network.swg.util.ByteAppender;
import com.ocdsoft.bacta.swg.shared.lang.NotImplementedException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by crush on 8/14/2014.
 */
public class AutoDeltaSet<T> extends AutoDeltaContainer {
    private transient final List<Command> commands = new ArrayList<Command>(5);
    private final Set<T> set;
    private transient int baselineCommandCount = 0;

    public AutoDeltaSet(AutoDeltaByteStream owner) {
        super(owner);

        this.set = new TreeSet<>();
    }

    @Override
    public void clearDelta() {
        commands.clear();
    }

    public int size() {
        return set.size();
    }

    public Set<T> get() {
        return set;
    }

    public boolean contains(final T object) {
        return set.contains(object);
    }

    public void insert(T object) {
        set.add(object);
        commands.add(new Command(Command.Insert, object));
        ++baselineCommandCount;

        if (owner != null)
            owner.addToDirtyList(this);

    }

    public void erase(T object) {
        set.remove(object);
        commands.add(new Command(Command.Erase, object));
        ++baselineCommandCount;

        if (owner != null)
            owner.addToDirtyList(this);
    }

    public void clear() {
        set.clear();
        commands.add(new Command(Command.Clear, null));
        ++baselineCommandCount;

        if (owner != null)
            owner.addToDirtyList(this);
    }

    public boolean empty() {
        return set.size() == 0;
    }

    public boolean isDirty() {
        return commands.size() > 0;
    }

    @Override
    public void packDelta(SoeByteBuf buffer) {
        try {
            buffer.writeInt(commands.size());
            buffer.writeInt(baselineCommandCount);

            for (Command command : commands) {
                buffer.writeByte(command.cmd);

                if (command.cmd <= 1)
                    ByteAppender.append(command.value, buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unpackDelta(SoeByteBuf buffer) {
        throw new NotImplementedException();
    }

    @Override
    public void pack(SoeByteBuf buffer) {
        try {
            buffer.writeInt(set.size());
            buffer.writeInt(baselineCommandCount);

            for (T obj : set) {
                ByteAppender.append(obj, buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unpack(SoeByteBuf buffer) {
        throw new NotImplementedException();
    }

    private final class Command {
        private static final int Erase = 0x00;
        private static final int Insert = 0x01;
        private static final int Clear = 0x02;

        @Getter private final byte cmd;
        @Getter private final T value;

        public Command(int cmd, T value) {
            this.cmd = (byte) cmd;
            this.value = value;
        }
    }
}

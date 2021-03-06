package com.ocdsoft.bacta.swg.archive.delta;


import com.ocdsoft.bacta.engine.buffer.ByteBufferWritable;

import java.nio.ByteBuffer;
import java.util.function.Function;

public class AutoDeltaVariable<T extends ByteBufferWritable> extends AutoDeltaVariableBase {
    private T currentValue;
    private transient int lastValue;

    private final Function<ByteBuffer, T> createFunc;

    public AutoDeltaVariable(final Function<ByteBuffer, T> createFunc) {
        this.createFunc = createFunc;
    }

    public AutoDeltaVariable(final T value, final Function<ByteBuffer, T> createFunc) {
        super();

        this.createFunc = createFunc;
        this.currentValue = value;
        this.lastValue = value.hashCode();
    }

    public T get() {
        return this.currentValue;
    }

    public void set(T value) {
        if (this.currentValue != value) {
            this.currentValue = value;
            touch();
        }
    }

    @Override
    public void clearDelta() {
        this.lastValue = this.currentValue.hashCode();
    }

    public boolean isDirty() {
        return this.lastValue != this.currentValue.hashCode();
    }

    @Override
    public void packDelta(ByteBuffer buffer) {
        pack(buffer);
        clearDelta();
    }

    @Override
    public void unpackDelta(ByteBuffer buffer) {
        this.currentValue = createFunc.apply(buffer);
        touch();
    }
    @Override
    public void pack(ByteBuffer buffer) {
        currentValue.writeToBuffer(buffer);
    }

    @Override
    public void unpack(ByteBuffer buffer) {
        this.currentValue = createFunc.apply(buffer);
        clearDelta();
    }
}

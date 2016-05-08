package com.ocdsoft.bacta.swg.precu.object.archive.delta;

import com.ocdsoft.bacta.swg.precu.object.archive.AutoVariableBase;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * Created by crush on 8/14/2014.
 */
public abstract class AutoDeltaVariableBase implements AutoVariableBase {
    @Getter
    @Setter
    private AutoDeltaByteStream owner;
    @Getter
    @Setter
    private short index;

    public abstract void clearDelta();
    public abstract boolean isDirty();
    public abstract void packDelta(ByteBuffer buffer);
    public abstract void unpackDelta(ByteBuffer buffer);

    public final void touch() {
        if (owner != null)
            owner.addToDirtyList(this);
    }
}

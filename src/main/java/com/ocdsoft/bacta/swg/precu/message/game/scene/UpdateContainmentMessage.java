package com.ocdsoft.bacta.swg.precu.message.game.scene;

import com.ocdsoft.bacta.soe.message.GameNetworkMessage;
import com.ocdsoft.bacta.soe.message.Priority;
import com.ocdsoft.bacta.soe.util.SOECRC32;
import com.ocdsoft.bacta.swg.precu.object.ServerObject;

import java.nio.ByteBuffer;

@Priority(0x4)
public final class UpdateContainmentMessage extends GameNetworkMessage {

    private final long objectId;
    private final long containerId;
    private final int slotArrangement;

    public UpdateContainmentMessage(final ServerObject object) {
        this.objectId = object.getNetworkId();
        this.containerId = object.getContainedBy();
        this.slotArrangement = object.getCurrentArrangement();
    }

    public UpdateContainmentMessage(final ByteBuffer buffer) {
        this.objectId = buffer.getLong();
        this.containerId = buffer.getLong();
        this.slotArrangement = buffer.getInt();
    }

    @Override
    public void writeToBuffer(final ByteBuffer buffer) {
        buffer.putLong(objectId);
        buffer.putLong(containerId);
        buffer.putInt(slotArrangement);
    }
}

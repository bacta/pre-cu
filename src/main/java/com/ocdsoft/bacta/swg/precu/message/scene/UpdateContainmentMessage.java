package com.ocdsoft.bacta.swg.precu.message.scene;

import com.ocdsoft.bacta.swg.network.swg.message.SwgMessage;
import com.ocdsoft.bacta.swg.server.game.object.SceneObject;

public class UpdateContainmentMessage extends SwgMessage {
    public UpdateContainmentMessage(SceneObject object) {
        super(0x04, 0x56CBDE9E);

        writeLong(object.getNetworkId());
        writeLong(object.getContainedBy());
        writeLong(object.getCurrentArrangement());
    }

    public UpdateContainmentMessage(SceneObject scno, long parentId, int slotArrangement) {
        super(0x04, 0x56CBDE9E);

        //NetworkId networkId
        //NetworkId containerId
        //int slotArrangement

        writeLong(scno.getNetworkId());  // ObjectID
        writeLong(parentId);
        writeInt(slotArrangement);

    }

    public UpdateContainmentMessage(SceneObject object, SceneObject container, int slotArrangement) {
        super(0x04, 0x56CBDE9E);

        writeLong(object.getNetworkId());
        writeLong(container != null ? container.getNetworkId() : 0);
        writeInt(slotArrangement);
    }
}

package com.ocdsoft.bacta.swg.server.message.game.scene;

import com.ocdsoft.bacta.soe.message.GameNetworkMessage;
import com.ocdsoft.bacta.soe.message.Priority;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaByteStream;
import com.ocdsoft.bacta.swg.server.object.ServerObject;

import java.nio.ByteBuffer;

@Priority(0x5)
public final class BaselinesMessage extends GameNetworkMessage {
    public static final byte BASELINES_CLIENT_ONLY = 0;
    public static final byte BASELINES_CLIENT_SERVER = 1;
    public static final byte BASELINES_SERVER = 2;
    public static final byte BASELINES_SHARED = 3;
    public static final byte BASELINES_CLIENT_SERVER_NP = 4;
    public static final byte BASELINES_SERVER_NP = 5;
    public static final byte BASELINES_SHARED_NP = 6;
    public static final byte BASELINES_UI = 7;
    public static final byte BASELINES_FIRST_PARENT_CLIENT_SERVER = 8;
    public static final byte BASELINES_FIRST_PARENT_CLIENT_SERVER_NP = 9;

    private final long target;
    private final int typeId;
    private final ByteBuffer packageBuffer;
    private final byte packageId;

    public BaselinesMessage(final ServerObject object, final AutoDeltaByteStream sourcePackage, final int packageId) {
        this.target = object.getNetworkId();
        this.typeId = object.getObjectType();
        this.packageId = (byte) packageId;

        this.packageBuffer = ByteBuffer.allocate(4096);
        sourcePackage.pack(this.packageBuffer);
    }

    // TODO: Constructor Deserialization
    public BaselinesMessage(final ByteBuffer buffer) {
        this.target = buffer.getLong();
        this.typeId = buffer.getInt();
        this.packageId = buffer.get();

        final int size = buffer.getInt();

        this.packageBuffer = ByteBuffer.allocate(size);
        this.packageBuffer.put(buffer.array(), buffer.position(), size);
    }

    @Override
    public void writeToBuffer(final ByteBuffer buffer) {
        buffer.putLong(target);
        buffer.putInt(typeId);
        buffer.put(packageId);

        buffer.putInt(packageBuffer.position());
        buffer.put(packageBuffer.array(), 0, packageBuffer.position());
    }

    /*public void finish() {
        setInt(19, writerIndex() - 23);
    }*/
}

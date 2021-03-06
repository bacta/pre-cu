package com.ocdsoft.bacta.swg.server.object.waypoint;

import com.ocdsoft.bacta.engine.buffer.ByteBufferWritable;
import com.ocdsoft.bacta.engine.object.NetworkObject;
import com.ocdsoft.bacta.engine.utils.BufferUtil;
import com.ocdsoft.bacta.swg.shared.utility.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * Created by crush on 8/13/2014.
 */
public class Waypoint implements ByteBufferWritable {
    private final static Logger LOGGER = LoggerFactory.getLogger(Waypoint.class);

    private WaypointData data;

    public Waypoint() {
        data = WaypointData.getInvalidWaypoint();
    }

    public Waypoint(final long id) {
        if (id == NetworkObject.INVALID) {
            data = WaypointData.getInvalidWaypoint();
        } else {
            data = new WaypointData(id);
        }
    }

    public Waypoint(final ByteBuffer buffer) {
        final int appearanceNameCrc = buffer.getInt();
        final Location location = new Location(buffer);
        final String name = BufferUtil.getUnicode(buffer);
        final long networkId = buffer.getLong();
        final byte color = buffer.get();
        final boolean active = BufferUtil.getBoolean(buffer);

        if (networkId != NetworkObject.INVALID) {
            final WaypointData data = new WaypointData(networkId);
            this.setAppearanceNameCrc(appearanceNameCrc);
            this.setLocation(location);
            this.setName(name);
            this.setColor(color);
            this.setActive(active);

            this.data = data;
        } else {
            LOGGER.warn("Unpacking an invalid waypoint.");
        }
    }

    public int getAppearanceNameCrc() {
        return data.appearanceNameCrc;
    }

    public Location getLocation() {
        return data.location;
    }

    public String getName() {
        return data.name;
    }

    public long getNetworkId() {
        return data.networkId;
    }

    public byte getColor() {
        return data.color;
    }

    public boolean isActive() {
        return data.active;
    }

    public boolean isValid() {
        return data.networkId != NetworkObject.INVALID;
    }

    public boolean isVisible() {
        return data.color != WaypointType.INVISIBLE;
    }

    public WaypointDataBase getWaypointDataBase() {
        return data;
    }

    public void set(final WaypointDataBase waypointDataBase) {
        assert isValid() : "Attempted to chagne data on an invalid waypoint";

        data.appearanceNameCrc = waypointDataBase.appearanceNameCrc;
        data.location = waypointDataBase.location;
        data.setName(waypointDataBase.name);
        data.color = (byte) Math.min(waypointDataBase.color, WaypointType.NUM_COLORS - 1);
        data.active = waypointDataBase.active;

        //change notification!?
    }

    public void setAppearanceNameCrc(final int appearanceNameCrc) {
        data.appearanceNameCrc = appearanceNameCrc;
        //change notification.
    }

    public void setLocation(final Location location) {
        data.location = location;
        //change notification
    }

    public void setName(final String name) {
        data.setName(name);
        //change notification
    }

    public void setColor(final byte color) {
        data.color = (byte) Math.min(color, WaypointType.NUM_COLORS - 1);
        //change notification
    }

    public void setActive(final boolean active) {
        data.active = active;
        //change notification
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        data.writeToBuffer(buffer);
    }
}

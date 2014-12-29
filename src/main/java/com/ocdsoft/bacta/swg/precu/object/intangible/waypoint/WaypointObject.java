package com.ocdsoft.bacta.swg.precu.object.intangible.waypoint;

import com.ocdsoft.bacta.swg.network.soe.lang.UnicodeString;
import com.ocdsoft.bacta.swg.server.game.object.Vector;
import com.ocdsoft.bacta.swg.server.game.object.archive.delta.AutoDeltaBoolean;
import com.ocdsoft.bacta.swg.server.game.object.archive.delta.AutoDeltaLong;
import com.ocdsoft.bacta.swg.server.game.object.archive.delta.AutoDeltaString;
import com.ocdsoft.bacta.swg.server.game.object.archive.delta.AutoDeltaVariable;
import com.ocdsoft.bacta.swg.server.game.object.intangible.IntangibleObject;


public final class WaypointObject extends IntangibleObject {
    @Override
    public int getOpcode() {
        return 0x57415950;
    } //'WAYP'

    //WaypointObjectMessage03
    private final AutoDeltaVariable<Vector> location = new AutoDeltaVariable<>(Vector.zero, sharedPackage);
    private final AutoDeltaBoolean waypointActive = new AutoDeltaBoolean(false, sharedPackage);
    private final AutoDeltaLong cell = new AutoDeltaLong(0L, sharedPackage);
    private final AutoDeltaString planetName = new AutoDeltaString("", sharedPackage);
    private final AutoDeltaVariable<UnicodeString> waypointName = new AutoDeltaVariable<>(UnicodeString.empty, sharedPackage);
    private final AutoDeltaBoolean waypointVisible = new AutoDeltaBoolean(true, sharedPackage);
    private final AutoDeltaString waypointColor = new AutoDeltaString("blue", sharedPackage);
}

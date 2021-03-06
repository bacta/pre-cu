package com.ocdsoft.bacta.swg.server.object.intangible.mission;

import com.google.inject.Inject;
import com.ocdsoft.bacta.engine.lang.UnicodeString;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaInt;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaString;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaVariable;
import com.ocdsoft.bacta.swg.server.object.intangible.IntangibleObject;
import com.ocdsoft.bacta.swg.server.object.template.server.ServerObjectTemplate;
import com.ocdsoft.bacta.swg.server.object.waypoint.Waypoint;
import com.ocdsoft.bacta.swg.shared.container.SlotIdManager;
import com.ocdsoft.bacta.swg.shared.localization.StringId;
import com.ocdsoft.bacta.swg.shared.math.Vector;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplateList;
import com.ocdsoft.bacta.swg.shared.utility.Location;

/**
 * Created by crush on 9/4/2014.
 */
public class MissionObject extends IntangibleObject {
    private final AutoDeltaVariable<StringId> description;
    private final AutoDeltaInt difficulty;
    private final AutoDeltaVariable<Location> endLocation;
    private final AutoDeltaVariable<Vector> location;
    private final AutoDeltaVariable<UnicodeString> missionCreator;
    private final AutoDeltaInt missionType;
    private final AutoDeltaInt reward;
    private final AutoDeltaVariable<Vector> startLocation;
    private final AutoDeltaInt targetAppearance;
    private final AutoDeltaVariable<StringId> title;
    private final AutoDeltaInt status;
    private final AutoDeltaString targetName;
    private final AutoDeltaVariable<Waypoint> waypoint;

    @Inject
    public MissionObject(final ObjectTemplateList objectTemplateList,
                         final SlotIdManager slotIdManager,
                         final ServerObjectTemplate template) {
        super(objectTemplateList, slotIdManager, template);

        description = new AutoDeltaVariable<>(StringId.INVALID, StringId::new);
        difficulty = new AutoDeltaInt();
        endLocation = new AutoDeltaVariable<>(Location::new);
        location = new AutoDeltaVariable<>(Vector::new);
        missionCreator = new AutoDeltaVariable<>(UnicodeString::new);
        missionType = new AutoDeltaInt();
        reward = new AutoDeltaInt();
        startLocation = new AutoDeltaVariable<>(Vector::new);
        targetAppearance = new AutoDeltaInt();
        title = new AutoDeltaVariable<>(StringId.INVALID, StringId::new);
        status = new AutoDeltaInt();
        targetName = new AutoDeltaString();
        waypoint = new AutoDeltaVariable<>(Waypoint::new);

        addMembersToPackages();
    }

    private void addMembersToPackages() {
        sharedPackage.addVariable(difficulty);
        sharedPackage.addVariable(endLocation);
        sharedPackage.addVariable(missionCreator);
        sharedPackage.addVariable(reward);
        sharedPackage.addVariable(startLocation);
        sharedPackage.addVariable(targetAppearance);
        sharedPackage.addVariable(description);
        sharedPackage.addVariable(title);
        sharedPackage.addVariable(status);
        sharedPackage.addVariable(missionType);
        sharedPackage.addVariable(targetName);
        sharedPackage.addVariable(waypoint);
    }
}

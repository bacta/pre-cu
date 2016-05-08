package com.ocdsoft.bacta.swg.precu.object.tangible.installation;

import com.ocdsoft.bacta.swg.precu.object.archive.delta.AutoDeltaBoolean;
import com.ocdsoft.bacta.swg.precu.object.archive.delta.AutoDeltaFloat;
import com.ocdsoft.bacta.swg.precu.object.tangible.TangibleObject;
import com.ocdsoft.bacta.swg.precu.object.template.server.ServerInstallationObjectTemplate;

public class InstallationObject extends TangibleObject {
    private final AutoDeltaBoolean activated;
    private final AutoDeltaFloat power;
    private final AutoDeltaFloat powerRate;

//    //This all gets moved to a synchronized ui object.
//    private final AutoDeltaBoolean resourcePoolUpdateFlag = new AutoDeltaBoolean(false, uiPackage);
//    private final AutoDeltaLong resourceIds = new AutoDeltaLong(0L, uiPackage);
//    private final AutoDeltaLong resourceIdsSecondary = new AutoDeltaLong(0L, uiPackage);
//    private final AutoDeltaStringVector resourceNames = new AutoDeltaVector<String>(uiPackage);
//    private final AutoDeltaStringVector resourceTypes = new AutoDeltaVector<String>(uiPackage);
//    private final AutoDeltaLong selectedResourceId = new AutoDeltaLong(0L, uiPackage);
//    private final AutoDeltaBoolean operating = new AutoDeltaBoolean(false, uiPackage);
//    private final AutoDeltaInt extractionRateDisplayed = new AutoDeltaInt(0, uiPackage);
//    private final AutoDeltaFloat extractionRateMax = new AutoDeltaFloat(0.0F, uiPackage);
//    private final AutoDeltaFloat extractionRate = new AutoDeltaFloat(0.0F, uiPackage);
//    private final AutoDeltaFloat hopperSize = new AutoDeltaFloat(0.0F, uiPackage);
//    private final AutoDeltaInt hopperSizeMax = new AutoDeltaInt(0, uiPackage);
//    private final AutoDeltaBoolean updateHopperContents = new AutoDeltaBoolean(false, uiPackage);
//    private final AutoDeltaVector<HopperEntry> hopperList = new AutoDeltaVector<HopperEntry>(uiPackage);
//    private final AutoDeltaByte conditionPercentage = new AutoDeltaByte(100, uiPackage); //TODO This seems fishy.

    private int installationType;
    private float tickCount;
    private float activateStartTime;

    public InstallationObject(final ServerInstallationObjectTemplate template) {
        super(template);

        activated = new AutoDeltaBoolean();
        power = new AutoDeltaFloat();
        powerRate = new AutoDeltaFloat();

        sharedPackage.addVariable(activated);
        sharedPackage.addVariable(power);
        sharedPackage.addVariable(powerRate);
    }
}

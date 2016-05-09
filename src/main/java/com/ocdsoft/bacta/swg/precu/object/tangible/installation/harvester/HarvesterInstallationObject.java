package com.ocdsoft.bacta.swg.precu.object.tangible.installation.harvester;

import com.google.inject.Inject;
import com.ocdsoft.bacta.swg.precu.object.tangible.installation.InstallationObject;
import com.ocdsoft.bacta.swg.precu.object.template.server.ServerHarvesterInstallationObjectTemplate;
import com.ocdsoft.bacta.swg.shared.container.SlotIdManager;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplateList;

/**
 * Created by crush on 5/7/2016.
 */
public class HarvesterInstallationObject extends InstallationObject {

    private float installedEfficiency;
    private long resourceType;
    private int maxExtractionRate;
    private float currentExtractionRate;
    private int maxHopperAmount;
    private long hopperResource;
    private float hopperAmount;

    @Inject
    public HarvesterInstallationObject(final ObjectTemplateList objectTemplateList,
                                       final SlotIdManager slotIdManager,
                                       final ServerHarvesterInstallationObjectTemplate template) {
        super(objectTemplateList, slotIdManager, template);


    }
}
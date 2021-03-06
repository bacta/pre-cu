package com.ocdsoft.bacta.swg.server.object.tangible.ship;

import com.google.inject.Inject;
import com.ocdsoft.bacta.swg.server.object.tangible.TangibleObject;
import com.ocdsoft.bacta.swg.server.object.template.server.ServerObjectTemplate;
import com.ocdsoft.bacta.swg.shared.container.SlotIdManager;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplateList;

/**
 * Created by crush on 9/4/2014.
 */
public class ShipObject extends TangibleObject {
    //private final AutoDeltaShort shipId;
    //private final AutoDeltaFloat slideDampener;
    //private final AutoDeltaFloat currentChassisHitPoints;
    //private final AutoDeltaFloat maximumChassisHitPoints;
    //private final AutoDeltaInt chassisType;
    //private final AutoDeltaFloat chassisComponentMassMaximum;
    //private final AutoDeltaFloat chassisComponentMassCurrent;
    //private final AutoDeltaFloat chassisSpeedMaximumModifier;
    //private final AutoDeltaFloat shipActualAccelerationRate;
    //private final AutoDeltaFloat shipActualDecelerationRate;
    //private final AutoDeltaFloat shipActualPitchAccelerationRate;
    //private final AutoDeltaFloat shipActualYawAccelerationRate;
    //private final AutoDeltaFloat shipActualRollAccelerationRate;
    //private final AutoDeltaFloat shipActualPitchRateMaximum;
    //private final AutoDeltaFloat shipActualYawRateMaximum;
    //private final AutoDeltaFloat shipActualRollRateMaximum;
    //private final AutoDeltaFloat shipActualSpeedMaximum;
    //private final AutoDeltaPackedMap<Integer, Float> componentArmorHitpointsMaximum;
    //private final AutoDeltaPackedMap<Integer, Float> componentArmorHitpointsCurrent;
    //private final AutoDeltaPackedMap<Integer, Float> componentEfficiencyGeneral;
    //private final AutoDeltaPackedMap<Integer, Float> componentEfficiencyEnergy;
    //private final AutoDeltaPackedMap<Integer, Float> componentEnergyMaintenanceRequirement;
    //private final AutoDeltaPackedMap<Integer, Float> componentMass;
    //private final AutoDeltaMap<Integer, Long> componentCrc;
    //private final AutoDeltaPackedMap<Integer, Float> componentHitpointsCurrent;
    //private final AutoDeltaPackedMap<Integer, Float> componentHitpointsMaximum;
    //private final AutoDeltaPackedMap<Integer, Integer> componentFlags;
    //private final AutoDeltaPackedMap<Integer, UnicodeString> componentNames;
    //private final AutoDeltaPackedMap<Integer, Long> componentCreators;
    //private final AutoDeltaPackedMap<Integer, Float> weaponDamageMaximum;
    //private final AutoDeltaPackedMap<Integer, Float> weaponDamageMinimum;
    //private final AutoDeltaPackedMap<Integer, Float> weaponEffectivenessShields;
    //private final AutoDeltaPackedMap<Integer, Float> weaponEffectivenessArmor;
    //private final AutoDeltaPackedMap<Integer, Float> weaponEnergyPerShot;
    //private final AutoDeltaPackedMap<Integer, Float> weaponRefireRate;
    //private final AutoDeltaPackedMap<Integer, Float> weaponEfficiencyRefireRate;
    //private final AutoDeltaPackedMap<Integer, Integer> weaponAmmoCurrent;
    //private final AutoDeltaPackedMap<Integer, Integer> weaponAmmoMaximum;
    //private final AutoDeltaPackedMap<Integer, Integer> weaponAmmoType;
    //private final AutoDeltaFloat shieldHitpointsFrontCurrent;
    //private final AutoDeltaFloat shieldHitpointsFrontMaximum;
    //private final AutoDeltaFloat shieldHitpointsBackCurrent;
    //private final AutoDeltaFloat shieldHitpointsBackMaximum;
    //private final AutoDeltaFloat shieldRechargeRate;
    //private final AutoDeltaFloat capacitorEnergyCurrent;
    //private final AutoDeltaFloat capacitorEnergyMaximum;
    //private final AutoDeltaFloat capacitorEnergyRechargeRate;
    //private final AutoDeltaFloat engineAccelerationRate;
    //private final AutoDeltaFloat engineDecelerationRate;
    //private final AutoDeltaFloat enginePitchAccelerationRate;
    //private final AutoDeltaFloat engineYawAccelerationRate;
    //private final AutoDeltaFloat engineRollAccelerationRate;
    //private final AutoDeltaFloat enginePitchRateMaximum;
    //private final AutoDeltaFloat engineYawRateMaximum;
    //private final AutoDeltaFloat engineRollRateMaximum;
    //private final AutoDeltaFloat engineSpeedMaximum;
    //private final AutoDeltaFloat reactorEnergyGenerationRate;
    //private final AutoDeltaFloat boosterEnergyCurrent;
    //private final AutoDeltaFloat boosterEnergyMaximum;
    //private final AutoDeltaFloat boosterEnergyRechargeRate;
    //private final AutoDeltaFloat boosterEnergyConsumptionRate;
    //private final AutoDeltaFloat boosterAcceleration;
    //private final AutoDeltaFloat boosterSpeedMaximum;
    //private final AutoDeltaFloat droidInterfaceCommandSpeed;
    //private final AutoDeltaLong installedDroidControlDevice;
    //private final AutoDeltaInt cargoHoldContentsMaximum;
    //private final AutoDeltaInt cargoHoldContentsCurrent;
    //private final AutoDeltaPackedMap<Long, Integer> cargoHoldContents;
    //private final AutoDeltaMap<Long, Pair<UnicodeString, String>> cargoHoldContentsResourceTypeInfo;
    //private final AutoDeltaLong pilotLookAtTarget;
    //private final AutoDeltaInt pilotLookAtTargetSlot;
    //Archive::AutoDeltaVariableCallback<BitArray,ShipObject::Callbacks::DefaultCallback<ShipObject::Messages::TargetableSlotBitfieldChanged,BitArray>,ShipObject> m_targetableSlotBitfield;
    //private final AutoDeltaString wingName;
    //private final AutoDeltaString typeName;
    //private final AutoDeltaString difficulty;
    //private final AutoDeltaString FACTION;
    //private final AutoDeltaInt guildId;

    @Inject
    public ShipObject(final ObjectTemplateList objectTemplateList,
                      final SlotIdManager slotIdManager,
                      final ServerObjectTemplate template) {
        super(objectTemplateList, slotIdManager, template);
    }
}

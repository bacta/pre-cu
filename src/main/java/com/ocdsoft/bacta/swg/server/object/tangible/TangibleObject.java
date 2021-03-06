package com.ocdsoft.bacta.swg.server.object.tangible;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.ocdsoft.bacta.engine.buffer.ByteBufferWritable;
import com.ocdsoft.bacta.engine.utils.BufferUtil;
import com.ocdsoft.bacta.soe.connection.SoeUdpConnection;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaBoolean;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaInt;
import com.ocdsoft.bacta.swg.archive.delta.AutoDeltaString;
import com.ocdsoft.bacta.swg.archive.delta.map.AutoDeltaStringObjectMap;
import com.ocdsoft.bacta.swg.archive.delta.set.AutoDeltaIntSet;
import com.ocdsoft.bacta.swg.archive.delta.set.AutoDeltaLongSet;
import com.ocdsoft.bacta.swg.server.message.game.scene.UpdatePvpStatusMessage;
import com.ocdsoft.bacta.swg.server.message.game.scene.UpdateTransformMessage;
import com.ocdsoft.bacta.swg.server.object.ServerObject;
import com.ocdsoft.bacta.swg.server.object.UpdateTransformCallback;
import com.ocdsoft.bacta.swg.server.object.tangible.creature.CreatureObject;
import com.ocdsoft.bacta.swg.server.object.template.server.ServerObjectTemplate;
import com.ocdsoft.bacta.swg.server.object.template.server.ServerTangibleObjectTemplate;
import com.ocdsoft.bacta.swg.server.object.template.shared.SharedTangibleObjectTemplate;
import com.ocdsoft.bacta.swg.server.zone.Zone;
import com.ocdsoft.bacta.swg.shared.container.Container;
import com.ocdsoft.bacta.swg.shared.container.SlotIdManager;
import com.ocdsoft.bacta.swg.shared.math.Transform;
import com.ocdsoft.bacta.swg.shared.math.Vector;
import com.ocdsoft.bacta.swg.shared.object.GameObject;
import com.ocdsoft.bacta.swg.shared.property.CustomizationDataProperty;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplateList;
import com.ocdsoft.bacta.swg.shared.utility.TriggerVolumeData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.magnos.steer.SteerSubject;
import org.magnos.steer.vec.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class TangibleObject extends ServerObject implements SteerSubject<Vec3> {
    private static final transient Logger LOGGER = LoggerFactory.getLogger(TangibleObject.class);


    @Override
    public int getObjectType() {
        return 0x54414E4F;
    } //'TANO'

    public static transient ImmutableSet<TangibleObject> NO_NEAR_OBJECTS = ImmutableSet.copyOf(new TangibleObject[0]);

    private transient ImmutableSet<TangibleObject> nearObjects = NO_NEAR_OBJECTS;
    @Setter
    @Getter
    protected transient Zone zone = null;
    @Getter
    protected String zoneName = "";
    @Getter
    @Setter
    private transient int movementCounter = 0;
    @Getter
    @Setter
    private transient boolean inert = true;

    private String customAppearnce;
    //private LocationData locationTargets

    @Getter
    private long ownerId;
    //private List<PvpEnemy> pvpEnemies;

    @Getter
    private boolean hidden;

    private final AutoDeltaInt pvpFaction;
    private final AutoDeltaInt pvpType;
    private final AutoDeltaString appearanceData;
    private final AutoDeltaIntSet components;
    private final AutoDeltaInt condition;
    private final AutoDeltaInt count;
    private final AutoDeltaInt damageTaken;
    private final AutoDeltaInt maxHitPoints;
    private final AutoDeltaBoolean visible;
    private final AutoDeltaBoolean inCombat;
    private final AutoDeltaLongSet passiveRevealPlayerCharacter;
    private final AutoDeltaInt mapColorOverride;
    private final AutoDeltaLongSet accessList;
    private final AutoDeltaIntSet guildAccessList;
    private final AutoDeltaStringObjectMap<TangibleObjectEffect> effectsMap;

    @Inject
    public TangibleObject(final ObjectTemplateList objectTemplateList,
                          final SlotIdManager slotIdManager,
                          final ServerObjectTemplate template) {
        super(objectTemplateList, slotIdManager, template, false);

        assert template instanceof ServerTangibleObjectTemplate;
        final ServerTangibleObjectTemplate objectTemplate = (ServerTangibleObjectTemplate) template;

        //hateList.setOwner(this);

        pvpFaction = new AutoDeltaInt();
        pvpType = new AutoDeltaInt();
        appearanceData = new AutoDeltaString("");
        components = new AutoDeltaIntSet();
        condition = new AutoDeltaInt(objectTemplate.getCondition());
        count = new AutoDeltaInt(objectTemplate.getCount());
        damageTaken = new AutoDeltaInt();
        maxHitPoints = new AutoDeltaInt(objectTemplate.getMaxHitPoints());
        visible = new AutoDeltaBoolean(true);
        inCombat = new AutoDeltaBoolean();
        passiveRevealPlayerCharacter = new AutoDeltaLongSet();
        mapColorOverride = new AutoDeltaInt();
        accessList = new AutoDeltaLongSet();
        guildAccessList = new AutoDeltaIntSet();
        effectsMap = new AutoDeltaStringObjectMap<>(TangibleObjectEffect::new);

        //Set to not disabled, and set invulnerable and wantSawAttack from template
        int condition = this.condition.get();
        condition &= ~(ServerTangibleObjectTemplate.Conditions.C_disabled.value
                | ServerTangibleObjectTemplate.Conditions.C_invulnerable.value
                | ServerTangibleObjectTemplate.Conditions.C_wantSawAttackTrigger.value);

        if (objectTemplate.getInvulnerable())
            condition |= ServerTangibleObjectTemplate.Conditions.C_invulnerable.value;

        if (objectTemplate.getWantSawAttackTriggers())
            condition |= ServerTangibleObjectTemplate.Conditions.C_wantSawAttackTrigger.value;

        this.condition.set(condition);


        //TODO: Attach trigger volumes to this object.
        int i, count = objectTemplate.getTriggerVolumesCount();

        for (i = 0; i < count; ++i) {
            final TriggerVolumeData trigger = objectTemplate.getTriggerVolumes(i);
            //createTriggerVolume(trigger.getRadius(), trigger.getName(), true);
        }

        //Initialize customization data property if any customization variables are declared for the shared tangible object template.
        final SharedTangibleObjectTemplate sharedObjectTemplate = (SharedTangibleObjectTemplate) getSharedTemplate();

        if (sharedObjectTemplate != null) {
            //TODO: WTF?!?
            //sharedObjectTemplate.createCustomizationDataPropertyAsNeeded(this);

            final CustomizationDataProperty cdProperty = getProperty(CustomizationDataProperty.getClassPropertyId());

            if (cdProperty != null) {
                //retrieve the customization data instance associated with the property.
                //final CustomizationData customizationData = cdProperty.fetchCustomizationData();

                //customizationData.registerModificationListener(customizaitonDataModificationCallback, this);
            }

            //CreateAppearances
        }

        //if (isWaypoint())
        //addNotification(ServerPathfindingNotification::getInstance());

        //Attach the collision property
        //final ServerCollisionProperty collision = new ServerCollisionProperty(this, getSharedTemplate());

        //addProperty(collision);

        addMembersToPackages();
    }

    private void addMembersToPackages() {
        sharedPackage.addVariable(pvpFaction);
        sharedPackage.addVariable(pvpType);
        sharedPackage.addVariable(appearanceData);
        sharedPackage.addVariable(components);
        sharedPackage.addVariable(condition);
        sharedPackage.addVariable(count);
        sharedPackage.addVariable(damageTaken);
        sharedPackage.addVariable(maxHitPoints);
        sharedPackage.addVariable(visible);
        sharedPackage.addVariable(inCombat);

        sharedPackageNp.addVariable(passiveRevealPlayerCharacter);
        sharedPackageNp.addVariable(mapColorOverride);
        sharedPackageNp.addVariable(accessList);
        sharedPackageNp.addVariable(guildAccessList);
        sharedPackageNp.addVariable(effectsMap);
    }


    public TangibleObject[] getNearObjects() {
        return nearObjects.toArray(new TangibleObject[nearObjects.size()]);
    }

    public String getAppearanceData() {
        return appearanceData.get();
    }

    public void setAppearanceData(final String appearanceData) {
        this.appearanceData.set(appearanceData);
        setDirty(true);
    }

    public final void setPosition(final Transform transform, boolean updateZone) {
        super.setTransform(transform);

        if (updateZone) {
            updateZone();
        }

        final int sequenceNumber = 0; //This value comes from the MessageQueueDataTransform packet.
        final byte speed = 2; //This value comes from the MessageQueueDataTransform packet.
        final byte lookAtYaw = 1; //This value comes from the MessageQueueDataTransform packet.
        final boolean useLookAtYaw = true; //This value comes from the MessageQueueDataTransform packet.

        //TODO: We should move this to the handler for the MessageQueueDataTransform packet...
        broadcastMessage(new UpdateTransformMessage(this, sequenceNumber, transform, speed, lookAtYaw, useLookAtYaw), false);
    }

    public void updateZone() {

        final ImmutableSet<TangibleObject> newNearObjects = getUpdatedNearObjects();

        final Set<TangibleObject> newObjects = Sets.difference(newNearObjects, nearObjects);
        final Set<TangibleObject> expiredObjects = Sets.difference(nearObjects, newNearObjects);

        nearObjects = newNearObjects;

        sendCreateAndBaselinesTo(newObjects.stream().filter(t -> t.connection != null).map(t -> t.connection).collect(Collectors.toSet()));
        sendDestroyTo(expiredObjects.stream().filter(t -> t.connection != null).map(t -> t.connection).collect(Collectors.toSet()));

        //Notify Appear
        newObjects.forEach(this::addInRangeObject);

        //Notify Disappear
        expiredObjects.forEach(this::removeInRangeObject);
    }

    public void clearZone() {
        zone = null;
        nearObjects = NO_NEAR_OBJECTS;
    }

    private ImmutableSet<TangibleObject> getUpdatedNearObjects() {
        if (zone == null) {
            return NO_NEAR_OBJECTS;
        }

        final UpdateTransformCallback updateTransformCallback = new UpdateTransformCallback(this);
        zone.contains(transform.getPositionInParent(), 160.f, Integer.MAX_VALUE, 1, updateTransformCallback);

        return updateTransformCallback.getNearObjects();
    }

    public void updateNearObjects() {
        nearObjects = getUpdatedNearObjects();
    }

    public void addInRangeObject(final TangibleObject tano) {
        if (tano.getConnection() != null && listeners.add(tano.getConnection()))
            tano.addInRangeObject(this);
    }

    public void removeInRangeObject(final TangibleObject tano) {
        if (tano.getConnection() != null && listeners.remove(tano.getConnection()))
            tano.removeInRangeObject(this);
    }

    public final void setCondition(ServerTangibleObjectTemplate.Conditions condition) {
        int currentCondition = this.condition.get();
        this.condition.set(currentCondition | (int) condition.value);
    }

    public final void clearCondition(ServerTangibleObjectTemplate.Conditions condition) {
        int currentCondition = this.condition.get();
        this.condition.set(currentCondition & ((int) (~condition.value)));
    }

    @Override
    public void setOwnerId(final long id) {

        // if we are changing owners, we are no longer insured
        boolean ownerChanged = false;
        if (ownerId != id) {
            ownerChanged = true;
            setInsured(false);
        }

        ownerId = id;
        final Container container = getContainerProperty();
        if (container != null) {
            final Iterator<ServerObject> i = container.iterator();
            while (i.hasNext()) {

                ServerObject content = i.next();

                if (content != null && CreatureObject.asCreatureObject(content) == null) {
                    content.setOwnerId(id);
                }
            }
        }

        // if we're hidden, who can see us has changed
        if (ownerChanged && isInWorld() && isHidden()) {
            visibilityDataModified();
        }
    }

    public void setHidden(final boolean hidden) {
        if (this.hidden != hidden) {
            visibilityDataModified();
            this.hidden = hidden;
        }
    }

    public void visibilityDataModified() {
        LOGGER.error("This method is not implemented");
        //TODO: Implement visibility modification
//        if (isInWorld()) {
//            if (isVisible() && !isHidden()) {
//                // show the object
//                final TriggerVolume triggerVolume = getTriggerVolume(NetworkTriggerVolumeNamespace::NetworkTriggerVolumeName);
//                if (triggerVolume != null) {
//                    std::vector<ServerObject *> observers;
//                    ServerWorld::findPlayerCreaturesInRange(getPosition_w(), triggerVolume->getRadius(), observers);
//                    if (!observers.empty())
//                        ObserveTracker::onObjectMadeVisibleTo(*this, observers);
//                }
//            }
//            else {
//                // hide the object
//                ObserveTracker::onObjectMadeInvisible(*this);
//
//                // if the object is hidden, show the object to
//                // players who have passively revealed the object
//                if (isVisible() && isHidden() && !m_passiveRevealPlayerCharacter.empty())
//                {
//                    const TriggerVolume * triggerVolume = getTriggerVolume(NetworkTriggerVolumeNamespace::NetworkTriggerVolumeName);
//                    if (triggerVolume != NULL) {
//                        std::vector<ServerObject *> possibleObservers;
//                        ServerWorld::findPlayerCreaturesInRange(getPosition_w(), triggerVolume->getRadius(), possibleObservers);
//                        if (!possibleObservers.empty())
//                        {
//                            std::vector<ServerObject *> allowedObservers;
//                            for (std::vector<ServerObject *>::const_iterator i = possibleObservers.begin(); i != possibleObservers.end(); ++i) {
//                                if (m_passiveRevealPlayerCharacter.contains((*i)->getNetworkId()))
//                                allowedObservers.push_back(*i);
//                            }
//
//                            if (!allowedObservers.empty())
//                                ObserveTracker::onObjectMadeVisibleTo(*this, allowedObservers);
//                        }
//                    }
//                }
//            }
//        }
    }

    public boolean isVisible() {
        return visible.get();
    }

    public void setInsured(final boolean insured) {
        if (insured)
            setCondition(ServerTangibleObjectTemplate.Conditions.C_insured);
        else
            clearCondition(ServerTangibleObjectTemplate.Conditions.C_insured);
    }

    public boolean isNonPvpObject() {
        return !getLocalFlag(LocalObjectFlags.PVPABLE);
    }

    @Override
    protected void sendObjectSpecificBaselinesToClient(final SoeUdpConnection client) {
        super.sendObjectSpecificBaselinesToClient(client);

        if (!isNonPvpObject()) {
            int flags = 0, factionsId = 0;

            //uint32 flags, factionId;
            //Pvp::getClientVisibleStatus(client, this, flags, factionId);
            final UpdatePvpStatusMessage statusMessage = new UpdatePvpStatusMessage(networkId, flags, factionsId);
            client.sendMessage(statusMessage);
            //PvpUpdateObserver::updatePvpStatusCache(client, this, flags, factionId);
        }
    }

    @Override
    public Vec3 getPosition() {
        return null;
    }

    @Override
    public Vec3 getPosition(Vec3 vec3) {
        return null;
    }

    @Override
    public float getRadius() {
        return 0;
    }

    @Override
    public float getDistanceAndNormal(Vec3 vec3, Vec3 v1, Vec3 v2) {
        return 0;
    }

    @Override
    public long getSpatialGroups() {
        return 0;
    }

    @Override
    public long getSpatialCollisionGroups() {
        return 0;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public void attach(Object o) {

    }

    @Override
    public <T> T attachment() {
        return null;
    }

    @Override
    public <T> T attachment(Class<T> aClass) {
        return null;
    }

    @Override
    public Vec3 getDirection() {
        return null;
    }

    @Override
    public Vec3 getVelocity() {
        return null;
    }

    @Override
    public float getMaximumVelocity() {
        return 0;
    }

    @Override
    public Vec3 getAcceleration() {
        return null;
    }

    @Override
    public float getMaximumAcceleration() {
        return 0;
    }

    @Override
    public Vec3 getTarget(SteerSubject<Vec3> steerSubject) {
        return null;
    }


    public static TangibleObject asTangibleObject(final GameObject object) {
        if (object instanceof TangibleObject)
            return (TangibleObject) object;

        return null;
    }

    @AllArgsConstructor
    public static final class TangibleObjectEffect implements ByteBufferWritable {
        public final String filename;
        public final String hardpoint;
        public final Vector offset;
        public final float scale;

        public TangibleObjectEffect(final ByteBuffer buffer) {
            this.filename = BufferUtil.getAscii(buffer);
            this.hardpoint = BufferUtil.getAscii(buffer);
            this.offset = new Vector(buffer);
            this.scale = buffer.getFloat();
        }

        @Override
        public void writeToBuffer(final ByteBuffer buffer) {
            BufferUtil.put(buffer, this.filename);
            BufferUtil.put(buffer, this.hardpoint);
            BufferUtil.put(buffer, this.offset);
            BufferUtil.put(buffer, this.scale);
        }
    }

    public static final class LocalObjectFlags {
        //This might cause a problem if TANO is statically initialized first.
        public static final int PVPABLE = ServerObject.LocalObjectFlags.MAX;
        public static final int CUSTOMIZATION_DATA_MODIFIED = ServerObject.LocalObjectFlags.MAX + 1;

        public static final int MAX = ServerObject.LocalObjectFlags.MAX + 2;
    }
}

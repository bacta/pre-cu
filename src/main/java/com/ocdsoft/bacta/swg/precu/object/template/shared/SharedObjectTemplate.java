package com.ocdsoft.bacta.swg.precu.object.template.shared;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.localization.StringId;
import com.ocdsoft.bacta.swg.shared.container.ArrangementDescriptor;
import com.ocdsoft.bacta.swg.shared.container.SlotDescriptor;
import com.ocdsoft.bacta.swg.shared.foundation.DataResourceList;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.shared.template.definition.TemplateDefinition;
import com.ocdsoft.bacta.swg.shared.utility.*;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
@TemplateDefinition
public class SharedObjectTemplate extends ObjectTemplate {
	public static final int TAG_SHAREDOBJECTTEMPLATE = Tag.convertStringToTag("SHOT");

	private static void registerTemplateConstructors(final DataResourceList<ObjectTemplate> objectTemplateList) {
		objectTemplateList.registerTemplate(SharedObjectTemplate.TAG_SHAREDOBJECTTEMPLATE, SharedObjectTemplate::new);
	}

	private int templateVersion;

	private final StringIdParam objectName = new StringIdParam(); //the default name of this object
	private final StringIdParam detailedDescription = new StringIdParam(); //long description of the object
	private final StringIdParam lookAtText = new StringIdParam(); //? does this differ from detailedDescription ?
	private final BoolParam snapToTerrain = new BoolParam(); //flag that the object is snapped to terrain on addition to the world
	private final IntegerParam containerType = new IntegerParam(); //if this is a container, what kind
	private final IntegerParam containerVolumeLimit = new IntegerParam(); //if this object has a volume container, this say how much it can hold.
	private final StringParam tintPalette = new StringParam(); //what tints are available to the object
	private final StringParam slotDescriptorFilename = new StringParam(); //what slots are available for this object
	private final StringParam arrangementDescriptorFilename = new StringParam(); //what slots this object can be put into
	private final StringParam appearanceFilename = new StringParam(); //?
	private final StringParam portalLayoutFilename = new StringParam(); //?
	private final StringParam clientDataFile = new StringParam(); //client-specific data
	private final FloatParam scale = new FloatParam(); //modification to object's size
	private final IntegerParam gameObjectType = new IntegerParam(); // game object type
	private final BoolParam sendToClient = new BoolParam(); //specifies whether or not the object using this template should is sent to the client
	private final FloatParam scaleThresholdBeforeExtentTest = new FloatParam(); //specifies the scale at which the object will use box extents for collision
	private final FloatParam clearFloraRadius = new FloatParam(); // distance to clear collidable flora around this object
	private final IntegerParam surfaceType = new IntegerParam(); 
	private final FloatParam noBuildRadius = new FloatParam(); // distance to not allow structure placement around this object
	private final BoolParam onlyVisibleInTools = new BoolParam(); 
	private final FloatParam locationReservationRadius = new FloatParam(); 
	private final BoolParam forceNoCollision = new BoolParam(); 

	public SharedObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	//@TDF-USER-START
	private ArrangementDescriptor arrangementDescriptor;
	private SlotDescriptor slotDescriptor;

	public ArrangementDescriptor getArrangementDescriptor() {
		return arrangementDescriptor;
	}

	public SlotDescriptor getSlotDescriptor() {
		return slotDescriptor;
	}

	public void setArrangementDescriptor(final ArrangementDescriptor arrangementDescriptor) {
		this.arrangementDescriptor = arrangementDescriptor;
	}

	public void setSlotDescriptor(final SlotDescriptor slotDescriptor) {
		this.slotDescriptor = slotDescriptor;
	}

	@Override
	protected void postLoad() {
		//TODO: Instead of storing a reference to arrangementDescriptorList and slotDescriptorList just for
		//this one method, we will create setters, and allot it to be set after creation, externally.
		//if (slotFilename != null && !slotFilename.isEmpty())
		//	slotDescriptor = slotDescriptorList.fetch(slotFilename);

		//if (arrangementFilename != null && !arrangementFilename.isEmpty())
		//	arrangementDescriptor = arrangementDescriptorList.fetch(arrangementFilename);

		//load the client data file?!
	}
	//@TDF-USER-END

	@Override
	public int getId() {
		return TAG_SHAREDOBJECTTEMPLATE;
	}

	public StringId getObjectName() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!objectName.isLoaded()) {
			if (base == null) {
				return StringId.INVALID;
			} else {
				return base.getObjectName();
			}
		}

		StringId value = this.objectName.getValue();
		return value;
	}

	public StringId getDetailedDescription() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!detailedDescription.isLoaded()) {
			if (base == null) {
				return StringId.INVALID;
			} else {
				return base.getDetailedDescription();
			}
		}

		StringId value = this.detailedDescription.getValue();
		return value;
	}

	public StringId getLookAtText() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!lookAtText.isLoaded()) {
			if (base == null) {
				return StringId.INVALID;
			} else {
				return base.getLookAtText();
			}
		}

		StringId value = this.lookAtText.getValue();
		return value;
	}

	public boolean getSnapToTerrain() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!snapToTerrain.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getSnapToTerrain();
			}
		}

		boolean value = this.snapToTerrain.getValue();
		return value;
	}

	public ContainerType getContainerType() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!containerType.isLoaded()) {
			if (base == null) {
				return ContainerType.from(0);
			} else {
				return base.getContainerType();
			}
		}

		return ContainerType.from(containerType.getValue());
	}

	public int getContainerVolumeLimit() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!containerVolumeLimit.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getContainerVolumeLimit();
			}
		}

		int value = this.containerVolumeLimit.getValue();
		final byte delta = this.containerVolumeLimit.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getContainerVolumeLimit();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (int) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (int) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public String getTintPalette() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!tintPalette.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getTintPalette();
			}
		}

		String value = this.tintPalette.getValue();
		return value;
	}

	public String getSlotDescriptorFilename() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!slotDescriptorFilename.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getSlotDescriptorFilename();
			}
		}

		String value = this.slotDescriptorFilename.getValue();
		return value;
	}

	public String getArrangementDescriptorFilename() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!arrangementDescriptorFilename.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getArrangementDescriptorFilename();
			}
		}

		String value = this.arrangementDescriptorFilename.getValue();
		return value;
	}

	public String getAppearanceFilename() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!appearanceFilename.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getAppearanceFilename();
			}
		}

		String value = this.appearanceFilename.getValue();
		return value;
	}

	public String getPortalLayoutFilename() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!portalLayoutFilename.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getPortalLayoutFilename();
			}
		}

		String value = this.portalLayoutFilename.getValue();
		return value;
	}

	public String getClientDataFile() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!clientDataFile.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getClientDataFile();
			}
		}

		String value = this.clientDataFile.getValue();
		return value;
	}

	public float getScale() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!scale.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getScale();
			}
		}

		float value = this.scale.getValue();
		final byte delta = this.scale.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getScale();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (float) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (float) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public GameObjectType getGameObjectType() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!gameObjectType.isLoaded()) {
			if (base == null) {
				return GameObjectType.from(0);
			} else {
				return base.getGameObjectType();
			}
		}

		return GameObjectType.from(gameObjectType.getValue());
	}

	public boolean getSendToClient() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!sendToClient.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getSendToClient();
			}
		}

		boolean value = this.sendToClient.getValue();
		return value;
	}

	public float getScaleThresholdBeforeExtentTest() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!scaleThresholdBeforeExtentTest.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getScaleThresholdBeforeExtentTest();
			}
		}

		float value = this.scaleThresholdBeforeExtentTest.getValue();
		final byte delta = this.scaleThresholdBeforeExtentTest.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getScaleThresholdBeforeExtentTest();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (float) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (float) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public float getClearFloraRadius() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!clearFloraRadius.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getClearFloraRadius();
			}
		}

		float value = this.clearFloraRadius.getValue();
		final byte delta = this.clearFloraRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getClearFloraRadius();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (float) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (float) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public SurfaceType getSurfaceType() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!surfaceType.isLoaded()) {
			if (base == null) {
				return SurfaceType.from(0);
			} else {
				return base.getSurfaceType();
			}
		}

		return SurfaceType.from(surfaceType.getValue());
	}

	public float getNoBuildRadius() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!noBuildRadius.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getNoBuildRadius();
			}
		}

		float value = this.noBuildRadius.getValue();
		final byte delta = this.noBuildRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getNoBuildRadius();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (float) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (float) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public boolean getOnlyVisibleInTools() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!onlyVisibleInTools.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getOnlyVisibleInTools();
			}
		}

		boolean value = this.onlyVisibleInTools.getValue();
		return value;
	}

	public float getLocationReservationRadius() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!locationReservationRadius.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getLocationReservationRadius();
			}
		}

		float value = this.locationReservationRadius.getValue();
		final byte delta = this.locationReservationRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getLocationReservationRadius();
			}

			if (delta == '+')
				value = baseValue + value;
			if (delta == '-')
				value = baseValue - value;
			if (delta == '=')
				value = baseValue + (float) (baseValue * (value / 100.0f));
			if (delta == '_')
				value = baseValue - (float) (baseValue * (value / 100.0f));
		}
		return value;
	}

	public boolean getForceNoCollision() {
		SharedObjectTemplate base = null;

		if (baseData != null)
			base = (SharedObjectTemplate) baseData;

		if (!forceNoCollision.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getForceNoCollision();
			}
		}

		boolean value = this.forceNoCollision.getValue();
		return value;
	}

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SHAREDOBJECTTEMPLATE) {
			return;
		}

		iff.enterForm();
		templateVersion = iff.getCurrentName();

		if (templateVersion == Tag.TAG_DERV) {
			iff.enterForm();
			iff.enterChunk();
			final String baseFilename = iff.readString();
			iff.exitChunk();
			final ObjectTemplate base = objectTemplateList.fetch(baseFilename);
			Preconditions.checkNotNull(base, "was unable to load base template %s", baseFilename);
			if (baseData == base && base != null) {
				base.releaseReference();
			} else {
				if (baseData != null)
					baseData.releaseReference();
				baseData = base;
			}
			iff.exitForm();
			templateVersion = iff.getCurrentName();
		}

		iff.enterForm();
		iff.enterChunk();
		final int paramCount = iff.readInt();
		iff.exitChunk();
		for (int i = 0; i < paramCount; ++i) {
			iff.enterChunk();
			final String parameterName = iff.readString();

			if ("objectName".equalsIgnoreCase(parameterName)) {
				objectName.loadFromIff(objectTemplateList, iff);
			} else if ("detailedDescription".equalsIgnoreCase(parameterName)) {
				detailedDescription.loadFromIff(objectTemplateList, iff);
			} else if ("lookAtText".equalsIgnoreCase(parameterName)) {
				lookAtText.loadFromIff(objectTemplateList, iff);
			} else if ("snapToTerrain".equalsIgnoreCase(parameterName)) {
				snapToTerrain.loadFromIff(objectTemplateList, iff);
			} else if ("containerType".equalsIgnoreCase(parameterName)) {
				containerType.loadFromIff(objectTemplateList, iff);
			} else if ("containerVolumeLimit".equalsIgnoreCase(parameterName)) {
				containerVolumeLimit.loadFromIff(objectTemplateList, iff);
			} else if ("tintPalette".equalsIgnoreCase(parameterName)) {
				tintPalette.loadFromIff(objectTemplateList, iff);
			} else if ("slotDescriptorFilename".equalsIgnoreCase(parameterName)) {
				slotDescriptorFilename.loadFromIff(objectTemplateList, iff);
			} else if ("arrangementDescriptorFilename".equalsIgnoreCase(parameterName)) {
				arrangementDescriptorFilename.loadFromIff(objectTemplateList, iff);
			} else if ("appearanceFilename".equalsIgnoreCase(parameterName)) {
				appearanceFilename.loadFromIff(objectTemplateList, iff);
			} else if ("portalLayoutFilename".equalsIgnoreCase(parameterName)) {
				portalLayoutFilename.loadFromIff(objectTemplateList, iff);
			} else if ("clientDataFile".equalsIgnoreCase(parameterName)) {
				clientDataFile.loadFromIff(objectTemplateList, iff);
			} else if ("scale".equalsIgnoreCase(parameterName)) {
				scale.loadFromIff(objectTemplateList, iff);
			} else if ("gameObjectType".equalsIgnoreCase(parameterName)) {
				gameObjectType.loadFromIff(objectTemplateList, iff);
			} else if ("sendToClient".equalsIgnoreCase(parameterName)) {
				sendToClient.loadFromIff(objectTemplateList, iff);
			} else if ("scaleThresholdBeforeExtentTest".equalsIgnoreCase(parameterName)) {
				scaleThresholdBeforeExtentTest.loadFromIff(objectTemplateList, iff);
			} else if ("clearFloraRadius".equalsIgnoreCase(parameterName)) {
				clearFloraRadius.loadFromIff(objectTemplateList, iff);
			} else if ("surfaceType".equalsIgnoreCase(parameterName)) {
				surfaceType.loadFromIff(objectTemplateList, iff);
			} else if ("noBuildRadius".equalsIgnoreCase(parameterName)) {
				noBuildRadius.loadFromIff(objectTemplateList, iff);
			} else if ("onlyVisibleInTools".equalsIgnoreCase(parameterName)) {
				onlyVisibleInTools.loadFromIff(objectTemplateList, iff);
			} else if ("locationReservationRadius".equalsIgnoreCase(parameterName)) {
				locationReservationRadius.loadFromIff(objectTemplateList, iff);
			} else if ("forceNoCollision".equalsIgnoreCase(parameterName)) {
				forceNoCollision.loadFromIff(objectTemplateList, iff);
			} else {
				throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
			}

			iff.exitChunk();
		}
		iff.exitForm();
	}

	public enum GameObjectType {
		GOT_none(0x00000000),
		GOT_corpse(1),
		GOT_group(2),
		GOT_guild(3),
		GOT_lair(4),
		GOT_static(5),
		GOT_camp(6), 
		GOT_vendor(7), 
		GOT_loadbeacon(8), 
		GOT_armor(0x00000100), //if you add armor types, please add them to the GOT_powerups as well
		GOT_armor_body(257),
		GOT_armor_head(258),
		GOT_armor_misc(259),
		GOT_armor_leg(260),
		GOT_armor_arm(261),
		GOT_armor_hand(262),
		GOT_armor_foot(263),
		GOT_armor_shield(264),
		GOT_armor_layer(265),
		GOT_armor_segment(266),
		GOT_armor_core(267),
		GOT_armor_psg(268),
		GOT_building(0x00000200),
		GOT_building_municipal(513),
		GOT_building_player(514),
		GOT_building_factional(515),
		GOT_creature(0x00000400),
		GOT_creature_character(1025),
		GOT_creature_droid(1026),
		GOT_creature_droid_probe(1027),
		GOT_creature_monster(1028),
		GOT_data(0x00000800),
		GOT_data_draft_schematic(2049),
		GOT_data_manufacturing_schematic(2050),
		GOT_data_mission_object(2051),
		GOT_data_token(2052),
		GOT_data_waypoint(2053),
		GOT_data_fictional(2054),
		GOT_data_pet_control_device(2055),
		GOT_data_vehicle_control_device(2056),
		GOT_data_draft_schematic_read_only(2057),
		GOT_data_ship_control_device(2058),
		GOT_data_droid_control_device(2059),
		GOT_data_house_control_device(2060),
		GOT_data_vendor_control_device(2061),
		GOT_data_player_quest_object(2062),
		GOT_installation(0x00001000),
		GOT_installation_factory(4097),
		GOT_installation_generator(4098),
		GOT_installation_harvester(4099),
		GOT_installation_turret(4100),
		GOT_installation_minefield(4101),
		GOT_misc(0x00002000), 
		GOT_misc_ammunition(8193), 
		GOT_misc_chemical(8194), 
		GOT_misc_clothing_DUMMY(8195), // when you remove this, please recompile all the shared object templates
		GOT_misc_component_DUMMY(8196), // when you remove this, please recompile all the shared object templates
		GOT_misc_container(8197),
		GOT_misc_crafting_station(8198),
		GOT_misc_deed_DUMMY(8199),
		GOT_misc_electronics(8200),
		GOT_misc_flora(8201),
		GOT_misc_food(8202),
		GOT_misc_furniture(8203), 
		GOT_misc_instrument(8204), 
		GOT_misc_pharmaceutical(8205), 
		GOT_misc_resource_container_DUMMY(8206), // when you remove this, please recompile all the shared object templates
		GOT_misc_sign(8207),
		GOT_misc_counter(8208), 
		GOT_misc_factory_crate(8209), 
		GOT_misc_ticket_travel(8210), 
		GOT_misc_item(8211), // generic 'usable' item
		GOT_misc_trap(8212),
		GOT_misc_container_wearable(8213),
		GOT_misc_fishing_pole(8214),
		GOT_misc_fishing_bait(8215),
		GOT_misc_drink(8216),
		GOT_misc_firework(8217),
		GOT_misc_item_usable(8218),
		GOT_misc_petmed(8219),
		GOT_misc_firework_show(8220),
		GOT_misc_clothing_attachment(8221),
		GOT_misc_live_sample(8222),
		GOT_misc_armor_attachment(8223),
		GOT_misc_community_crafting_project(8224),
		GOT_misc_force_crystal(8225),
		GOT_misc_droid_programming_chip(8226),
		GOT_misc_asteroid(8227),
		GOT_misc_pob_ship_pilot_chair(8228),
		GOT_misc_operations_chair(8229),
		GOT_misc_turret_access_ladder(8230),
		GOT_misc_container_ship_loot(8231),
		GOT_misc_armor_noequip(8232),
		GOT_misc_enzyme(8233),
		GOT_misc_food_pet(8234),
		GOT_misc_collection(8235),
		GOT_misc_container_public(8236),
		GOT_misc_ground_target(8237),
		GOT_misc_blueprint(8238),
		GOT_misc_enzyme_isomerase(8239),
		GOT_misc_enzyme_lyase(8240),
		GOT_misc_enzyme_hydrolase(8241),
		GOT_misc_tcg_card(8242),
		GOT_misc_appearance_only(8243),
		GOT_misc_appearance_only_invisible(8244),
		GOT_terminal(0x00004000),
		GOT_terminal_bank(16385),
		GOT_terminal_bazaar(16386),
		GOT_terminal_cloning(16387),
		GOT_terminal_insurance(16388),
		GOT_terminal_manage(16389),
		GOT_terminal_mission(16390),
		GOT_terminal_permissions(16391),
		GOT_terminal_player_structure(16392),
		GOT_terminal_shipping(16393),
		GOT_terminal_travel(16394),
		GOT_terminal_space(16395),
		GOT_terminal_misc(16396),
		GOT_terminal_space_npe(16397),
		GOT_tool(0x00008000),
		GOT_tool_crafting(32769),
		GOT_tool_survey(32770),
		GOT_tool_repair(32771),
		GOT_tool_camp_kit(32772),
		GOT_tool_ship_component_repair(32773),
		GOT_vehicle(0x00010000), 
		GOT_vehicle_hover(65537), 
		GOT_vehicle_hover_ai(65538), 
		GOT_weapon(0x00020000), //if you add weapon types, please add them to the GOT_powerups as well
		GOT_weapon_melee_misc(131073),
		GOT_weapon_ranged_misc(131074),
		GOT_weapon_ranged_thrown(131075),
		GOT_weapon_heavy_misc(131076),
		GOT_weapon_heavy_mine(131077),
		GOT_weapon_heavy_special(131078),
		GOT_weapon_melee_1h(131079),
		GOT_weapon_melee_2h(131080),
		GOT_weapon_melee_polearm(131081),
		GOT_weapon_ranged_pistol(131082),
		GOT_weapon_ranged_carbine(131083),
		GOT_weapon_ranged_rifle(131084),
		GOT_component(0x00040000),
		GOT_component_armor(262145),
		GOT_component_chemistry(262146),
		GOT_component_clothing(262147),
		GOT_component_droid(262148),
		GOT_component_electronics(262149),
		GOT_component_munition(262150),
		GOT_component_structure(262151),
		GOT_component_weapon_melee(262152),
		GOT_component_weapon_ranged(262153),
		GOT_component_tissue(262154),
		GOT_component_genetic(262155),
		GOT_component_saber_crystal(262156),
		GOT_component_community_crafting(262157),
		GOT_component_new_armor(262158),
		GOT_powerup_weapon(0x00080000),
		GOT_powerup_weapon_melee(524289),
		GOT_powerup_weapon_ranged(524290),
		GOT_powerup_weapon_thrown(524291),
		GOT_powerup_weapon_heavy(524292),
		GOT_powerup_weapon_mine(524293),
		GOT_powerup_weapon_heavy_special(524294),
		GOT_powerup_armor(0x00100000),
		GOT_powerup_armor_body(1048577),
		GOT_powerup_armor_head(1048578),
		GOT_powerup_armor_misc(1048579),
		GOT_powerup_armor_leg(1048580),
		GOT_powerup_armor_arm(1048581),
		GOT_powerup_armor_hand(1048582),
		GOT_powerup_armor_foot(1048583),
		GOT_powerup_armor_layer(1048584),
		GOT_powerup_armor_segment(1048585),
		GOT_powerup_armor_core(1048586),
		GOT_jewelry(0x00200000),
		GOT_jewelry_ring(2097153),
		GOT_jewelry_bracelet(2097154),
		GOT_jewelry_necklace(2097155),
		GOT_jewelry_earring(2097156),
		GOT_resource_container(0x00400000),
		GOT_resource_container_energy_gas(4194305),
		GOT_resource_container_energy_liquid(4194306),
		GOT_resource_container_energy_radioactive(4194307),
		GOT_resource_container_energy_solid(4194308),
		GOT_resource_container_inorganic_chemicals(4194309),
		GOT_resource_container_inorganic_gas(4194310),
		GOT_resource_container_inorganic_minerals(4194311),
		GOT_resource_container_inorganic_water(4194312),
		GOT_resource_container_organic_food(4194313),
		GOT_resource_container_organic_hide(4194314),
		GOT_resource_container_organic_structure(4194315),
		GOT_resource_container_pseudo(4194316),
		GOT_resource_container_space(4194317),
		GOT_deed(0x00800000),
		GOT_deed_building(8388609),
		GOT_deed_installation(8388610),
		GOT_deed_pet(8388611),
		GOT_deed_droid(8388612),
		GOT_deed_vehicle(8388613),
		GOT_clothing(0x01000000),
		GOT_clothing_bandolier(16777217),
		GOT_clothing_belt(16777218),
		GOT_clothing_bodysuit(16777219),
		GOT_clothing_cape(16777220),
		GOT_clothing_cloak(16777221),
		GOT_clothing_foot(16777222),
		GOT_clothing_dress(16777223),
		GOT_clothing_hand(16777224),
		GOT_clothing_eye(16777225),
		GOT_clothing_head(16777226),
		GOT_clothing_jacket(16777227),
		GOT_clothing_pants(16777228),
		GOT_clothing_robe(16777229),
		GOT_clothing_shirt(16777230),
		GOT_clothing_vest(16777231),
		GOT_clothing_wookiee(16777232), 
		GOT_clothing_misc(16777233), 
		GOT_clothing_skirt(16777234), 
		GOT_ship_component(0x40000000), //add space-specific GOTS at the "end" to make merging easier
		GOT_ship_component_reactor(1073741825),
		GOT_ship_component_engine(1073741826),
		GOT_ship_component_shield(1073741827),
		GOT_ship_component_armor(1073741828),
		GOT_ship_component_weapon(1073741829),
		GOT_ship_component_capacitor(1073741830),
		GOT_ship_component_booster(1073741831),
		GOT_ship_component_droid_interface(1073741832),
		GOT_ship_component_hangar(1073741833),
		GOT_ship_component_targeting_station(1073741834),
		GOT_ship_component_bridge(1073741835),
		GOT_ship_component_chassis(1073741836),
		GOT_ship_component_missilepack(1073741837),
		GOT_ship_component_countermeasurepack(1073741838),
		GOT_ship_component_missilelauncher(1073741839),
		GOT_ship_component_countermeasurelauncher(1073741840),
		GOT_ship_component_cargo_hold(1073741841),
		GOT_ship_component_modification(1073741842),
		GOT_ship(0x20000000),
		GOT_ship_fighter(536870913),
		GOT_ship_capital(536870914),
		GOT_ship_station(536870915),
		GOT_ship_transport(536870916),
		GOT_ship_mining_asteroid_static(536870917),
		GOT_ship_mining_asteroid_dynamic(536870918),
		GOT_cybernetic(0x20000100),
		GOT_cybernetic_arm(536871169),
		GOT_cybernetic_legs(536871170),
		GOT_cybernetic_torso(536871171),
		GOT_cybernetic_forearm(536871172),
		GOT_cybernetic_hand(536871173),
		GOT_cybernetic_component(536871174),
		GOT_chronicles(0x00001100),
		GOT_chronicles_relic(4353),
		GOT_chronicles_chronicle(4354),
		GOT_chronicles_quest_holocron(4355), 
		GOT_chronicles_quest_holocron_recipe(4356), 
		GOT_chronicles_relic_fragment(4357); 

		private static final GameObjectType[] values = values();
		public final long value;

		GameObjectType(final long value) {
			this.value = value;
		}
		public static GameObjectType from(final long value) {
			for (final GameObjectType e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum GameObjectType", value));
		}
	}

	public enum ContainerType {
		CT_none(0), 
		CT_slotted(1), 
		CT_volume(2), //This kind of container can hold tangible objects
		CT_volumeIntangible(3), //This kind of container can hold intangible objects
		CT_volumeGeneric(4), //This kind of container can hold any object tangible or not.
		CT_ridable(5); //This should be used for vehicles and mounts only: provides a slotted container with contents visible to the world

		private static final ContainerType[] values = values();
		public final long value;

		ContainerType(final long value) {
			this.value = value;
		}
		public static ContainerType from(final long value) {
			for (final ContainerType e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum ContainerType", value));
		}
	}

	public enum SurfaceType {
		ST_other(0),
		ST_metal(1),
		ST_stone(2),
		ST_wood(3),
		ST_acid(4),
		ST_ice(5), 
		ST_molten(6), 
		ST_obsidian(7); 

		private static final SurfaceType[] values = values();
		public final long value;

		SurfaceType(final long value) {
			this.value = value;
		}
		public static SurfaceType from(final long value) {
			for (final SurfaceType e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum SurfaceType", value));
		}
	}

}


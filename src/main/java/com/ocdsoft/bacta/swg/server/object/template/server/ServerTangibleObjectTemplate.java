package com.ocdsoft.bacta.swg.server.object.template.server;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.shared.foundation.DataResourceList;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.shared.template.definition.TemplateDefinition;
import com.ocdsoft.bacta.swg.shared.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
@TemplateDefinition
public class ServerTangibleObjectTemplate extends ServerObjectTemplate {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerTangibleObjectTemplate.class);
	public static final int TAG_SERVERTANGIBLEOBJECTTEMPLATE = Tag.convertStringToTag("TANO");

	private static void registerTemplateConstructors(final DataResourceList<ObjectTemplate> objectTemplateList) {
		objectTemplateList.registerTemplate(ServerTangibleObjectTemplate.TAG_SERVERTANGIBLEOBJECTTEMPLATE, ServerTangibleObjectTemplate::new);
	}

	private int templateVersion;

	// these MUST be reflected in: 
	// //depot/swg/current/dsrc/sku.0/sys.server/compiled/game/object/tangible_object_template.tdf
	// //depot/swg/current/dsrc/sku.0/sys.server/compiled/game/script/base_class.java
	// //depot/swg/current/src/engine/client/library/clientGame/src/shared/object/TangibleObject.h
	// //depot/swg/current/src/engine/server/library/serverGame/src/shared/object/TangibleObject.h
	private final List<TriggerVolumeParam> triggerVolumes = new ArrayList<>(); //trigger volume(s) attached to the object
	private boolean triggerVolumesLoaded;
	private boolean triggerVolumesAppend;
	private final IntegerParam combatSkeleton = new IntegerParam(); //this should be fixed (not random) for any template type
	private final IntegerParam maxHitPoints = new IntegerParam(); //hp for non-creature objects
	private final StringParam armor = new StringParam(); //what kind of armor this object has (if any)
	private final IntegerParam interestRadius = new IntegerParam(); //area of interest of the object
	private final IntegerParam count = new IntegerParam(); //generic counter
	private final IntegerParam condition = new IntegerParam(); //object condition
	private final BoolParam wantSawAttackTriggers = new BoolParam(); //whether we're interested in OnSawAttack triggers

	public ServerTangibleObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SERVERTANGIBLEOBJECTTEMPLATE;
	}

	public TriggerVolumeData getTriggerVolumes(int index) {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!triggerVolumesLoaded) {
			if (base == null) {
				return new TriggerVolumeData();
			} else {
				return base.getTriggerVolumes(index);
			}
		}

		if (triggerVolumesAppend && base != null) {
			int baseCount = base.getTriggerVolumesCount();

			if (index < baseCount) {
				return base.getTriggerVolumes(index);
			}
			index -= baseCount;
		}
		TriggerVolumeData value = this.triggerVolumes.get(index).getValue();
		return value;
	}

	public int getTriggerVolumesCount() {
		if (!triggerVolumesLoaded) {
			if (baseData == null)
				return 0;

			final ServerTangibleObjectTemplate base = (ServerTangibleObjectTemplate) baseData;
			return base.getTriggerVolumesCount();
		}

		int count = triggerVolumes.size();

		if (triggerVolumesAppend && baseData != null) {
			final ServerTangibleObjectTemplate base = (ServerTangibleObjectTemplate) baseData;
			count += base.getTriggerVolumesCount();
		}

		return count;
	}

	public CombatSkeleton getCombatSkeleton() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!combatSkeleton.isLoaded()) {
			if (base == null) {
				return CombatSkeleton.from(0);
			} else {
				return base.getCombatSkeleton();
			}
		}

		return CombatSkeleton.from(combatSkeleton.getValue());
	}

	public int getMaxHitPoints() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!maxHitPoints.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getMaxHitPoints();
			}
		}

		int value = this.maxHitPoints.getValue();
		final byte delta = this.maxHitPoints.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxHitPoints();
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

	public int getMaxHitPointsMin() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!maxHitPoints.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getMaxHitPointsMin();
			}
		}

		int value = this.maxHitPoints.getMinValue();
		final byte delta = this.maxHitPoints.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxHitPointsMin();
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

	public int getMaxHitPointsMax() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!maxHitPoints.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getMaxHitPointsMax();
			}
		}

		int value = this.maxHitPoints.getMaxValue();
		final byte delta = this.maxHitPoints.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxHitPointsMax();
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

	public ServerArmorTemplate getArmor() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!armor.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getArmor();
			}
		}

		ServerArmorTemplate returnValue = null;
		final String templateName = armor.getValue();

		if (!templateName.isEmpty()) {
			returnValue = objectTemplateList.fetch(templateName);

			if (returnValue == null)
				throw new IllegalStateException(String.format("error loading template %s", templateName));
		}

		return returnValue;
	}

	public int getInterestRadius() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!interestRadius.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getInterestRadius();
			}
		}

		int value = this.interestRadius.getValue();
		final byte delta = this.interestRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getInterestRadius();
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

	public int getInterestRadiusMin() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!interestRadius.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getInterestRadiusMin();
			}
		}

		int value = this.interestRadius.getMinValue();
		final byte delta = this.interestRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getInterestRadiusMin();
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

	public int getInterestRadiusMax() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!interestRadius.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getInterestRadiusMax();
			}
		}

		int value = this.interestRadius.getMaxValue();
		final byte delta = this.interestRadius.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getInterestRadiusMax();
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

	public int getCount() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!count.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getCount();
			}
		}

		int value = this.count.getValue();
		final byte delta = this.count.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getCount();
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

	public int getCountMin() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!count.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getCountMin();
			}
		}

		int value = this.count.getMinValue();
		final byte delta = this.count.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getCountMin();
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

	public int getCountMax() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!count.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getCountMax();
			}
		}

		int value = this.count.getMaxValue();
		final byte delta = this.count.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getCountMax();
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

	public int getCondition() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!condition.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getCondition();
			}
		}

		int value = this.condition.getValue();
		final byte delta = this.condition.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getCondition();
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

	public int getConditionMin() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!condition.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getConditionMin();
			}
		}

		int value = this.condition.getMinValue();
		final byte delta = this.condition.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getConditionMin();
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

	public int getConditionMax() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!condition.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getConditionMax();
			}
		}

		int value = this.condition.getMaxValue();
		final byte delta = this.condition.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getConditionMax();
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

	public boolean getWantSawAttackTriggers() {
		ServerTangibleObjectTemplate base = null;

		if (baseData instanceof ServerTangibleObjectTemplate)
			base = (ServerTangibleObjectTemplate) baseData;

		if (!wantSawAttackTriggers.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getWantSawAttackTriggers();
			}
		}

		boolean value = this.wantSawAttackTriggers.getValue();
		return value;
	}

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SERVERTANGIBLEOBJECTTEMPLATE) {
			super.load(iff);
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

			if ("triggerVolumes".equalsIgnoreCase(parameterName)) {
				triggerVolumes.clear();
				triggerVolumesAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final TriggerVolumeParam newData = new TriggerVolumeParam();
					newData.loadFromIff(objectTemplateList, iff);
					triggerVolumes.add(newData);
				}
				triggerVolumesLoaded = true;
			} else if ("combatSkeleton".equalsIgnoreCase(parameterName)) {
				combatSkeleton.loadFromIff(objectTemplateList, iff);
			} else if ("maxHitPoints".equalsIgnoreCase(parameterName)) {
				maxHitPoints.loadFromIff(objectTemplateList, iff);
			} else if ("armor".equalsIgnoreCase(parameterName)) {
				armor.loadFromIff(objectTemplateList, iff);
			} else if ("interestRadius".equalsIgnoreCase(parameterName)) {
				interestRadius.loadFromIff(objectTemplateList, iff);
			} else if ("count".equalsIgnoreCase(parameterName)) {
				count.loadFromIff(objectTemplateList, iff);
			} else if ("condition".equalsIgnoreCase(parameterName)) {
				condition.loadFromIff(objectTemplateList, iff);
			} else if ("wantSawAttackTriggers".equalsIgnoreCase(parameterName)) {
				wantSawAttackTriggers.loadFromIff(objectTemplateList, iff);
			} else {
				LOGGER.trace("Unexpected parameter {}", parameterName);
			}

			iff.exitChunk();
		}
		iff.exitForm();

		super.load(iff);
		iff.exitForm();
	}

	public enum CombatSkeleton {
		CS_none(0), // all "body" or not attackable
		CS_humanoid(1), // head, body, 2 arms, 2 legs, standard human proportions/layout
		CombatSkeleton_Last(CS_humanoid.value);

		private static final CombatSkeleton[] values = values();
		public final long value;

		CombatSkeleton(final long value) {
			this.value = value;
		}
		public static CombatSkeleton from(final long value) {
			for (final CombatSkeleton e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum CombatSkeleton", value));
		}
	}

	public enum Conditions {
		C_onOff(0x00000001),
		C_vendor(0x00000002),
		C_insured(0x00000004), 
		C_conversable(0x00000008), 
		C_hibernating(0x00000010), 
		C_magicItem(0x00000020), 
		C_aggressive(0x00000040), 
		C_wantSawAttackTrigger(0x00000080), 
		C_invulnerable(0x00000100), 
		C_disabled(0x00000200), 
		C_uninsurable(0x00000400), 
		C_interesting(0x00000800), 
		C_mount(0x00001000), //Set programmatically by mount system.  Do not set this in the template.
		C_crafted(0x00002000), //Set programmatically by crafting system.  Do not set this in the template.
		C_wingsOpened(0x00004000), //Set programmatically by wing system.  Do not set this in the template.
		C_spaceInteresting(0x00008000), 
		C_docking(0x00010000), //Set programmatically by docking system.  Do not set this in the template.
		C_destroying(0x00020000), //Set programmatically by destruction system.  Do not set this in the template.
		C_commable(0x00040000), 
		C_dockable(0x00080000), 
		C_eject(0x00100000), 
		C_inspectable(0x00200000), 
		C_transferable(0x00400000), 
		C_inflightTutorial(0x00800000), 
		C_spaceCombatMusic(0x01000000), //Set programmatically by the AI system.  Do not set this in the template.
		C_encounterLocked(0x02000000), 
		C_spawnedCreature(0x04000000),
		C_holidayInteresting(0x08000000),
		C_locked(0x10000000), 
		Conditions_Last(C_locked.value);

		private static final Conditions[] values = values();
		public final long value;

		Conditions(final long value) {
			this.value = value;
		}
		public static Conditions from(final long value) {
			for (final Conditions e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum Conditions", value));
		}
	}

}


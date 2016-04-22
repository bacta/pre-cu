package com.ocdsoft.bacta.swg.precu.object.template.server;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.template.ObjectTemplateList;
import com.ocdsoft.bacta.swg.utility.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
public class ServerCreatureObjectTemplate extends ServerTangibleObjectTemplate {
	private static final int TAG_SERVERCREATUREOBJECTTEMPLATE = Tag.convertStringToTag("0000");

	private int templateVersion;

	//Creature Attributes
	private final StringParam defaultWeapon = new StringParam(); //weapon to use if none is equipped
	private final IntegerParam[] attributes = new IntegerParam[]{ //initial value for attributes
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
	};
	private final IntegerParam[] minAttributes = new IntegerParam[]{ //minimum value for attributes
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
	};
	private final IntegerParam[] maxAttributes = new IntegerParam[]{ //maximum value for attributes
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
			new IntegerParam(),
	};
	private final FloatParam minDrainModifier = new FloatParam(); //min drain rate in units/sec
	private final FloatParam maxDrainModifier = new FloatParam(); //max drain rate in units/sec
	private final FloatParam minFaucetModifier = new FloatParam(); //min regeneration rate in units/sec
	private final FloatParam maxFaucetModifier = new FloatParam(); //max regeneration rate in units/sec
	private final List<StructParam<ObjectTemplate>> attribMods = new ArrayList<>(); //(de)buffs the creature is created with
	private boolean attribModsLoaded;
	private boolean attribModsAppend;
	private final IntegerParam shockWounds = new IntegerParam(); //current shock wounds
	private final BoolParam canCreateAvatar = new BoolParam(); //can a player create an avatar with this template
	private final StringParam nameGeneratorType = new StringParam(); //identifies which name generator to use
	// AI Behavioral Variables
	private final FloatParam approachTriggerRange = new FloatParam();
	private final FloatParam[] maxMentalStates = new FloatParam[]{ //maximum value for the mental state
			new FloatParam(),
			new FloatParam(),
			new FloatParam(),
			new FloatParam(),
	};
	private final FloatParam[] mentalStatesDecay = new FloatParam[]{ //time for the state to decay from 100 to 0
			new FloatParam(),
			new FloatParam(),
			new FloatParam(),
			new FloatParam(),
	};

	public ServerCreatureObjectTemplate(final String filename, final ObjectTemplateList objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SERVERCREATUREOBJECTTEMPLATE;
	}

	public ServerWeaponObjectTemplate getDefaultWeapon() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!defaultWeapon.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getDefaultWeapon();
			}
		}

		ServerWeaponObjectTemplate returnValue = null;
		final String templateName = defaultWeapon.getValue();

		if (!templateName.isEmpty()) {
			returnValue = objectTemplateList.fetch(templateName);

			if (returnValue == null)
				throw new IllegalStateException(String.format("error loading template %s", templateName));
		}

		return returnValue;
	}

	public int getAttributes(Attributes index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!attributes[(int) index.value].isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getAttributes(index);
			}
		}

		int value = this.attributes[(int) index.value].getValue();
		final byte delta = this.attributes[(int) index.value].getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getAttributes(index);
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

	public int getMinAttributes(Attributes index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!minAttributes[(int) index.value].isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getMinAttributes(index);
			}
		}

		int value = this.minAttributes[(int) index.value].getValue();
		final byte delta = this.minAttributes[(int) index.value].getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMinAttributes(index);
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

	public int getMaxAttributes(Attributes index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!maxAttributes[(int) index.value].isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getMaxAttributes(index);
			}
		}

		int value = this.maxAttributes[(int) index.value].getValue();
		final byte delta = this.maxAttributes[(int) index.value].getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxAttributes(index);
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

	public float getMinDrainModifier() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!minDrainModifier.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMinDrainModifier();
			}
		}

		float value = this.minDrainModifier.getValue();
		final byte delta = this.minDrainModifier.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMinDrainModifier();
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

	public float getMaxDrainModifier() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!maxDrainModifier.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMaxDrainModifier();
			}
		}

		float value = this.maxDrainModifier.getValue();
		final byte delta = this.maxDrainModifier.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxDrainModifier();
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

	public float getMinFaucetModifier() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!minFaucetModifier.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMinFaucetModifier();
			}
		}

		float value = this.minFaucetModifier.getValue();
		final byte delta = this.minFaucetModifier.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMinFaucetModifier();
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

	public float getMaxFaucetModifier() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!maxFaucetModifier.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMaxFaucetModifier();
			}
		}

		float value = this.maxFaucetModifier.getValue();
		final byte delta = this.maxFaucetModifier.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxFaucetModifier();
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

	public AttribMod getAttribMods(int index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!attribModsLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getAttribMods(index);
			}
		}

		if (attribModsAppend && base != null) {
			int baseCount = base.getAttribModsCount();

			if (index < baseCount) {
				return base.getAttribMods(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = attribMods.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final AttribModObjectTemplate param = (AttribModObjectTemplate) structTemplate;

		final AttribMod data = new AttribMod();
		data.target = param.getTarget();
		data.value = param.getValue();
		data.time = param.getTime();
		data.timeAtValue = param.getTimeAtValue();
		data.decay = param.getDecay();

		return data;
	}

	public int getAttribModsCount() {
		if (!attribModsLoaded) {
			if (baseData == null)
				return 0;

			final ServerCreatureObjectTemplate base = (ServerCreatureObjectTemplate) baseData;
			return base.getAttribModsCount();
		}

		int count = attribMods.size();

		if (attribModsAppend && baseData != null) {
			final ServerCreatureObjectTemplate base = (ServerCreatureObjectTemplate) baseData;
			count += base.getAttribModsCount();
		}

		return count;
	}

	public int getShockWounds() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!shockWounds.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getShockWounds();
			}
		}

		int value = this.shockWounds.getValue();
		final byte delta = this.shockWounds.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getShockWounds();
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

	public boolean getCanCreateAvatar() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!canCreateAvatar.isLoaded()) {
			if (base == null) {
				return false;
			} else {
				return base.getCanCreateAvatar();
			}
		}

		boolean value = this.canCreateAvatar.getValue();
		return value;
	}

	public String getNameGeneratorType() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!nameGeneratorType.isLoaded()) {
			if (base == null) {
				return "";
			} else {
				return base.getNameGeneratorType();
			}
		}

		String value = this.nameGeneratorType.getValue();
		return value;
	}

	public float getApproachTriggerRange() {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!approachTriggerRange.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getApproachTriggerRange();
			}
		}

		float value = this.approachTriggerRange.getValue();
		final byte delta = this.approachTriggerRange.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getApproachTriggerRange();
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

	public float getMaxMentalStates(MentalStates index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!maxMentalStates[(int) index.value].isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMaxMentalStates(index);
			}
		}

		float value = this.maxMentalStates[(int) index.value].getValue();
		final byte delta = this.maxMentalStates[(int) index.value].getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxMentalStates(index);
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

	public float getMentalStatesDecay(MentalStates index) {
		ServerCreatureObjectTemplate base = null;

		if (baseData != null)
			base = (ServerCreatureObjectTemplate) baseData;

		if (!mentalStatesDecay[(int) index.value].isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMentalStatesDecay(index);
			}
		}

		float value = this.mentalStatesDecay[(int) index.value].getValue();
		final byte delta = this.mentalStatesDecay[(int) index.value].getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMentalStatesDecay(index);
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

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SERVERCREATUREOBJECTTEMPLATE)
			return;

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

			if ("".equalsIgnoreCase(parameterName)) {
				defaultWeapon.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				int listCount = iff.readInt();
				int j;
				for (j = 0; j < 6 && j < listCount; ++j)
					attributes[j].loadFromIff(objectTemplateList, iff);
				for (; j < listCount; ++j) {
					final IntegerParam dummy = new IntegerParam();
					dummy.loadFromIff(objectTemplateList, iff);
				}
			} else if ("".equalsIgnoreCase(parameterName)) {
				int listCount = iff.readInt();
				int j;
				for (j = 0; j < 6 && j < listCount; ++j)
					minAttributes[j].loadFromIff(objectTemplateList, iff);
				for (; j < listCount; ++j) {
					final IntegerParam dummy = new IntegerParam();
					dummy.loadFromIff(objectTemplateList, iff);
				}
			} else if ("".equalsIgnoreCase(parameterName)) {
				int listCount = iff.readInt();
				int j;
				for (j = 0; j < 6 && j < listCount; ++j)
					maxAttributes[j].loadFromIff(objectTemplateList, iff);
				for (; j < listCount; ++j) {
					final IntegerParam dummy = new IntegerParam();
					dummy.loadFromIff(objectTemplateList, iff);
				}
			} else if ("".equalsIgnoreCase(parameterName)) {
				minDrainModifier.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				maxDrainModifier.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				minFaucetModifier.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				maxFaucetModifier.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				attribMods.clear();
				attribModsAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
					newData.loadFromIff(objectTemplateList, iff);
					attribMods.add(newData);
				}
				attribModsLoaded = true;
			} else if ("".equalsIgnoreCase(parameterName)) {
				shockWounds.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				canCreateAvatar.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				nameGeneratorType.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				approachTriggerRange.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				int listCount = iff.readInt();
				int j;
				for (j = 0; j < 4 && j < listCount; ++j)
					maxMentalStates[j].loadFromIff(objectTemplateList, iff);
				for (; j < listCount; ++j) {
					final FloatParam dummy = new FloatParam();
					dummy.loadFromIff(objectTemplateList, iff);
				}
			} else if ("".equalsIgnoreCase(parameterName)) {
				int listCount = iff.readInt();
				int j;
				for (j = 0; j < 4 && j < listCount; ++j)
					mentalStatesDecay[j].loadFromIff(objectTemplateList, iff);
				for (; j < listCount; ++j) {
					final FloatParam dummy = new FloatParam();
					dummy.loadFromIff(objectTemplateList, iff);
				}
			} else {
				throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
			}

			iff.exitChunk();
		}
		iff.exitForm();
	}

	public enum PathNodeType {
		PN_Open(0), //grasslands
		PN_SparseCover(1), //light forests, vaporator farms
		PN_DenseCover(2), //dense forests etc.
		PN_NaturalInterior(3), //caves
		PN_ArtificialInterior(4), //buildings
		PN_NaturalPath(5), //paths and trails
		PN_ArtificialPath(6), //roads
		PN_PassableWater(7), //rivers, ponds, shorelines
		PN_ImpassableWater(8); //big lakes and oceans

		private static final PathNodeType[] values = values();
		public final long value;

		PathNodeType(final long value) {
			this.value = value;
		}

		public static PathNodeType from(final long value) {
			for (final PathNodeType e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum PathNodeType", value));
		}
	}

}

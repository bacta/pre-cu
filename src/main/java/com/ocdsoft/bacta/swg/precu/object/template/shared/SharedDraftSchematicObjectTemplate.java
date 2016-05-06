package com.ocdsoft.bacta.swg.precu.object.template.shared;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.localization.StringId;
import com.ocdsoft.bacta.swg.shared.foundation.DataResourceList;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.shared.template.definition.TemplateDefinition;
import com.ocdsoft.bacta.swg.shared.utility.IntegerParam;
import com.ocdsoft.bacta.swg.shared.utility.StringIdParam;
import com.ocdsoft.bacta.swg.shared.utility.StringParam;
import com.ocdsoft.bacta.swg.shared.utility.StructParam;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
@TemplateDefinition
public class SharedDraftSchematicObjectTemplate extends SharedIntangibleObjectTemplate {
	public static final int TAG_SHAREDDRAFTSCHEMATICOBJECTTEMPLATE = Tag.convertStringToTag("SDSC");

	private int templateVersion;

	// this enum is also defined in the server object_template.tdf file
	// this enum is also defined in the server object_template.tdf file
	private final List<StructParam<ObjectTemplate>> slots = new ArrayList<>(); //ingredient slots
	private boolean slotsLoaded;
	private boolean slotsAppend;
	private final List<StructParam<ObjectTemplate>> attributes = new ArrayList<>(); //what attributes the schematic can affect
	private boolean attributesLoaded;
	private boolean attributesAppend;
	private final StringParam craftedSharedTemplate = new StringParam();

	public SharedDraftSchematicObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SHAREDDRAFTSCHEMATICOBJECTTEMPLATE;
	}

	public IngredientSlot getSlots(int index) {
		SharedDraftSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (SharedDraftSchematicObjectTemplate) baseData;

		if (!slotsLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getSlots(index);
			}
		}

		if (slotsAppend && base != null) {
			int baseCount = base.getSlotsCount();

			if (index < baseCount) {
				return base.getSlots(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = slots.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final IngredientSlotObjectTemplate param = (IngredientSlotObjectTemplate) structTemplate;

		final IngredientSlot data = new IngredientSlot();
		data.name = param.getName();
		data.hardpoint = param.getHardpoint();

		return data;
	}

	public int getSlotsCount() {
		if (!slotsLoaded) {
			if (baseData == null)
				return 0;

			final SharedDraftSchematicObjectTemplate base = (SharedDraftSchematicObjectTemplate) baseData;
			return base.getSlotsCount();
		}

		int count = slots.size();

		if (slotsAppend && baseData != null) {
			final SharedDraftSchematicObjectTemplate base = (SharedDraftSchematicObjectTemplate) baseData;
			count += base.getSlotsCount();
		}

		return count;
	}

	public SchematicAttribute getAttributes(int index) {
		SharedDraftSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (SharedDraftSchematicObjectTemplate) baseData;

		if (!attributesLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getAttributes(index);
			}
		}

		if (attributesAppend && base != null) {
			int baseCount = base.getAttributesCount();

			if (index < baseCount) {
				return base.getAttributes(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = attributes.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final SchematicAttributeObjectTemplate param = (SchematicAttributeObjectTemplate) structTemplate;

		final SchematicAttribute data = new SchematicAttribute();
		data.name = param.getName();
		data.experiment = param.getExperiment();
		data.value = param.getValue();

		return data;
	}

	public int getAttributesCount() {
		if (!attributesLoaded) {
			if (baseData == null)
				return 0;

			final SharedDraftSchematicObjectTemplate base = (SharedDraftSchematicObjectTemplate) baseData;
			return base.getAttributesCount();
		}

		int count = attributes.size();

		if (attributesAppend && baseData != null) {
			final SharedDraftSchematicObjectTemplate base = (SharedDraftSchematicObjectTemplate) baseData;
			count += base.getAttributesCount();
		}

		return count;
	}

	public String getCraftedSharedTemplate() {
		SharedDraftSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (SharedDraftSchematicObjectTemplate) baseData;

		if (!craftedSharedTemplate.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getCraftedSharedTemplate();
			}
		}

		String value = this.craftedSharedTemplate.getValue();
		return value;
	}

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SHAREDDRAFTSCHEMATICOBJECTTEMPLATE)
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
				slots.clear();
				slotsAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
					newData.loadFromIff(objectTemplateList, iff);
					slots.add(newData);
				}
				slotsLoaded = true;
			} else if ("".equalsIgnoreCase(parameterName)) {
				attributes.clear();
				attributesAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
					newData.loadFromIff(objectTemplateList, iff);
					attributes.add(newData);
				}
				attributesLoaded = true;
			} else if ("".equalsIgnoreCase(parameterName)) {
				craftedSharedTemplate.loadFromIff(objectTemplateList, iff);
			} else {
				throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
			}

			iff.exitChunk();
		}
		iff.exitForm();
	}

	public enum ArmorRating {
		AR_armorNone(0),
		AR_armorLight(1),
		AR_armorMedium(2),
		AR_armorHeavy(3);

		private static final ArmorRating[] values = values();
		public final long value;

		ArmorRating(final long value) {
			this.value = value;
		}

		public static ArmorRating from(final long value) {
			for (final ArmorRating e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum ArmorRating", value));
		}
	}

	public enum DamageType {
		DT_kinetic(0x00000001),
		DT_energy(0x00000002),
		DT_blast(0x00000004),
		DT_stun(0x00000008),
		DT_restraint(0x00000010),
		DT_elemental_heat(0x00000020),
		DT_elemental_cold(0x00000040),
		DT_elemental_acid(0x00000080),
		DT_elemental_electrical(0x00000100),
		DT_environmental_heat(0x00000200),
		DT_environmental_cold(0x00000400),
		DT_environmental_acid(0x00000800),
		DT_environmental_electrical(0x00001000);

		private static final DamageType[] values = values();
		public final long value;

		DamageType(final long value) {
			this.value = value;
		}

		public static DamageType from(final long value) {
			for (final DamageType e : values)
				if (e.value == value) return e;
			throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum DamageType", value));
		}
	}

	public static class IngredientSlot {
		@Getter
		protected StringId name;
		@Getter
		protected String hardpoint;
	}

	protected static class IngredientSlotObjectTemplate extends ObjectTemplate {
		public static final int TAG_INGREDIENTSLOT = Tag.convertStringToTag("SISS");

		public IngredientSlotObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
			super(filename, objectTemplateList);
		}

		private final StringIdParam name = new StringIdParam(); //slot name
		private final StringParam hardpoint = new StringParam(); //UI hardpoint for this slot

		@Override
		public int getId() {
			return TAG_INGREDIENTSLOT;
		}

		public StringId getName() {
			IngredientSlotObjectTemplate base = null;

			if (baseData != null)
				base = (IngredientSlotObjectTemplate) baseData;

			if (!name.isLoaded()) {
				if (base == null) {
					return StringId.INVALID;
				} else {
					return base.getName();
				}
			}

			StringId value = this.name.getValue();
			return value;
		}

		public String getHardpoint() {
			IngredientSlotObjectTemplate base = null;

			if (baseData != null)
				base = (IngredientSlotObjectTemplate) baseData;

			if (!hardpoint.isLoaded()) {
				if (base == null) {
					return "";
				} else {
					return base.getHardpoint();
				}
			}

			String value = this.hardpoint.getValue();
			return value;
		}

		@Override
		protected void load(final Iff iff) {
			iff.enterForm();
			iff.enterChunk();
			final int paramCount = iff.readInt();
			iff.exitChunk();
			for (int i = 0; i < paramCount; ++i) {
				iff.enterChunk();
				final String parameterName = iff.readString();

				if ("	".equalsIgnoreCase(parameterName)) {
					name.loadFromIff(objectTemplateList, iff);
				} else if ("	".equalsIgnoreCase(parameterName)) {
					hardpoint.loadFromIff(objectTemplateList, iff);
				} else {
					throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
				}

				iff.exitChunk();
			}
			iff.exitForm();
		}

	}

	public static class SchematicAttribute {
		@Getter
		protected StringId name;
		@Getter
		protected StringId experiment;
		@Getter
		protected int value;
	}

	protected static class SchematicAttributeObjectTemplate extends ObjectTemplate {
		public static final int TAG_SCHEMATICATTRIBUTE = Tag.convertStringToTag("DSSA");

		public SchematicAttributeObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
			super(filename, objectTemplateList);
		}

		private final StringIdParam name = new StringIdParam(); //name of the attribute
		private final StringIdParam experiment = new StringIdParam(); //name of the experimental attribute associated with the attribute
		private final IntegerParam value = new IntegerParam(); //value of the attribute - a range for draft schematics and a single value for manufacture schematics

		@Override
		public int getId() {
			return TAG_SCHEMATICATTRIBUTE;
		}

		public StringId getName() {
			SchematicAttributeObjectTemplate base = null;

			if (baseData != null)
				base = (SchematicAttributeObjectTemplate) baseData;

			if (!name.isLoaded()) {
				if (base == null) {
					return StringId.INVALID;
				} else {
					return base.getName();
				}
			}

			StringId value = this.name.getValue();
			return value;
		}

		public StringId getExperiment() {
			SchematicAttributeObjectTemplate base = null;

			if (baseData != null)
				base = (SchematicAttributeObjectTemplate) baseData;

			if (!experiment.isLoaded()) {
				if (base == null) {
					return StringId.INVALID;
				} else {
					return base.getExperiment();
				}
			}

			StringId value = this.experiment.getValue();
			return value;
		}

		public int getValue() {
			SchematicAttributeObjectTemplate base = null;

			if (baseData != null)
				base = (SchematicAttributeObjectTemplate) baseData;

			if (!value.isLoaded()) {
				if (base == null) {
					return 0;
				} else {
					return base.getValue();
				}
			}

			int value = this.value.getValue();
			final byte delta = this.value.getDeltaType();

			if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
				int baseValue = 0;

				if (baseData != null) {
					if (base != null)
						baseValue = base.getValue();
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

		@Override
		protected void load(final Iff iff) {
			iff.enterForm();
			iff.enterChunk();
			final int paramCount = iff.readInt();
			iff.exitChunk();
			for (int i = 0; i < paramCount; ++i) {
				iff.enterChunk();
				final String parameterName = iff.readString();

				if ("	".equalsIgnoreCase(parameterName)) {
					name.loadFromIff(objectTemplateList, iff);
				} else if ("	".equalsIgnoreCase(parameterName)) {
					experiment.loadFromIff(objectTemplateList, iff);
				} else if ("	".equalsIgnoreCase(parameterName)) {
					value.loadFromIff(objectTemplateList, iff);
				} else {
					throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
				}

				iff.exitChunk();
			}
			iff.exitForm();
		}

	}

}


package com.ocdsoft.bacta.swg.precu.object.template.server;

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
public class ServerManufactureSchematicObjectTemplate extends ServerIntangibleObjectTemplate {
	public static final int TAG_SERVERMANUFACTURESCHEMATICOBJECTTEMPLATE = Tag.convertStringToTag("MSCO");

	private static void registerTemplateConstructors(final DataResourceList<ObjectTemplate> objectTemplateList) {
		objectTemplateList.registerTemplate(ServerManufactureSchematicObjectTemplate.TAG_SERVERMANUFACTURESCHEMATICOBJECTTEMPLATE, ServerManufactureSchematicObjectTemplate::new);
		objectTemplateList.registerTemplate(IngredientSlotObjectTemplate.TAG_INGREDIENTSLOT, IngredientSlotObjectTemplate::new);
	}

	private int templateVersion;

	private final StringParam draftSchematic = new StringParam(); //source draft schematic
	private final StringParam creator = new StringParam(); //who created me
	private final List<StructParam<ObjectTemplate>> ingredients = new ArrayList<>(); //ingredients needed to create
	private boolean ingredientsLoaded;
	private boolean ingredientsAppend;
	private final IntegerParam itemCount = new IntegerParam(); //number of items this schematic can make
	private final List<StructParam<ObjectTemplate>> attributes = new ArrayList<>(); //values for the attributes the schematic affects
	private boolean attributesLoaded;
	private boolean attributesAppend;

	public ServerManufactureSchematicObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SERVERMANUFACTURESCHEMATICOBJECTTEMPLATE;
	}

	public String getDraftSchematic() {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!draftSchematic.isLoaded()) {
			if (base == null) {
				return null;
			} else {
				return base.getDraftSchematic();
			}
		}

		String value = this.draftSchematic.getValue();
		return value;
	}

	public String getCreator() {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!creator.isLoaded()) {
			if (base == null) {
				return "";
			} else {
				return base.getCreator();
			}
		}

		String value = this.creator.getValue();
		return value;
	}

	public IngredientSlot getIngredients(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!ingredientsLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getIngredients(index);
			}
		}

		if (ingredientsAppend && base != null) {
			int baseCount = base.getIngredientsCount();

			if (index < baseCount) {
				return base.getIngredients(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = ingredients.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final IngredientSlotObjectTemplate param = (IngredientSlotObjectTemplate) structTemplate;

		final IngredientSlot data = new IngredientSlot();
		data.name = param.getName();
		data.ingredient = param.getIngredient();

		return data;
	}

	public IngredientSlot getIngredientsMin(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!ingredientsLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getIngredientsMin(index);
			}
		}

		if (ingredientsAppend && base != null) {
			int baseCount = base.getIngredientsCount();

			if (index < baseCount) {
				return base.getIngredientsMin(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = ingredients.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final IngredientSlotObjectTemplate param = (IngredientSlotObjectTemplate) structTemplate;

		final IngredientSlot data = new IngredientSlot();
		data.name = param.getName();
		data.ingredient = param.getIngredientMin();

		return data;
	}

	public IngredientSlot getIngredientsMax(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!ingredientsLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getIngredientsMax(index);
			}
		}

		if (ingredientsAppend && base != null) {
			int baseCount = base.getIngredientsCount();

			if (index < baseCount) {
				return base.getIngredientsMax(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = ingredients.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final IngredientSlotObjectTemplate param = (IngredientSlotObjectTemplate) structTemplate;

		final IngredientSlot data = new IngredientSlot();
		data.name = param.getName();
		data.ingredient = param.getIngredientMax();

		return data;
	}

	public int getIngredientsCount() {
		if (!ingredientsLoaded) {
			if (baseData == null)
				return 0;

			final ServerManufactureSchematicObjectTemplate base = (ServerManufactureSchematicObjectTemplate) baseData;
			return base.getIngredientsCount();
		}

		int count = ingredients.size();

		if (ingredientsAppend && baseData != null) {
			final ServerManufactureSchematicObjectTemplate base = (ServerManufactureSchematicObjectTemplate) baseData;
			count += base.getIngredientsCount();
		}

		return count;
	}

	public int getItemCount() {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!itemCount.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getItemCount();
			}
		}

		int value = this.itemCount.getValue();
		final byte delta = this.itemCount.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getItemCount();
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

	public int getItemCountMin() {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!itemCount.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getItemCountMin();
			}
		}

		int value = this.itemCount.getMinValue();
		final byte delta = this.itemCount.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getItemCountMin();
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

	public int getItemCountMax() {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!itemCount.isLoaded()) {
			if (base == null) {
				return 0;
			} else {
				return base.getItemCountMax();
			}
		}

		int value = this.itemCount.getMaxValue();
		final byte delta = this.itemCount.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			int baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getItemCountMax();
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

	public SchematicAttribute getAttributes(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

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
		data.value = param.getValue();

		return data;
	}

	public SchematicAttribute getAttributesMin(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!attributesLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getAttributesMin(index);
			}
		}

		if (attributesAppend && base != null) {
			int baseCount = base.getAttributesCount();

			if (index < baseCount) {
				return base.getAttributesMin(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = attributes.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final SchematicAttributeObjectTemplate param = (SchematicAttributeObjectTemplate) structTemplate;

		final SchematicAttribute data = new SchematicAttribute();
		data.name = param.getName();
		data.value = param.getValueMin();

		return data;
	}

	public SchematicAttribute getAttributesMax(int index) {
		ServerManufactureSchematicObjectTemplate base = null;

		if (baseData != null)
			base = (ServerManufactureSchematicObjectTemplate) baseData;

		if (!attributesLoaded) {
			if (base == null) {
				return null;
			} else {
				return base.getAttributesMax(index);
			}
		}

		if (attributesAppend && base != null) {
			int baseCount = base.getAttributesCount();

			if (index < baseCount) {
				return base.getAttributesMax(index);
			}
			index -= baseCount;
		}
		final ObjectTemplate structTemplate = attributes.get(index).getValue();
		Preconditions.checkNotNull(structTemplate);
		final SchematicAttributeObjectTemplate param = (SchematicAttributeObjectTemplate) structTemplate;

		final SchematicAttribute data = new SchematicAttribute();
		data.name = param.getName();
		data.value = param.getValueMax();

		return data;
	}

	public int getAttributesCount() {
		if (!attributesLoaded) {
			if (baseData == null)
				return 0;

			final ServerManufactureSchematicObjectTemplate base = (ServerManufactureSchematicObjectTemplate) baseData;
			return base.getAttributesCount();
		}

		int count = attributes.size();

		if (attributesAppend && baseData != null) {
			final ServerManufactureSchematicObjectTemplate base = (ServerManufactureSchematicObjectTemplate) baseData;
			count += base.getAttributesCount();
		}

		return count;
	}

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SERVERMANUFACTURESCHEMATICOBJECTTEMPLATE) {
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

			if ("draftSchematic".equalsIgnoreCase(parameterName)) {
				draftSchematic.loadFromIff(objectTemplateList, iff);
			} else if ("creator".equalsIgnoreCase(parameterName)) {
				creator.loadFromIff(objectTemplateList, iff);
			} else if ("ingredients".equalsIgnoreCase(parameterName)) {
				ingredients.clear();
				ingredientsAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
					newData.loadFromIff(objectTemplateList, iff);
					ingredients.add(newData);
				}
				ingredientsLoaded = true;
			} else if ("itemCount".equalsIgnoreCase(parameterName)) {
				itemCount.loadFromIff(objectTemplateList, iff);
			} else if ("attributes".equalsIgnoreCase(parameterName)) {
				attributes.clear();
				attributesAppend = iff.readBoolean();
				int listCount = iff.readInt();
				for (int j = 0; j < listCount; ++j) {
					final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
					newData.loadFromIff(objectTemplateList, iff);
					attributes.add(newData);
				}
				attributesLoaded = true;
			} else {
				throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
			}

			iff.exitChunk();
		}
		iff.exitForm();

		super.load(iff);
		iff.exitForm();
	}

	public static class IngredientSlot {
		@Getter
		protected StringId name;
		@Getter
		protected Ingredient ingredient;
	}

	protected static class IngredientSlotObjectTemplate extends ObjectTemplate {
		public static final int TAG_INGREDIENTSLOT = Tag.convertStringToTag("MINS");

		public IngredientSlotObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
			super(filename, objectTemplateList);
		}

		private final StringIdParam name = new StringIdParam(); //slot name
		private final StructParam<ObjectTemplate> ingredient = new StructParam<ObjectTemplate>(); //ingredient used to fill the slot

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

		public Ingredient getIngredient() {
			IngredientSlotObjectTemplate base = null;

			if (baseData != null)
				base = (IngredientSlotObjectTemplate) baseData;

			if (!ingredient.isLoaded()) {
				if (base == null) {
					return null;
				} else {
					return base.getIngredient();
				}
			}

			final ObjectTemplate structTemplate = ingredient.getValue();
			Preconditions.checkNotNull(structTemplate);
			final IngredientObjectTemplate param = (IngredientObjectTemplate) structTemplate;

			final Ingredient data = new Ingredient();
			data.ingredientType = param.getIngredientType();
			for (int i = 0; i < param.getIngredientsCount(); ++i)
				data.ingredients.add(param.getIngredients(i));
			data.complexity = param.getComplexity();
			data.skillCommand = param.getSkillCommand();

			return data;
		}

		public Ingredient getIngredientMin() {
			IngredientSlotObjectTemplate base = null;

			if (baseData != null)
				base = (IngredientSlotObjectTemplate) baseData;

			if (!ingredient.isLoaded()) {
				if (base == null) {
					return null;
				} else {
					return base.getIngredientMin();
				}
			}

			final ObjectTemplate structTemplate = ingredient.getValue();
			Preconditions.checkNotNull(structTemplate);
			final IngredientObjectTemplate param = (IngredientObjectTemplate) structTemplate;

			final Ingredient data = new Ingredient();
			data.ingredientType = param.getIngredientType();
			for (int i = 0; i < param.getIngredientsCount(); ++i)
				data.ingredients.add(param.getIngredientsMin(i));
			data.complexity = param.getComplexityMin();
			data.skillCommand = param.getSkillCommand();

			return data;
		}

		public Ingredient getIngredientMax() {
			IngredientSlotObjectTemplate base = null;

			if (baseData != null)
				base = (IngredientSlotObjectTemplate) baseData;

			if (!ingredient.isLoaded()) {
				if (base == null) {
					return null;
				} else {
					return base.getIngredientMax();
				}
			}

			final ObjectTemplate structTemplate = ingredient.getValue();
			Preconditions.checkNotNull(structTemplate);
			final IngredientObjectTemplate param = (IngredientObjectTemplate) structTemplate;

			final Ingredient data = new Ingredient();
			data.ingredientType = param.getIngredientType();
			for (int i = 0; i < param.getIngredientsCount(); ++i)
				data.ingredients.add(param.getIngredientsMax(i));
			data.complexity = param.getComplexityMax();
			data.skillCommand = param.getSkillCommand();

			return data;
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

				if ("name".equalsIgnoreCase(parameterName)) {
					name.loadFromIff(objectTemplateList, iff);
				} else if ("ingredient".equalsIgnoreCase(parameterName)) {
					ingredient.loadFromIff(objectTemplateList, iff);
				} else {
					throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
				}

				iff.exitChunk();
			}
			iff.exitForm();
		}

	}

}


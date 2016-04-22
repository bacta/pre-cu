package com.ocdsoft.bacta.swg.precu.object.template.server;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.localization.StringId;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.template.ObjectTemplateList;
import com.ocdsoft.bacta.swg.utility.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
public class ServerIntangibleObjectTemplate extends ServerObjectTemplate {
    private static final int TAG_SERVERINTANGIBLEOBJECTTEMPLATE = Tag.convertStringToTag("0000");

    private int templateVersion;

    //
    // shared info needed by draft and manufacturing schematics
    //
    // this enum list must be reflected in MessageQueueIngredients.h
    private final IntegerParam count = new IntegerParam(); //generic counter

    public ServerIntangibleObjectTemplate(final String filename, final ObjectTemplateList objectTemplateList) {
        super(filename, objectTemplateList);
    }

    @Override
    public int getId() {
        return TAG_SERVERINTANGIBLEOBJECTTEMPLATE;
    }

    public int getCount() {
        ServerIntangibleObjectTemplate base = null;

        if (baseData != null)
            base = (ServerIntangibleObjectTemplate) baseData;

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

    @Override
    protected void load(final Iff iff) {
        if (iff.getCurrentName() != TAG_SERVERINTANGIBLEOBJECTTEMPLATE)
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
                count.loadFromIff(objectTemplateList, iff);
            } else {
                throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
            }

            iff.exitChunk();
        }
        iff.exitForm();
    }

    public enum IngredientType {
        IT_none(0), //no item (for empty/unused slots)
        IT_item(1), //a specific item (Skywalker barrel mark V)
        IT_template(2), //any item created from a template (any small_blaster_barrel)
        IT_resourceType(3), //a specific resource type (iron type 5)
        IT_resourceClass(4), //any resource of a resource class (ferrous metal)
        IT_templateGeneric(5), //same as IT_template, but if multiple components are required, they don't have to be the same exact type as the first component used
        IT_schematic(6), //item crafted from a draft schematic
        IT_schematicGeneric(7); //same as IT_schematic, but if multiple components are required, they don't have to be the same exact type as the first component used

        private static final IngredientType[] values = values();
        public final long value;

        IngredientType(final long value) {
            this.value = value;
        }

        public static IngredientType from(final long value) {
            for (final IngredientType e : values)
                if (e.value == value) return e;
            throw new IllegalArgumentException(String.format("UNKNOWN value %d for enum IngredientType", value));
        }
    }

    public static class SimpleIngredient {
        @Getter
        protected StringId name;
        @Getter
        protected String ingredient;
        @Getter
        protected int count;
    }

    protected static class SimpleIngredientObjectTemplate extends ObjectTemplate {
        private static final int TAG_SIMPLEINGREDIENT = Tag.convertStringToTag("SING");

        public SimpleIngredientObjectTemplate(final String filename, final ObjectTemplateList objectTemplateList) {
            super(filename, objectTemplateList);
        }

        private final StringIdParam name = new StringIdParam(); //name of ingredient that is given to players
        private final StringParam ingredient = new StringParam(); //name of ingredient used to match the resource/component name
        private final IntegerParam count = new IntegerParam(); //number of ingredients

        @Override
        public int getId() {
            return TAG_SIMPLEINGREDIENT;
        }

        public StringId getName() {
            SimpleIngredientObjectTemplate base = null;

            if (baseData != null)
                base = (SimpleIngredientObjectTemplate) baseData;

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

        public String getIngredient() {
            SimpleIngredientObjectTemplate base = null;

            if (baseData != null)
                base = (SimpleIngredientObjectTemplate) baseData;

            if (!ingredient.isLoaded()) {
                if (base == null) {
                    return "";
                } else {
                    return base.getIngredient();
                }
            }

            String value = this.ingredient.getValue();
            return value;
        }

        public int getCount() {
            SimpleIngredientObjectTemplate base = null;

            if (baseData != null)
                base = (SimpleIngredientObjectTemplate) baseData;

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
                    ingredient.loadFromIff(objectTemplateList, iff);
                } else if ("	".equalsIgnoreCase(parameterName)) {
                    count.loadFromIff(objectTemplateList, iff);
                } else {
                    throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
                }

                iff.exitChunk();
            }
            iff.exitForm();
        }

    }

    public static class Ingredient {
        @Getter
        protected IngredientType ingredientType;
        protected List<SimpleIngredient> ingredients = new ArrayList<>(1);
        @Getter
        protected float complexity;
        @Getter
        protected String skillCommand;
    }

    protected static class IngredientObjectTemplate extends ObjectTemplate {
        private static final int TAG_INGREDIENT = Tag.convertStringToTag("INGR");

        public IngredientObjectTemplate(final String filename, final ObjectTemplateList objectTemplateList) {
            super(filename, objectTemplateList);
        }

        private final IntegerParam ingredientType = new IntegerParam(); //type of ingredient required
        private final List<StructParam<ObjectTemplate>> ingredients = new ArrayList<>(); //ingredients to be used/being used
        private boolean ingredientsLoaded;
        private boolean ingredientsAppend;
        private final FloatParam complexity = new FloatParam(); //adjustment to complexity by using this ingredient
        private final StringParam skillCommand = new StringParam(); //skill command needed to use this ingredient

        @Override
        public int getId() {
            return TAG_INGREDIENT;
        }

        public IngredientType getIngredientType() {
            IngredientObjectTemplate base = null;

            if (baseData != null)
                base = (IngredientObjectTemplate) baseData;

            if (!ingredientType.isLoaded()) {
                if (base == null) {
                    return IngredientType.from(0);
                } else {
                    return base.getIngredientType();
                }
            }

            return IngredientType.from(ingredientType.getValue());
        }

        public SimpleIngredient getIngredients(int index) {
            IngredientObjectTemplate base = null;

            if (baseData != null)
                base = (IngredientObjectTemplate) baseData;

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
            final SimpleIngredientObjectTemplate param = (SimpleIngredientObjectTemplate) structTemplate;

            final SimpleIngredient data = new SimpleIngredient();
            data.name = param.getName();
            data.ingredient = param.getIngredient();
            data.count = param.getCount();

            return data;
        }

        public int getIngredientsCount() {
            if (!ingredientsLoaded) {
                if (baseData == null)
                    return 0;

                final IngredientObjectTemplate base = (IngredientObjectTemplate) baseData;
                return base.getIngredientsCount();
            }

            int count = ingredients.size();

            if (ingredientsAppend && baseData != null) {
                final IngredientObjectTemplate base = (IngredientObjectTemplate) baseData;
                count += base.getIngredientsCount();
            }

            return count;
        }

        public float getComplexity() {
            IngredientObjectTemplate base = null;

            if (baseData != null)
                base = (IngredientObjectTemplate) baseData;

            if (!complexity.isLoaded()) {
                if (base == null) {
                    return 0.0f;
                } else {
                    return base.getComplexity();
                }
            }

            float value = this.complexity.getValue();
            final byte delta = this.complexity.getDeltaType();

            if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
                float baseValue = 0;

                if (baseData != null) {
                    if (base != null)
                        baseValue = base.getComplexity();
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

        public String getSkillCommand() {
            IngredientObjectTemplate base = null;

            if (baseData != null)
                base = (IngredientObjectTemplate) baseData;

            if (!skillCommand.isLoaded()) {
                if (base == null) {
                    return "";
                } else {
                    return base.getSkillCommand();
                }
            }

            String value = this.skillCommand.getValue();
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
                    ingredientType.loadFromIff(objectTemplateList, iff);
                } else if ("	".equalsIgnoreCase(parameterName)) {
                    ingredients.clear();
                    ingredientsAppend = iff.readBoolean();
                    int listCount = iff.readInt();
                    for (int j = 0; j < listCount; ++j) {
                        final StructParam<ObjectTemplate> newData = new StructParam<ObjectTemplate>();
                        newData.loadFromIff(objectTemplateList, iff);
                        ingredients.add(newData);
                    }
                    ingredientsLoaded = true;
                } else if ("	".equalsIgnoreCase(parameterName)) {
                    complexity.loadFromIff(objectTemplateList, iff);
                } else if ("	".equalsIgnoreCase(parameterName)) {
                    skillCommand.loadFromIff(objectTemplateList, iff);
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
        protected int value;
    }

    protected static class SchematicAttributeObjectTemplate extends ObjectTemplate {
        private static final int TAG_SCHEMATICATTRIBUTE = Tag.convertStringToTag("ITAT");

        public SchematicAttributeObjectTemplate(final String filename, final ObjectTemplateList objectTemplateList) {
            super(filename, objectTemplateList);
        }

        private final StringIdParam name = new StringIdParam(); //name of the attribute
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

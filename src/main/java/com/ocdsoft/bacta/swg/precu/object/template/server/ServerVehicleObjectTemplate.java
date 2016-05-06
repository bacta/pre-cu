package com.ocdsoft.bacta.swg.precu.object.template.server;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.shared.foundation.DataResourceList;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.shared.template.definition.TemplateDefinition;
import com.ocdsoft.bacta.swg.shared.utility.FloatParam;
import com.ocdsoft.bacta.swg.shared.utility.StringParam;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
@TemplateDefinition
public class ServerVehicleObjectTemplate extends ServerTangibleObjectTemplate {
	public static final int TAG_SERVERVEHICLEOBJECTTEMPLATE = Tag.convertStringToTag("VEHO");

	private int templateVersion;

	private final StringParam fuelType = new StringParam(); //type of fuel used
	private final FloatParam currentFuel = new FloatParam(); //current amount of fuel the vehicle has
	private final FloatParam maxFuel = new FloatParam(); //max amount of fuel the vehicle can hold
	private final FloatParam consumpsion = new FloatParam(); //units/sec/speed(?) fuel used

	public ServerVehicleObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SERVERVEHICLEOBJECTTEMPLATE;
	}

	public String getFuelType() {
		ServerVehicleObjectTemplate base = null;

		if (baseData != null)
			base = (ServerVehicleObjectTemplate) baseData;

		if (!fuelType.isLoaded()) {
			if (base == null) {
				return "";
			} else {
				return base.getFuelType();
			}
		}

		String value = this.fuelType.getValue();
		return value;
	}

	public float getCurrentFuel() {
		ServerVehicleObjectTemplate base = null;

		if (baseData != null)
			base = (ServerVehicleObjectTemplate) baseData;

		if (!currentFuel.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getCurrentFuel();
			}
		}

		float value = this.currentFuel.getValue();
		final byte delta = this.currentFuel.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getCurrentFuel();
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

	public float getMaxFuel() {
		ServerVehicleObjectTemplate base = null;

		if (baseData != null)
			base = (ServerVehicleObjectTemplate) baseData;

		if (!maxFuel.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getMaxFuel();
			}
		}

		float value = this.maxFuel.getValue();
		final byte delta = this.maxFuel.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getMaxFuel();
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

	public float getConsumpsion() {
		ServerVehicleObjectTemplate base = null;

		if (baseData != null)
			base = (ServerVehicleObjectTemplate) baseData;

		if (!consumpsion.isLoaded()) {
			if (base == null) {
				return 0.0f;
			} else {
				return base.getConsumpsion();
			}
		}

		float value = this.consumpsion.getValue();
		final byte delta = this.consumpsion.getDeltaType();

		if (delta == '+' || delta == '-' || delta == '_' || delta == '=') {
			float baseValue = 0;

			if (baseData != null) {
				if (base != null)
					baseValue = base.getConsumpsion();
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
		if (iff.getCurrentName() != TAG_SERVERVEHICLEOBJECTTEMPLATE)
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
				fuelType.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				currentFuel.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				maxFuel.loadFromIff(objectTemplateList, iff);
			} else if ("".equalsIgnoreCase(parameterName)) {
				consumpsion.loadFromIff(objectTemplateList, iff);
			} else {
				throw new IllegalStateException(String.format("Unexpected parameter %s", parameterName));
			}

			iff.exitChunk();
		}
		iff.exitForm();
	}

}


package com.ocdsoft.bacta.swg.server.object.template.server;

import bacta.iff.Iff;
import com.google.common.base.Preconditions;
import com.ocdsoft.bacta.swg.shared.foundation.DataResourceList;
import com.ocdsoft.bacta.swg.shared.foundation.Tag;
import com.ocdsoft.bacta.swg.shared.template.ObjectTemplate;
import com.ocdsoft.bacta.swg.shared.template.definition.TemplateDefinition;
import com.ocdsoft.bacta.swg.shared.utility.StringParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generated by the TemplateDefinitionWriter.
 * MANUAL MODIFICATIONS MAY BE OVERWRITTEN.
 */
@TemplateDefinition
public class ServerShipObjectTemplate extends ServerTangibleObjectTemplate {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerShipObjectTemplate.class);
	public static final int TAG_SERVERSHIPOBJECTTEMPLATE = Tag.convertStringToTag("SHIP");

	private static void registerTemplateConstructors(final DataResourceList<ObjectTemplate> objectTemplateList) {
		objectTemplateList.registerTemplate(ServerShipObjectTemplate.TAG_SERVERSHIPOBJECTTEMPLATE, ServerShipObjectTemplate::new);
	}

	private int templateVersion;

	private final StringParam shipType = new StringParam(); 

	public ServerShipObjectTemplate(final String filename, final DataResourceList<ObjectTemplate> objectTemplateList) {
		super(filename, objectTemplateList);
	}

	@Override
	public int getId() {
		return TAG_SERVERSHIPOBJECTTEMPLATE;
	}

	public String getShipType() {
		ServerShipObjectTemplate base = null;

		if (baseData instanceof ServerShipObjectTemplate)
			base = (ServerShipObjectTemplate) baseData;

		if (!shipType.isLoaded()) {
			if (base == null) {
				return "";
			} else {
				return base.getShipType();
			}
		}

		String value = this.shipType.getValue();
		return value;
	}

	@Override
	protected void load(final Iff iff) {
		if (iff.getCurrentName() != TAG_SERVERSHIPOBJECTTEMPLATE) {
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

			if ("shipType".equalsIgnoreCase(parameterName)) {
				shipType.loadFromIff(objectTemplateList, iff);
			} else {
				LOGGER.error("Unexpected parameter {}", parameterName);
			}

			iff.exitChunk();
		}
		iff.exitForm();

		super.load(iff);
		iff.exitForm();
	}

}

package com.hocaron.customannotation.masking;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

import lombok.Getter;

@Getter
public class MaskingPropertySerializer extends StdSerializer<String> implements ContextualSerializer {

	private MaskingType maskingType;

	protected MaskingPropertySerializer() {
		super(String.class);
	}

	protected MaskingPropertySerializer(MaskingType maskingType) {
		super(String.class);
		this.maskingType = maskingType;
	}

	@Override
	public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {

		jsonGenerator.writeString(maskingType.getMasking().apply(value));
	}

	@Override
	public JsonSerializer<String> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
		MaskingType maskingTypeValue = null;
		MaskingRequired maskingAnnotation = null;

		if (beanProperty != null) {
			maskingAnnotation = beanProperty.getAnnotation(MaskingRequired.class);
		}
		if (maskingAnnotation != null) {
			maskingTypeValue = maskingAnnotation.type();
		}
		return new MaskingPropertySerializer(maskingTypeValue);
	}
}

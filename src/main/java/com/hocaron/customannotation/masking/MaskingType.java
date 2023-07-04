package com.hocaron.customannotation.masking;

import java.util.function.Function;

import lombok.Getter;

@Getter
public enum MaskingType {
	NONE(NoneMaskingFunction.INSTANCE),
	ID(IdMaskingFunction.INSTANCE),
	MOBILE(MobileMaskingFunction.INSTANCE),
	;

	private final Function<String, String> masking;

	MaskingType(Function<String, String> masking) {
		this.masking = masking;
	}

	private enum NoneMaskingFunction implements Function<String, String> {
		INSTANCE;

		@Override
		public String apply(String plainText) {
			return plainText;
		}
	}

	private enum IdMaskingFunction implements Function<String, String> {
		INSTANCE;

		@Override
		public String apply(final String plainText) {
			return plainText.replaceFirst("(.{3})$", "***");
		}
	}

	private enum MobileMaskingFunction implements Function<String, String> {
		INSTANCE;

		@Override
		public String apply(final String plainText) {
			return plainText.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
	}
}

package com.hocaron.customannotation.masking;

import lombok.Setter;

@Setter
public class MaskingTestField {

	@MaskingRequired(type = MaskingType.ID)
	private String id;

	@MaskingRequired(type = MaskingType.MOBILE)
	private String mobile;
}

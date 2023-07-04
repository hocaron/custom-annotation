package com.hocaron.customannotation.masking;

import lombok.Setter;

@Setter
public class MaskingTestField {

	@Masked(type = MaskingType.ID)
	private String id;

	@Masked(type = MaskingType.MOBILE)
	private String mobile;
}

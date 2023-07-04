package com.hocaron.customannotation.masking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

class MaskingTest {

	@Test
	@DisplayName("아아디 뒤에 3자리가 마스킹이 되어야 한다")
	public void testMaskingId() throws JsonProcessingException {
		MaskingTestField myObject = new MaskingTestField();
		myObject.setId("testId");

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(String.class, new MaskingPropertySerializer());
		mapper.registerModule(module);

		String json = mapper.writeValueAsString(myObject);
		Assertions.assertThat(json).isEqualTo("{\"id\":\"tes***\",\"mobile\":null}");
	}

	@Test
	@DisplayName("핸드폰 중간 4자리가 마스킹이 되어야 한다")
	public void testMaskingPhone() throws JsonProcessingException {
		MaskingTestField myObject = new MaskingTestField();
		myObject.setMobile("01012345678");

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(String.class, new MaskingPropertySerializer());
		mapper.registerModule(module);

		String json = mapper.writeValueAsString(myObject);
		Assertions.assertThat(json).isEqualTo("{\"id\":null,\"mobile\":\"010****5678\"}");
	}
}

package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/random")
public class RandomDataEndpoint {

	@Autowired
	private ObjectMapper objectMapper;

	
	@GetMapping("/changeSIM")
	public JsonNode getRandomChangeSIMRequest() {

		Faker faker = new Faker(new Locale("en-US"), new RandomService());
		
		ObjectNode  changeSIM = objectMapper.createObjectNode();
		ObjectNode header = objectMapper.createObjectNode();
		ObjectNode memoSource = objectMapper.createObjectNode();

		//set header
		header.put("partnerId", "POS");
		header.put("partnerTrnasactionId", "POS".concat(faker.regexify("[1-9]{20}")));
		header.put("partnerTimeStamp", faker.regexify("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})"));
		header.put("interactionId", faker.regexify("[0-9]{8}-[0-9]{4}-[A-Z1-9]{4}-[A-Z1-9]{4}-[A-Z1-9]{12}"));

		//set memoSource
		
		memoSource.put("memoSource", faker.regexify("[1-9]{1}"));
		memoSource.put("department", "RETAIL");
		memoSource.put("storeld", faker.regexify("[1-9]{4}"));
		memoSource.put("dealerCode", faker.regexify("[1-9]{7}"));
		
		changeSIM.set("header", header);
		changeSIM.put("accountMode", "POSTPAID");
		changeSIM.put("postPaidAccount", faker.numerify("99#####3232"));
		changeSIM.put("productType", faker.numerify("#M"));
		changeSIM.put("oldSim", faker.regexify("[1-9]{16}"));
		changeSIM.put("msisdn", faker.regexify("[1-9]{12}"));
		changeSIM.put("newSim",faker.regexify("[1-9]{16}"));
		changeSIM.put("newlmsi", faker.regexify("[1-9]{16}"));
		changeSIM.put("reasonCode", "DES");
		changeSIM.set("memoSource", memoSource);

		return changeSIM;
	}

	
	
	
}

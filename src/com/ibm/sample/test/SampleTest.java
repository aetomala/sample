package com.ibm.sample.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

import com.ibm.sample.Sample;
import com.ibm.sample.SampleException;

public class SampleTest {
	
	static final String postUrl = "https://jsonplaceholder.typicode.com/posts";
	
	static final String sample = "[\n" + 
			"   { \"email\": \"aetomala@us.ibm.com\", \"roles\": [\"Viewer\"] },\n" + 
			"   { \"email\": \"aetomala@us.ibm.com\", \"roles\": [\"Reader\"] },\n" + 
			"   { \"email\": \"dademarc@us.ibm.com\", \"roles\": [\"Viewer\"] },\n" + 
			"   { \"email\": \"dademarc@us.ibm.com\", \"roles\": [\"Reader\"] },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Manager\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Writer\", \"Manager\", \"Reader\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Editor\", \"Operator\", \"Viewer\", \"Manager\"]\n" + 
			"   },\n" + 
			"   { \"email\": \"lbc@ibm.com\", \"roles\": [\"Editor\", \"Viewer\"] },\n" + 
			"   { \"email\": \"lbc@ibm.com\", \"roles\": [\"Writer\", \"Reader\"] },\n" + 
			"   { \"email\": \"rdejana@us.ibm.com\", \"roles\": [\"Manager\"] },\n" + 
			"   {\n" + 
			"     \"email\": \"rdejana@us.ibm.com\",\n" + 
			"     \"roles\": [\"Manager\", \"Writer\", \"Reader\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"rdejana@us.ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Editor\", \"Operator\", \"Viewer\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"robert.herley@ibm.com\",\n" + 
			"     \"roles\": [\"Writer\", \"Manager\", \"Reader\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"robert.herley@ibm.com\",\n" + 
			"     \"roles\": [\"Viewer\", \"Manager\", \"Administrator\", \"Editor\", \"Operator\"]\n" + 
			"   },\n" + 
			"   { \"email\": \"sherwin.yu@us.ibm.com\", \"roles\": [\"Manager\"] },\n" + 
			"   {\n" + 
			"     \"email\": \"sherwin.yu@us.ibm.com\",\n" + 
			"     \"roles\": [\"Reader\", \"Writer\", \"Manager\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"sherwin.yu@us.ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Editor\", \"Operator\", \"Viewer\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"william.arnold@ibm.com\",\n" + 
			"     \"roles\": [\"Manager\", \"Reader\", \"Writer\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"william.arnold@ibm.com\",\n" + 
			"     \"roles\": [\"Manager\", \"Administrator\", \"Editor\", \"Operator\", \"Viewer\"]\n" + 
			"   }\n" + 
			" ]";
	static final String badSample = "[\n" + 
			"   { \"email\": \"aetomala@us.ibm.com\", \"roles\": [\"Viewer\"] },\n" + 
			"   { \"email\": \"aetomala@us.ibm.com\", \"roles\": [\"Reader\"] },\n" + 
			"   { \"email\": \"dademarc@us.ibm.com\", \"roles\": [\"Viewer\"] },\n" + 
			"   { \"email\": \"dademarc@us.ibm.com\", \"roles\": [\"Reader\"] },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Manager\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Writer\", \"Manager\", \"Reader\"]\n" + 
			"   },\n" + 
			"   {\n" + 
			"     \"email\": \"daljit.sharma@ibm.com\",\n" + 
			"     \"roles\": [\"Administrator\", \"Editor\", \"Operator\", \"Viewer\", \"Manager\"]\n" + 
			"   },\n" + 
			"   {\n "+
			" ]";

	@Test
	public void collectTest() {
		JSONParser parser = new JSONParser();
		JSONArray result = null;

		try {
			result = (JSONArray) parser.parse(Sample.makeacall(postUrl));
		}catch (ParseException e){
			e.printStackTrace();
		}

		assertNotNull  (result );
		
		assertEquals (100,result.size());
		
	}
	
	@Test
	public void convertTest() {
		JSONParser parser = new JSONParser();
		JSONObject result = null;
		
		try {
			result = (JSONObject) parser.parse(Sample.convert(sample));
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertNotNull (result);
		
		JSONArray roles = (JSONArray)((JSONObject) result.get("robert.herley@ibm.com")).get("roles");

		assertEquals (7,roles.size());
	}
	
	@Test(expected = SampleException.class)
	public void convertBadTest() throws ParseException, SampleException{
		JSONParser parser = new JSONParser();
		JSONObject result = null;
		try {
			result = (JSONObject) parser.parse(Sample.convert(badSample));
		}catch (SampleException e) {
			assertThat(e.getCause(), instanceOf(ParseException.class));
			throw e;
		}

	}
}

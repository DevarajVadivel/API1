package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	static RequestSpecification reqSpec ;
	static Response response;

	
	public void basicAuth(String username,String password) {

		reqSpec.auth().preemptive().basic(username, password);
	}
	
	
	public static void given() {
		reqSpec = RestAssured.given();
	}
	
	public String getPropertyFileValue(String key) throws IOException {
		Properties properties=new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir")+"//config.properties"));
		String  value=(String)properties.get(key);
		return value;
	}	
	
	
	
	
	public static void addHeader(String key,String value) {

		reqSpec = RestAssured.given().header(key,value);

	}

	public void addHeaders(List<Header>h) {
		Headers headers=new Headers(h);
		reqSpec =RestAssured.given().headers(headers);
		
	}


	public static void pathParam(String key,String value) {
		reqSpec=reqSpec.pathParam(key, value);

	}


	public static void queryPath(String key,String value) {

		reqSpec=reqSpec.queryParam(key, value);
	}

	public static void addBody(String payload) {

		reqSpec=reqSpec.body(payload);

	}
	public static void addBody(Object payload) {
		
		reqSpec=reqSpec.body(payload);
		
	}

	public static Response requestType(String reqType,String endpoint) {
		switch (reqType) {
		case "GET":
			response = reqSpec.get(endpoint);
			break;

		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;

		case "PUT":
			response = reqSpec.put(endpoint);
			break;

		case "DELETE":
			response = reqSpec.delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public static int getStatusCode(Response response) {

		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public static  ResponseBody getResBody(Response response) {
		ResponseBody body = response.getBody();
		return body;

	}

	public static String resBodyAsString(Response response) {
		String asString = getResBody( response).asString();
		return asString;
		
	}
	public static String resBodyAsPretty(Response response) {
		String prettyString = getResBody( response).asPrettyString();
		return prettyString;
		
		
	}
	
}

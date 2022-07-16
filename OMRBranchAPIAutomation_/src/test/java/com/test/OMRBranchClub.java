
package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.AddAddress_Input_pojo;
import com.pojo.AddAddress_Output_pojo;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.Login_Output_pojo;
import com.pojo.UpdateAddress_Input_pojo;
import com.pojo.UpdateAddress_Output_pojo;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {

	String logtoken;
	String addressId;

	@Test(priority = 1)
	public void Login() throws IOException {
		given();
		addHeader("accept", "application/json");
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

		Response response = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);

		System.out.println(response.statusCode());

		Login_Output_pojo as = response.as(Login_Output_pojo.class);
		System.out.println(as.getMessage());
		System.out.println(as.getData().getApi_key());

		Assert.assertEquals(as.getMessage(), "Login successfully", "Verify login successfully");

		Assert.assertEquals(as.getData().getFirst_name(), "Dharma");

		logtoken = as.getData().getLogtoken();

	}

//	@Test(priority = 2)
//	public void addAddress() {
//
//		List<Header> head = new ArrayList<Header>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Authorization", "Bearer " + logtoken);
//		Header h3 = new Header("Content-Type", "application/json");
//		head.add(h1);
//		head.add(h2);
//		head.add(h3);
//		addHeaders(head);
//
//		AddAddress_Input_pojo input_pojo = new AddAddress_Input_pojo("Dharma", "Prakash", "6377388747", "001", 33, 3378,
//				101, "600097", "Komarapalayam", "Home");
//		addBody(input_pojo);
//
//		Response response = requestType("POST", Endpoints.ADDADDRESS);
//
//		AddAddress_Output_pojo as = response.as(AddAddress_Output_pojo.class);
//
//		int id = as.getAddress_id();
//		addressId = String.valueOf(id);
//
//		System.out.println(getStatusCode(response));
//		Assert.assertEquals(as.getMessage(), "Address added successfully", "Verify Add address message");
//
//	}
//
//	@Test(priority = 3)
//	public void updateAddress() {
//
//		List<Header> head = new ArrayList<Header>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Authorization", "Bearer " + logtoken);
//		Header h3 = new Header("Content-Type", "application/json");
//		head.add(h1);
//		head.add(h2);
//		head.add(h3);
//		addHeaders(head);
//
//		UpdateAddress_Input_pojo address_Input_pojo = new UpdateAddress_Input_pojo(addressId, "Raja", "Ram",
//				"8760836156", "001", 33, 3378, 101, "600097", "Komarapalayam", "Home");
//
//		addBody(address_Input_pojo);
//
//		Response response = requestType("PUT", Endpoints.UPDATEADDRESS);
//
//		System.out.println(getStatusCode(response));
//		Assert.assertEquals(getStatusCode(response), 200, "Verify status code ");
//
//		UpdateAddress_Output_pojo as = response.as(UpdateAddress_Output_pojo.class);
//
//		Assert.assertEquals(as.getMessage(), "Address updated successfully", "Verify updated address message");
//
//	}
//
//	@Test(priority = 4)
//	public void deleteUser() {
//
//		List<Header> head = new ArrayList<Header>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Authorization", "Bearer " + logtoken);
//		Header h3 = new Header("Content-Type", "application/json");
//		head.add(h1);
//		head.add(h2);
//		head.add(h3);
//		addHeaders(head);
//
//		DeleteAddress_Input_pojo deleteAddress_Input_pojo = new DeleteAddress_Input_pojo(addressId);
//
//		addBody(deleteAddress_Input_pojo);
//
//		Response response = requestType("DELETE", Endpoints.DELETEADDRESS);
//
//		System.out.println(getStatusCode(response));
//
//		Assert.assertEquals(getStatusCode(response), 200, "Verify status code");
//		UpdateAddress_Output_pojo as = response.as(UpdateAddress_Output_pojo.class);
//
//		Assert.assertEquals(as.getMessage(), "Address deleted successfully", "Verify deleted address message");
//
//	}

}

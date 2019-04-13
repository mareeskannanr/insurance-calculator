package com.element.coding.app;

import com.element.coding.app.model.Enquiry;
import com.element.coding.app.model.InsuranceType;
import com.element.coding.app.utils.AppConstants;
import com.element.coding.app.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	private static final String GET_ENQUIRIES_URL = "/api/enquiries";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void emptyEnquiryCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, "{}");
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_REQUIRED_ERROR));
		assertTrue(response.getContentAsString().contains(AppConstants.TYPE_REQUIRED_ERROR));
	}

	@Test
	public void invalidInsuranceTypeCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, "{\"insuranceType\" : \"error\", \"coverage\": \"30\"}");
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.INVALID_ENQUIRY_MSG));
	}

	//Low Coverage Check for Bike
	@Test
	public void lowCoverageCheckForBike() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.BIKE, -1));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.BIKE)));
	}

	//High Coverage check for Bike
	@Test
	public void lessCoverageCheckForBike() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.BIKE, 3001));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.BIKE)));
	}

	//Low Coverage check for Jewelry
	@Test
	public void lessCoverageCheckForJewelry() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.JEWELRY, 499));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.JEWELRY)));
	}

	//High Coverage check for Jewelry
	@Test
	public void highCoverageCheckForJewelry() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.JEWELRY, 10001));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.JEWELRY)));
	}

	//Low Coverage check for Electronics
	@Test
	public void lowCoverageCheckForElectronics() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.ELECTRONICS, 499));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.ELECTRONICS)));
	}

	//High Coverage check for Electronics
	@Test
	public void highCoverageCheckForElectronics() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.ELECTRONICS, 6001));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.ELECTRONICS)));
	}

	//Low Coverage Check for Sports Equipment
	@Test
	public void lowCoverageCheckForSportsEquipment() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.SPORTS_EQUIPMENT, 25000));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.SPORTS_EQUIPMENT)));
	}

	//High Coverage Check for Sports Equipment
	@Test
	public void highCoverageCheckForSportsEquipment() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.SPORTS_EQUIPMENT, 25000));
		assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
		assertTrue(response.getContentAsString().contains(AppConstants.COVERAGE_ERROR_MAP.get(InsuranceType.SPORTS_EQUIPMENT)));
	}

	@Test
	public void bikeCoverageResultCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.BIKE, 1000));
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(), "300");
	}

	@Test
	public void jewelryCoverageResultCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.JEWELRY, 10000));
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(), "500");
	}

	@Test
	public void electronicsCoverageResultCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.ELECTRONICS, 5000));
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(), "1750");
	}

	@Test
	public void sportEquipmentsCoverageResultCheck() throws Exception {
		MockHttpServletResponse response = TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.SPORTS_EQUIPMENT, 5000));
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(), "1500");
	}

	@Test
	public void getAllEnquiresEmptyCheck() throws Exception {
		MvcResult response =  mockMvc.perform(get(GET_ENQUIRIES_URL)).andReturn();
		assertEquals(response.getResponse().getStatus(), HttpStatus.OK.value());

		Enquiry[] enquiries = TestUtils.mapper.readValue(response.getResponse().getContentAsString(), Enquiry[].class);
		assertEquals(enquiries.length, 0);
	}

	@Test
	public void getAllEnquiresCheck() throws Exception {
		TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.SPORTS_EQUIPMENT, 5000));
		TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.ELECTRONICS, 5000));
		TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.JEWELRY, 10000));
		TestUtils.postEnquiry(mockMvc, TestUtils.createEnquiry(InsuranceType.BIKE, 1000));
		MvcResult response =  mockMvc.perform(get(GET_ENQUIRIES_URL)).andReturn();
		assertEquals(response.getResponse().getStatus(), HttpStatus.OK.value());

		Enquiry[] enquiries = TestUtils.mapper.readValue(response.getResponse().getContentAsString(), Enquiry[].class);
		assertTrue(enquiries.length > 4);
	}
}

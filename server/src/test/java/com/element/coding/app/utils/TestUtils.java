package com.element.coding.app.utils;

import com.element.coding.app.model.Enquiry;
import com.element.coding.app.model.InsuranceType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

public class TestUtils {

    private static final String POST_ENQUIRY_URL = "/api/enquiry";
    private static final Enquiry enquiry = new Enquiry();
    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Create Rest Call to make test enquiries
     * @param mockMvc
     * @param enquiry
     * @return
     * @throws Exception
     */
    public static MockHttpServletResponse postEnquiry(MockMvc mockMvc, String enquiry) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(POST_ENQUIRY_URL)
                .accept(MediaType.APPLICATION_JSON).content(enquiry)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    /**
     * Create Enquiry Json Object
     * @param type
     * @param coverage
     * @return
     * @throws Exception
     */
    public static String createEnquiry(InsuranceType type, Integer coverage) throws Exception {
        enquiry.setCreated(LocalDateTime.now());
        enquiry.setCoverage(coverage);
        enquiry.setInsuranceType(type);
        return mapper.writeValueAsString(enquiry);
    }

}

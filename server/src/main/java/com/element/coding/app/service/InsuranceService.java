package com.element.coding.app.service;

import com.element.coding.app.model.Enquiry;
import com.element.coding.app.model.InsuranceType;

import java.util.List;

public interface InsuranceService {

    Integer calculatePrice(Enquiry enquiry);

    List<Enquiry> fetchAllEnquires();
}
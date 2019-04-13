package com.element.coding.app.service;

import com.element.coding.app.model.Enquiry;
import com.element.coding.app.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private EnquiryRepository repository;

    @Override
    public Integer calculatePrice(Enquiry enquiry) {
        Integer price = enquiry.getCoverage() * enquiry.getInsuranceType().getRisk() / 100;
        enquiry.setPrice(price);
        repository.save(enquiry);
        return price;
    }

    @Override
    public List<Enquiry> fetchAllEnquires() {
        return repository.findAll();
    }
}
package com.element.coding.app.controller;

import com.element.coding.app.exception.AppException;
import com.element.coding.app.model.Enquiry;
import com.element.coding.app.service.InsuranceService;
import com.element.coding.app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AppRestCtrl {

    @Autowired
    private InsuranceService service;

    /**
     * Calculate Price for given coverange and insurance type
     * @param enquiry
     * @return
     * @throws AppException
     */
    @PostMapping("/enquiry")
    public ResponseEntity calculatePrice(@Valid @RequestBody Enquiry enquiry) throws AppException {
        CommonUtils.validateData(enquiry);
        return ResponseEntity.ok(service.calculatePrice(enquiry));
    }

    /***
     * Fetch all the requested enquiries from repositiory
     * @return
     */
    @GetMapping("/enquiries")
    public ResponseEntity getAllEnquiries() {
        return ResponseEntity.ok(service.fetchAllEnquires());
    }

}

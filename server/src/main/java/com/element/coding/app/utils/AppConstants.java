package com.element.coding.app.utils;

import com.element.coding.app.model.InsuranceType;

import java.util.HashMap;
import java.util.Map;

public class AppConstants {
    public static final String INTERNAL_SERVER_ERROR_MSG = "Sorry, something went wrong!";
    public static final String INVALID_ENQUIRY_MSG = "Enquiry contains invalid data.";
    public static final String TYPE_REQUIRED_ERROR = "Insurance Type is required";
    public static final String COVERAGE_REQUIRED_ERROR = "Coverage Value is required";
    public static final Map<InsuranceType, String> COVERAGE_ERROR_MAP = new HashMap<>();

    //Generating Coverage Error Messages for Insurance Types
    static {
        for (InsuranceType type : InsuranceType.values()) {
            String message = "Insurance Module " + type.toString() + " must have Coverage between " + type.getFrom() + " and " + type.getTo();
            COVERAGE_ERROR_MAP.put(type, message);
        }
    }

}
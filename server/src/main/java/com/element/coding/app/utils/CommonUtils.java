package com.element.coding.app.utils;

import com.element.coding.app.exception.AppException;
import com.element.coding.app.model.Enquiry;
import com.element.coding.app.model.InsuranceType;

public class CommonUtils {

    /**
     * Check Coverage Range for Each Insurance Type
     * @param enquiry
     * @throws AppException
     */
    public static void validateData(Enquiry enquiry) throws AppException {

        InsuranceType type = enquiry.getInsuranceType();
        if(!(type.getFrom() <= enquiry.getCoverage() && type.getTo() >= enquiry.getCoverage())) {
            throw new AppException(AppConstants.COVERAGE_ERROR_MAP.get(type));
        }

    }

}

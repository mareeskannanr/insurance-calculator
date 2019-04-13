package com.element.coding.app.model;

import com.element.coding.app.utils.AppConstants;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "enquiries")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "insurance_type")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = AppConstants.TYPE_REQUIRED_ERROR)
    private InsuranceType insuranceType;

    @NotNull(message = AppConstants.COVERAGE_REQUIRED_ERROR)
    private Integer coverage;

    private Integer price;

};
package com.element.coding.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Contains Insurance Type Available to users
 */
public enum InsuranceType {

    @JsonProperty("Bike")
    BIKE("Bike", 0, 3000, 30),

    @JsonProperty("Jewelry")
    JEWELRY("Jewelry", 500, 10000, 5),

    @JsonProperty("Electronics")
    ELECTRONICS("Electronics", 500, 6000, 35),

    @JsonProperty("Sports Equipment")
    SPORTS_EQUIPMENT("Sports Equipment", 0, 20000, 30);

    private final String name;
    private final Integer from;
    private final Integer to;
    private final Integer risk;

    InsuranceType(String name, Integer from, Integer to, Integer risk) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.risk = risk;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Integer getFrom() {
        return this.from;
    }

    public Integer getTo() {
        return this.to;
    }

    public Integer getRisk() {
        return this.risk;
    }

}

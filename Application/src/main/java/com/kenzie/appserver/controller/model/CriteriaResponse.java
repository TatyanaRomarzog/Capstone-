package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriteriaResponse {

    @JsonProperty("positionTitle")
    String positionTitle;

    @JsonProperty("locations")
    List<String> locations;

    @JsonProperty("minimumSalary")
    Integer minimumSalary;

    @JsonProperty("openJobsLimit")
    Integer openJobsLimit;

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Integer getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Integer minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Integer getOpenJobsLimit() {
        return openJobsLimit;
    }

    public void setOpenJobsLimit(Integer openJobsLimit) {
        this.openJobsLimit = openJobsLimit;
    }
}

package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//we could make unique exceptions for illegal argument inputs in the constructors, but that's not necessary
//this is just rough draft, some of this still might change, elise

public class Criteria {
    String positionTitle;
    List<String> locations;
    int minimumSalary;
    int openJobsLimit;

    public Criteria(String positionTitle) {
        if(positionTitle == null) {
            throw new NullPointerException("position title must not be null");
        }

        this.positionTitle = positionTitle;
        this.locations = Collections.emptyList();
        this.minimumSalary = 0;
        this.openJobsLimit = 100;
    }

    public Criteria(String positionTitle, List<String> locations, int minimumSalary, int openJobsLimit) {
        if(positionTitle == null || locations == null) {
            throw new NullPointerException("position title and locations must not be null");
        }
        if(minimumSalary < 0 || openJobsLimit < 0) {
            throw new RuntimeException("minimum salary or open jobs limit cannot be less than 0");
        }

        this.positionTitle = positionTitle;
        this.locations = locations;
        this.minimumSalary = minimumSalary;
        this.openJobsLimit = openJobsLimit;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public List<String> getLocations() {
        return new ArrayList<>(locations);
    }

    public int getMinimumSalary() {
        return minimumSalary;
    }

    public int getOpenJobsLimit() {
        return openJobsLimit;
    }
}

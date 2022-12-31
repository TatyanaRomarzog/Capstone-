package com.kenzie.appserver.service.model;

public class Resume {
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String phoneNumber;
    private String emailAddress;
    private String objective;
    private String education;
    private String experience;
    private String skills;

    public Resume(String firstName, String lastName, String homeAddress, String phoneNumber,
                  String emailAddress, String objective, String education, String experience, String skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.objective = objective;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public Resume(Resume resume) {
        this.firstName = resume.getFirstName();
        this.lastName = resume.getLastName();
        this.homeAddress = resume.getHomeAddress();
        this.phoneNumber = resume.getPhoneNumber();
        this.emailAddress = resume.getEmailAddress();
        this.objective = resume.getObjective();
        this.education = resume.getEducation();
        this.experience = resume.getExperience();
        this.skills = resume.getSkills();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getObjective() {
        return objective;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }
}





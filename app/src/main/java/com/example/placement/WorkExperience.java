package com.example.placement;

/** Student-entered work or internship record stored in the current demo session. */
public class WorkExperience {
    public String company, role, startDate, endDate, description;
    public boolean internship;

    public WorkExperience(String company, String role, String startDate, String endDate, String description, boolean internship) {
        this.company = company;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.internship = internship;
    }
}

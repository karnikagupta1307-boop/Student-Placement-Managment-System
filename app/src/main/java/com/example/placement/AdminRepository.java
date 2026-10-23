package com.example.placement;

import java.util.List;

/** Data boundary for administrative features; replace the demo implementation with an API repository. */
public interface AdminRepository {
    List<String[]> students();
    List<String[]> drives();
    List<String[]> applications();
    List<String[]> companies();
    void addCompany(String name, String industry, String location, String website, String recruiter, String contact);
    void updateCompany(int index, String name, String industry, String location, String website, String recruiter, String contact);
    void removeCompany(int index);
    void addDrive(String company, String role, String deadline);
    void saveDrive(int index, String[] fields);
    void setDriveStatus(int index, String status);
    void removeDrive(int index);
    void updateApplicationStatus(int index, String status);
    List<String[]> interviews();
    void addInterview(String[] interview);
    void updateInterviewStatus(int index, String status);
}

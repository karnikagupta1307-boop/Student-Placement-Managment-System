package com.example.placement;

import java.util.ArrayList;
import java.util.List;

/** Local presentation records only. These are not live university records. */
public final class AdminDemoRepository implements AdminRepository {
    private static final List<String[]> STUDENTS = new ArrayList<>();
    private static final List<String[]> DRIVES = DemoSession.drives;
    private static final List<String[]> APPLICATIONS = DemoSession.applications;
    private static final List<String[]> COMPANIES = new ArrayList<>();
    private static final List<String[]> INTERVIEWS = new ArrayList<>();

    static {
        STUDENTS.add(new String[]{"Aarav Sharma", "SAP2024001", "Computer Engineering", "8.2", "80%"});
        STUDENTS.add(new String[]{"Ananya Rao", "SAP2024002", "Information Technology", "8.7", "60%"});
        STUDENTS.add(new String[]{"Rohan Mehta", "SAP2024003", "Electronics", "7.6", "40%"});
        COMPANIES.add(new String[]{"Infosys", "Technology", "Hyderabad", "https://www.infosys.com", "Placement Team", "campus@example.com"});
        COMPANIES.add(new String[]{"TCS", "Technology", "Hyderabad", "https://www.tcs.com", "Campus Recruiter", "campus@example.com"});
        INTERVIEWS.add(new String[]{"Infosys", "Systems Engineer", "Technical Round", "28 Nov 2026 10:30 AM", "Online", "Sample instructions", "Scheduled"});
    }

    @Override public List<String[]> students() { return STUDENTS; }
    @Override public List<String[]> drives() { return DRIVES; }
    @Override public List<String[]> applications() { return APPLICATIONS; }
    @Override public List<String[]> companies() { return COMPANIES; }
    @Override public void addCompany(String name, String industry, String location, String website, String recruiter, String contact) { COMPANIES.add(new String[]{name, industry, location, website, recruiter, contact}); }
    @Override public void updateCompany(int index, String name, String industry, String location, String website, String recruiter, String contact) { if(index>=0&&index<COMPANIES.size()) COMPANIES.set(index,new String[]{name,industry,location,website,recruiter,contact}); }
    @Override public void removeCompany(int index) { if (index >= 0 && index < COMPANIES.size()) COMPANIES.remove(index); }
    @Override public void addDrive(String company, String role, String deadline) {
        DRIVES.add(new String[]{company, role, "To be announced", "Hyderabad", "0", "All branches", "", deadline,"Draft"});
    }
    @Override public void saveDrive(int index,String[] fields){if(index<0)addDrive(fields[0],fields[1],fields[7]);else if(index<DRIVES.size())DRIVES.set(index,fields);}
    @Override public void setDriveStatus(int index,String status){if(index>=0&&index<DRIVES.size()){String[] old=DRIVES.get(index);String[] updated=java.util.Arrays.copyOf(old,9);updated[8]=status;DRIVES.set(index,updated);}}
    @Override public void removeDrive(int index) { if (index >= 0 && index < DRIVES.size()) DRIVES.remove(index); }
    @Override public void updateApplicationStatus(int index, String status) {
        if (index >= 0 && index < APPLICATIONS.size()) APPLICATIONS.get(index)[3] = status;
    }
    @Override public List<String[]> interviews(){return INTERVIEWS;}
    @Override public void addInterview(String[] interview){INTERVIEWS.add(interview);}
    @Override public void updateInterviewStatus(int index,String status){if(index>=0&&index<INTERVIEWS.size())INTERVIEWS.get(index)[6]=status;}
}

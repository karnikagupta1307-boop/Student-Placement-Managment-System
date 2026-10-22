package com.example.placement;

import java.util.*;

/** In-memory presentation data. Replace these operations with repository calls when backend is added. */
public final class DemoSession {
    public static String name="Student", sapId="SAP2024001", email="student@college.edu", branch="Computer Engineering", year="Third Year";
    public static final List<String[]> drives=new ArrayList<>();
    public static final List<String[]> applications=new ArrayList<>();
    public static final Map<String,String> documents=new LinkedHashMap<>();
    /** Selected content URIs are stored as strings; never treat them as filesystem paths. */
    public static final Map<String,String> documentUris=new LinkedHashMap<>();
    public static final Map<String,StudentDocument> documentItems=new LinkedHashMap<>();
    public static final Map<String,String> profile=new HashMap<>();
    public static final List<Project> projects=new ArrayList<>();
    public static final List<Certification> certifications=new ArrayList<>();
    public static final Map<String,CodingProfile> codingProfiles=new LinkedHashMap<>();
    public static final Map<String,LinkedHashSet<String>> skills=new LinkedHashMap<>();
    public static final List<Achievement> achievements=new ArrayList<>();
    public static final Map<String,String> professionalLinks=new LinkedHashMap<>();
    public static String profilePhotoUri="", profilePhotoName="";
    public static boolean loggedIn=false;
    static {
        profile.put("Phone number","+91 98765 43210");profile.put("Date of birth","12 Aug 2004");profile.put("Address","Hyderabad, Telangana");
        profile.put("Institution","SVKM's NMIMS, Hyderabad Campus");profile.put("Programme","B.Tech");profile.put("Branch",branch);profile.put("Current semester","6");profile.put("CGPA","8.2");profile.put("Backlogs","0");profile.put("Class 10 percentage","91");profile.put("Class 12 percentage","88");profile.put("Graduation year","2027");
        drives.add(new String[]{"TCS","Graduate Engineer Trainee","7.0 LPA","Mumbai","3.0","All branches","Java, SQL","30 Nov 2026"});
        drives.add(new String[]{"Infosys","Systems Engineer","6.5 LPA","Pune","6.0","All branches","Java, Python","05 Dec 2026"});
        drives.add(new String[]{"Accenture","Associate Software Engineer","6.0 LPA","Bengaluru","6.0","CS, IT, ENTC","Communication, SQL","10 Dec 2026"});
        drives.add(new String[]{"Deloitte","Analyst","8.0 LPA","Hyderabad","7.0","CS, IT, E&TC","Problem solving","15 Dec 2026"});
        drives.add(new String[]{"Microsoft","Software Engineer Intern","18.0 LPA","Remote","8.0","CS, IT","DSA, Java","20 Dec 2026"});
        drives.add(new String[]{"Amazon","SDE Intern","20.0 LPA","Bengaluru","7.5","CS, IT","DSA, Python","22 Dec 2026"});
        applications.add(new String[]{"TCS","Graduate Engineer Trainee","PM-26001","Under Review","7.0 LPA"});
        applications.add(new String[]{"Infosys","Systems Engineer","PM-26002","Shortlisted","6.5 LPA"});
        applications.add(new String[]{"Accenture","Associate Software Engineer","PM-26003","Applied","6.0 LPA"});
    }
    public static int profileCompletion(){int done=0,total=10; if(personalComplete())done++;if(academicComplete())done++;if(!profilePhotoUri.isEmpty())done++;if(documents.containsKey("Resume/CV"))done++;if(!projects.isEmpty())done++;if(!certifications.isEmpty())done++;if(!skills.isEmpty()&&skills.values().stream().anyMatch(v->!v.isEmpty()))done++;if(codingProfiles.values().stream().anyMatch(v->v.url!=null&&!v.url.isEmpty()))done++;if(!achievements.isEmpty())done++;if(professionalLinks.values().stream().anyMatch(v->v!=null&&!v.isEmpty()))done++;return done*10;}
    public static boolean personalComplete(){return name!=null&&!name.trim().isEmpty()&&sapId!=null&&!sapId.trim().isEmpty()&&email!=null&&!email.trim().isEmpty()&&has("Phone number")&&has("Date of birth")&&has("Address");}
    public static boolean academicComplete(){return has("Institution")&&has("Programme")&&has("Branch")&&has("Current semester")&&has("CGPA")&&has("Backlogs")&&has("Class 10 percentage")&&has("Class 12 percentage")&&has("Graduation year");}
    public static boolean has(String key){String v=profile.get(key);return v!=null&&!v.trim().isEmpty();}
    public static String[] incompleteProfileSections(){return new String[]{personalComplete()?"":"Personal Information",academicComplete()?"":"Academic Information",profilePhotoUri.isEmpty()?"Profile Photograph":"",documents.containsKey("Resume/CV")?"":"Resume/CV",projects.isEmpty()?"Projects":"",certifications.isEmpty()?"Certifications":"",skills.values().stream().anyMatch(v->!v.isEmpty())?"":"Skills",codingProfiles.values().stream().anyMatch(v->v.url!=null&&!v.url.isEmpty())?"":"Coding Profiles",achievements.isEmpty()?"Achievements":"",professionalLinks.values().stream().anyMatch(v->v!=null&&!v.isEmpty())?"":"Professional Links"};}
    public static void syncWizardFields(){if(has("Full name"))name=profile.get("Full name");if(has("SAP ID"))sapId=profile.get("SAP ID");if(has("College email"))email=profile.get("College email");if(has("Branch"))branch=profile.get("Branch");if(has("Graduation year"))year=profile.get("Graduation year");}
    public static void logout(){loggedIn=false;name="Student";profile.clear();documents.clear();documentUris.clear();documentItems.clear();projects.clear();certifications.clear();codingProfiles.clear();skills.clear();achievements.clear();professionalLinks.clear();profilePhotoUri="";profilePhotoName="";applications.removeIf(a->a[2].startsWith("PM-DEMO"));}
    private DemoSession(){}
}

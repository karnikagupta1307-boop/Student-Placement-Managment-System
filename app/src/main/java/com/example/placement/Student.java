package com.example.placement;
public class Student {
 public String name,sapId,email,branch,year,phone,dateOfBirth,address,institution,programme,semester,cgpa,backlogs,graduationYear,photoUri;
 public Student(String n,String s,String e,String b,String y){name=n;sapId=s;email=e;branch=b;year=y;phone=DemoSession.profile.get("Phone number");dateOfBirth=DemoSession.profile.get("Date of birth");address=DemoSession.profile.get("Address");institution=DemoSession.profile.get("Institution");programme=DemoSession.profile.get("Programme");semester=DemoSession.profile.get("Current semester");cgpa=DemoSession.profile.get("CGPA");backlogs=DemoSession.profile.get("Backlogs");graduationYear=DemoSession.profile.get("Graduation year");photoUri=DemoSession.profilePhotoUri;}
}

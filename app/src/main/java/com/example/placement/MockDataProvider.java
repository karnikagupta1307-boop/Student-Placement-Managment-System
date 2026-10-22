package com.example.placement;
import java.util.List;
/** Central entry point for presentation records; replace with repositories when backend is integrated. */
public final class MockDataProvider {
 public static List<String[]> drives(){return DemoSession.drives;}
 public static List<String[]> applications(){return DemoSession.applications;}
 public static Student student(){return new Student(DemoSession.name,DemoSession.sapId,DemoSession.email,DemoSession.branch,DemoSession.year);}
 private MockDataProvider(){}
}

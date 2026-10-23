package com.example.placement;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class AdminDashboardActivity extends BaseActivity {
    @Override protected int layout() { return R.layout.activity_admin_dashboard; }

    @Override protected void render() {
        toolbar("Administrator Dashboard");
        scroll(false);
        body.addView(text("Placement office overview", 23, ink, true));
        body.addView(text("NMIMS Hyderabad · Administrative workspace", 14, muted, false));
        gap(body, 8);
        note(body, "DEMONSTRATION DATA · This panel is not connected to university records.");
        AdminRepository repo = new AdminDemoRepository();
        LinearLayout stats = column();
        stat(stats, "Students", String.valueOf(repo.students().size()));
        stat(stats, "Incomplete profiles", "2");
        stat(stats, "Active placement drives", String.valueOf(repo.drives().size()));
        stat(stats, "Applications", String.valueOf(repo.applications().size()));
        stat(stats, "Interviews scheduled", "2");
        stat(stats, "Offers", "1");
        body.addView(stats);
        heading(body, "Manage placement activity");
        section("Students", "Review student directory and profile completeness", "students");
        section("Companies", "Manage demonstration recruiter records", "companies");
        section("Placement drives", "Create or remove demonstration opportunities", "drives");
        section("Applications", "Review applicants and update sample statuses", "applications");
        section("Interviews and results", "View sample interview schedules and outcomes", "results");
        section("Reports", "Review placement summary statistics", "reports");
        section("Settings", "Review demonstration workspace details", "settings");
        heading(body, "Placement office updates");
        item(body, "Profile completion campaign", "Sample notice for the student panel", "DEMO", null);
        action(body, "Log Out", this::logout);
    }

    private void stat(LinearLayout list, String title, String value) {
        LinearLayout row = row();
        row.setPadding(dp(14), dp(12), dp(14), dp(12));
        row.addView(text(title, 14, ink, true), new LinearLayout.LayoutParams(0, -2, 1));
        row.addView(text(value, 20, blue, true));
        card(list, row);
    }

    private void section(String title, String description, String key) {
        item(body, title, description, "OPEN", () -> {
            Intent intent = new Intent(this, AdminManagementActivity.class).putExtra("section", key);
            startActivity(intent);
        });
    }
}

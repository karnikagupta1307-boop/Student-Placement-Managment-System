package com.example.placement;

import android.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.Locale;

public class AdminManagementActivity extends BaseActivity {
    private final AdminRepository repository = new AdminDemoRepository();
    private String section;

    @Override protected int layout() { return R.layout.activity_admin_management; }

    @Override protected void render() {
        section = getIntent().getStringExtra("section");
        if (section == null) section = "students";
        String title = section.equals("drives") ? "Placement Drives" : section.equals("applications") ? "Applications" : section.equals("results") ? "Interviews and Results" : section.equals("companies") ? "Companies" : section.equals("reports") ? "Reports" : section.equals("settings") ? "Settings" : "Students";
        toolbar(title);
        scroll(false);
        note(body, "Local demonstration records only. Changes are not sent to the placement office.");
        if (section.equals("students")) renderStudents();
        else if (section.equals("companies")) renderCompanies();
        else if (section.equals("drives")) renderDrives();
        else if (section.equals("applications")) renderApplications();
        else if (section.equals("reports")) renderReports();
        else if (section.equals("settings")) { item(body, "Administrator access", "Demo workspace only. No role authorization is connected.", "DEMO", null); action(body, "Log Out", this::logout); }
        else renderResults();
    }

    private void renderCompanies() {
        action(body, "Add Company", this::createCompany);
        for (int i = 0; i < repository.companies().size(); i++) {
            final int index = i;
            String[] company = repository.companies().get(i);
            item(body, company[0], company[1] + " · " + company[2] + "\n" + company[3] + "\nRecruiter: " + company[4] + " · " + company[5], "DEMO", () ->
                new AlertDialog.Builder(this).setTitle(company[0]).setItems(new String[]{"View details", "Edit company", "Delete company"}, (d, which) -> {
                    if(which==0)new AlertDialog.Builder(this).setTitle(company[0]).setMessage("Industry: "+company[1]+"\nLocation: "+company[2]+"\nWebsite: "+company[3]+"\nRecruiter: "+company[4]+"\nContact: "+company[5]).setPositiveButton("Close",null).show();
                    else if(which==1)companyForm(index,company);
                    else new AlertDialog.Builder(this).setTitle("Delete company?").setMessage("Remove this local demonstration company record?").setNegativeButton("Cancel", null).setPositiveButton("Delete", (x, w) -> { repository.removeCompany(index); recreate(); }).show();
                }).setNegativeButton("Cancel",null).show());
        }
        if (repository.companies().isEmpty()) note(body, "No demonstration companies yet. Add a company to get started.");
    }

    private void createCompany() { companyForm(-1,null); }
    private void companyForm(int index,String[] old) {
        LinearLayout form = column();
        EditText name = input("Company name", InputType.TYPE_CLASS_TEXT);
        EditText industry = input("Industry", InputType.TYPE_CLASS_TEXT);
        EditText location = input("Location", InputType.TYPE_CLASS_TEXT);
        EditText website = input("Website (HTTPS)", InputType.TYPE_TEXT_VARIATION_URI);
        EditText recruiter=input("Recruiter / contact name",InputType.TYPE_CLASS_TEXT),contact=input("Recruiter email or phone",InputType.TYPE_CLASS_TEXT);
        EditText[] fields={name,industry,location,website,recruiter,contact};
        if(old!=null)for(int i=0;i<fields.length;i++)fields[i].setText(old.length>i?old[i]:"");
        for (EditText field : fields) form.addView(field, lp(-1, 50));
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(old==null?"Add demonstration company":"Edit demonstration company").setView(form).setNegativeButton("Cancel", null).setPositiveButton("Save", null).create();
        dialog.setOnShowListener(v -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(w -> {
            if (name.length() == 0 || industry.length() == 0 || location.length() == 0 || recruiter.length()==0 || contact.length()==0) { toast("Complete all required company and recruiter fields"); return; }
            String url = website.getText().toString().trim();
            if (!url.isEmpty() && (!url.startsWith("https://") || !android.util.Patterns.WEB_URL.matcher(url).matches())) { website.setError("Enter a valid HTTPS website"); return; }
            if(index<0)repository.addCompany(name.getText().toString().trim(),industry.getText().toString().trim(),location.getText().toString().trim(),url,recruiter.getText().toString().trim(),contact.getText().toString().trim());
            else repository.updateCompany(index,name.getText().toString().trim(),industry.getText().toString().trim(),location.getText().toString().trim(),url,recruiter.getText().toString().trim(),contact.getText().toString().trim());
            dialog.dismiss(); recreate();
        }));
        dialog.show();
    }

    private void renderReports() {
        int shortlisted = 0, selected = 0;
        for (String[] application : repository.applications()) {
            if (application[3].equalsIgnoreCase("Shortlisted")) shortlisted++;
            if (application[3].equalsIgnoreCase("Selected")) selected++;
        }
        item(body, "Registered students", String.valueOf(repository.students().size()), "DEMO", null);
        item(body, "Active placement drives", String.valueOf(repository.drives().size()), "DEMO", null);
        item(body, "Applications", String.valueOf(repository.applications().size()), "DEMO", null);
        item(body, "Shortlisted students", String.valueOf(shortlisted), "DEMO", null);
        item(body, "Selected students", String.valueOf(selected), "DEMO", null);
        item(body, "Companies", String.valueOf(repository.companies().size()), "DEMO", null);
        note(body, "Summary values are computed from local sample records and are not official placement statistics.");
    }

    private void renderStudents() {
        EditText search = input("Search by student name or ID", InputType.TYPE_CLASS_TEXT);
        body.addView(search, lp(-1, 50));
        LinearLayout list = column();
        body.addView(list);
        Runnable draw = () -> {
            list.removeAllViews();
            String query = search.getText().toString().toLowerCase(Locale.ROOT);
            for (String[] student : repository.students()) {
                if ((student[0] + student[1]).toLowerCase(Locale.ROOT).contains(query)) {
                    item(list, student[0], student[1] + " · " + student[2] + "\nCGPA " + student[3] + " · Profile " + student[4], "VIEW", () ->
                        new AlertDialog.Builder(this).setTitle(student[0]).setMessage("Student ID: " + student[1] + "\nProgramme: " + student[2] + "\nCGPA: " + student[3] + "\nProfile completion: " + student[4] + "\n\nSample directory record.").setPositiveButton("Close", null).show());
                }
            }
            if (list.getChildCount() == 0) note(list, "No students match this search.");
        };
        search.addTextChangedListener(new android.text.TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { draw.run(); }
            public void afterTextChanged(android.text.Editable e) { }
        });
        draw.run();
    }

    private void renderDrives() {
        action(body, "Create Placement Drive", () -> driveForm(-1,null));
        for (int i = 0; i < repository.drives().size(); i++) {
            final int index = i;
            String[] drive = repository.drives().get(i);
            String status=drive.length>8?drive[8]:"Published";
            item(body, drive[0] + " · " + drive[1], drive[2] + " · " + drive[5] + " · Min CGPA " + drive[4] + " · Deadline " + drive[7], status, () ->
                new AlertDialog.Builder(this).setTitle(drive[0]+" · "+drive[1]).setItems(new String[]{"View details", "Edit drive", "Publish / unpublish", "Close drive", "Delete drive"}, (d, which) -> {
                    if(which==0)new AlertDialog.Builder(this).setTitle("Drive details").setMessage("Company: "+drive[0]+"\nRole: "+drive[1]+"\nPackage: "+drive[2]+"\nLocation: "+drive[3]+"\nBranches: "+drive[5]+"\nMinimum CGPA: "+drive[4]+"\nDeadline: "+drive[7]+"\nStatus: "+status).setPositiveButton("Close",null).show();
                    else if(which==1)driveForm(index,drive);
                    else if(which==2){repository.setDriveStatus(index,status.equalsIgnoreCase("Published")?"Unpublished":"Published");recreate();}
                    else if(which==3){repository.setDriveStatus(index,"Closed");recreate();}
                    else new AlertDialog.Builder(this).setTitle("Delete drive?").setMessage("Delete this local demonstration drive?").setNegativeButton("Cancel",null).setPositiveButton("Delete",(x,w)->{repository.removeDrive(index);recreate();}).show();
                }).setNegativeButton("Cancel",null).show());
        }
        if (repository.drives().isEmpty()) note(body, "No demonstration drives are available.");
    }

    private void createDrive() { driveForm(-1,null); }
    private void driveForm(int index,String[] old) {
        LinearLayout form = column();
        EditText[] fields={input("Company name",InputType.TYPE_CLASS_TEXT),input("Job title",InputType.TYPE_CLASS_TEXT),input("Package / CTC",InputType.TYPE_CLASS_TEXT),input("Location",InputType.TYPE_CLASS_TEXT),input("Minimum CGPA",InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER),input("Eligible branches",InputType.TYPE_CLASS_TEXT),input("Required skills",InputType.TYPE_CLASS_TEXT),input("Application deadline",InputType.TYPE_CLASS_TEXT)};
        if(old!=null)for(int i=0;i<fields.length;i++)fields[i].setText(old.length>i?old[i]:"");
        for(EditText field:fields)form.addView(field,lp(-1,50));
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(index<0?"New demonstration drive":"Edit demonstration drive").setView(form).setNegativeButton("Cancel", null).setPositiveButton("Save", null).create();
        dialog.setOnShowListener(v -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(w -> {
            for(int i:new int[]{0,1,4,5,7})if(fields[i].getText().toString().trim().isEmpty()){fields[i].setError("Required");return;}
            try{double cgpa=Double.parseDouble(fields[4].getText().toString().trim());if(cgpa<0||cgpa>10){fields[4].setError("CGPA must be from 0 to 10");return;}}catch(Exception e){fields[4].setError("Enter a valid CGPA");return;}
            String[] values=new String[9];for(int i=0;i<8;i++)values[i]=fields[i].getText().toString().trim();values[8]=old!=null&&old.length>8?old[8]:"Draft";
            repository.saveDrive(index,values);
            dialog.dismiss(); recreate();
        }));
        dialog.show();
    }

    private void renderApplications() {
        String[] statuses = {"Applied", "Shortlisted", "Interview", "Selected", "Rejected"};
        for (int i = 0; i < repository.applications().size(); i++) {
            final int index = i;
            String[] application = repository.applications().get(i);
            item(body, application[0] + " · " + application[1], "Applicant: " + application[2] + "\nPackage: " + application[4], application[3], () -> {
                int selected = 0;
                for (int j = 0; j < statuses.length; j++) if (statuses[j].equals(application[3])) selected = j;
                new AlertDialog.Builder(this).setTitle("Update sample application").setSingleChoiceItems(statuses, selected, (dialog, which) -> { repository.updateApplicationStatus(index, statuses[which]); dialog.dismiss(); recreate(); }).setNegativeButton("Cancel", null).show();
            });
        }
        if (repository.applications().isEmpty()) note(body, "No sample applications are available.");
    }

    private void renderResults() {
        heading(body, "Upcoming sample interviews");
        item(body, "Infosys · Systems Engineer", "Technical round · 28 Nov 2026 · 10:30 AM\nOnline · meeting link not available", "UPCOMING", null);
        item(body, "TCS · Graduate Engineer", "HR round · 18 Nov 2026 · 2:00 PM\nPlacement cell, Room 204", "COMPLETED", null);
        heading(body, "Sample results");
        item(body, "Infosys · Systems Engineer", "Selected · 6.5 LPA", "DEMO RESULT", null);
        note(body, "These schedules and outcomes are placeholders. They are not actual student decisions.");
    }
}

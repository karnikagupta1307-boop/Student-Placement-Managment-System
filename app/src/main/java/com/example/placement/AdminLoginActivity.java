package com.example.placement;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;

/** Admin demo entry point. It deliberately does not authenticate or store credentials. */
public class AdminLoginActivity extends BaseActivity {
    @Override protected int layout() { return R.layout.activity_admin_login; }

    @Override protected void render() {
        toolbar("Administrator Login");
        scroll(false);
        body.addView(text("Placement office", 24, ink, true));
        body.addView(text("Administrator workspace", 15, muted, false));
        gap(body, 14);
        note(body, "DEMO WORKSPACE\nAdmin authentication is not connected. Enter an email to continue to the local presentation panel. No password is validated or stored.");
        EditText email = input("Administrator email", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        label(body, "Email");
        body.addView(email, lp(-1, 52));
        EditText password = input("Password (not checked in demo)", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        label(body, "Password");
        body.addView(password, lp(-1, 52));
        action(body, "Continue to Demo Admin Panel", () -> {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                email.setError("Enter a valid email address");
                return;
            }
            launch(AdminDashboardActivity.class);
            finish();
        });
        LinearLayout.LayoutParams foot = lp(-1, -2);
        foot.topMargin = dp(16);
        android.widget.TextView back = text("Back to Welcome", 14, blue, true);
        back.setGravity(Gravity.CENTER);
        back.setPadding(0, dp(16), 0, dp(16));
        back.setOnClickListener(v -> finish());
        body.addView(back, foot);
    }
}

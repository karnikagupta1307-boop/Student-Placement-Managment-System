package com.example.placement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Brief branded handoff to the landing page; it performs no network work. */
public class SplashActivity extends BaseActivity {
    @Override protected int layout() { return R.layout.activity_splash; }

    @Override protected void render() {
        root.setBackgroundColor(Color.WHITE);
        LinearLayout content = column();
        content.setGravity(Gravity.CENTER);
        root.addView(content, new LinearLayout.LayoutParams(-1, -1));
        TextView seal = text("N", 32, Color.WHITE, true);
        seal.setGravity(Gravity.CENTER);
        seal.setBackground(shape(blue, 18, 0));
        content.addView(seal, lp(76, 76));
        gap(content, dp(18));
        TextView title = text("NMIMS Hyderabad", 24, ink, true);
        title.setGravity(Gravity.CENTER);
        content.addView(title, lp(-1, -2));
        TextView subtitle = text("Placement Management System", 15, muted, false);
        subtitle.setGravity(Gravity.CENTER);
        content.addView(subtitle, lp(-1, -2));
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!isFinishing()) {
                startActivity(new Intent(this, WelcomeActivity.class));
                finish();
            }
        }, 650);
    }
}

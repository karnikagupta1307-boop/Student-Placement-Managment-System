package com.example.placement;
import android.os.Bundle;import android.content.Intent;
public class ProfileWizardActivity extends ScreenActivity {
 @Override protected void onCreate(Bundle state){step=getIntent().getIntExtra("wizard_step",0);super.onCreate(state);}
 @Override protected void refreshWizard(){Intent i=new Intent(this,ProfileWizardActivity.class).putExtra("wizard_step",step);startActivity(i);finish();}
}

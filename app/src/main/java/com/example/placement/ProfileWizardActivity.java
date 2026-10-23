package com.example.placement;
import android.os.Bundle;import android.content.Intent;
public class ProfileWizardActivity extends ScreenActivity {
 @Override protected void onCreate(Bundle state){step=state!=null?state.getInt("wizard_step",getIntent().getIntExtra("wizard_step",0)):getIntent().getIntExtra("wizard_step",0);super.onCreate(state);}
 @Override protected void onSaveInstanceState(Bundle state){state.putInt("wizard_step",step);super.onSaveInstanceState(state);}
 @Override protected void refreshWizard(){Intent i=new Intent(this,ProfileWizardActivity.class).putExtra("wizard_step",step);startActivity(i);finish();}
}

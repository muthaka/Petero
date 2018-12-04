package c.pekamu.petero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a message that the User image was clicked.
     */
    public void showUserExperience(View view) {
        showToast(getString(R.string.user_message));
    }

    /**
     * Shows a message that the User image was clicked.
     */
    public void showInputs(View view) {
        showToast(getString(R.string.inputs_message));
    }

    /**
     * Shows a message that the User image was clicked.
     */
    public void showSavingData(View view) {
        showToast(getString(R.string.saving_message));
    }

    /**
     * Shows a message that the User image was clicked.
     */
    public void showAlarmManager(View view) {
        showToast(getString(R.string.alarm_message));
    }

    public void openUserExperience(View view){
        Intent userEx = new Intent(this, MainActivity.class);
        startActivity(userEx);
        showUserExperience(view);
    }
    public void openSavingData(View view){
        Intent savingDt = new Intent(this, SavingData.class);
        startActivity(savingDt);
        showSavingData(view);
    }
    public void openInputs(View view){
        Intent inputs = new Intent(this, InputData.class);
        startActivity(inputs);
        showInputs(view);
    }
    public void openAlarmManager(View view){
        Intent alarmMng = new Intent(this, AlarmManager.class);
        startActivity(alarmMng);
        showAlarmManager(view);
    }


}
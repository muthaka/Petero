package c.pekamu.petero;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Alarm extends AppCompatActivity {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ToggleButton alarmToggle = findViewById(R.id.toggleButton);
        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton,
                                                 boolean isChecked) {
                        String toastMessage;
                        if(isChecked){
                            deliverNotification(Alarm.this);
                            //Set the toast message for the "on" case.
                            toastMessage = "Stand Up Alarm On!";
                        } else {
                            mNotificationManager.cancelAll();
                            //Set the toast message for the "off" case.
                            toastMessage = "Stand Up Alarm Off!";
                        }

                        //Show a toast to say the alarm is turned on or off.
                        Toast.makeText(Alarm.this, toastMessage,Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, Alarm.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_run)
                .setContentTitle("Stand Up Alert")
                .setContentText("You should stand up and walk around now!")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}

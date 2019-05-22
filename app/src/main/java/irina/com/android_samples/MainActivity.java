package irina.com.android_samples;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import irina.com.android_samples.gallery.GalleryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeBackgroundColor();

        showNotificationAfterDelay(getNotificationWithAction("Hey!", "Its time to look at new inspiring images!"), 86400000);
//        showNotificationAfterDelay(getBasicNotification("My notification", "Much longer text that cannot fit one line..."), 5000);
//        showNotificationAfterDelay(getNotificationWithAction("My notification", "Hello World!"), 5000);
//        showNotificationAfterDelay(getNotificationWithProgress(), 5000);

    }

    private void changeBackgroundColor() {
        RelativeLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout == null) {
            return;
        }
        Random rnd = new Random();
        mainLayout.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
    }

    @Override
    protected void onPause() {
        super.onPause();

        RelativeLayout layout = findViewById(R.id.main_layout);
        if (layout == null) {
            return;
        }
        layout.setBackgroundColor(Color.CYAN);
    }

    public void openGallery(View view) {
        RadioButton gridViewButton = findViewById(R.id.gridViewRadioButton);
        RadioButton unsplashRadioButton = findViewById(R.id.unsplashRadioButton);

        GalleryActivity.ImageProvider imageProvider = unsplashRadioButton.isChecked() ? GalleryActivity.ImageProvider.Unsplash : GalleryActivity.ImageProvider.Giphy;
        GalleryActivity.PresenterType presenterType = gridViewButton.isChecked() ? GalleryActivity.PresenterType.Grid : GalleryActivity.PresenterType.List;
        Intent intent = GalleryActivity.buildIntent(this, imageProvider, presenterType);

        startActivity(intent);
    }

    public void openLoginActivity(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onButtonClick(View view) {
        TextView textViewName = findViewById(R.id.main_text_view);
        if (textViewName == null) {
            return;
        }
        textViewName.setText("ASDF");

        RelativeLayout layout = findViewById(R.id.main_layout);
        if (layout == null) {
            return;
        }
        layout.setBackgroundColor(Color.CYAN);

        //go to login page
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
    }

    //LOCAL NOTIFICATIONS
    private NotificationCompat.Builder getBasicNotification(String title, String text) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this,
                        "default")
                        .setSmallIcon(R.drawable.white_notification_icon)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        return mBuilder;
    }

    private NotificationCompat.Builder getNotificationWithAction(String title, String text) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.white_notification_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return mBuilder;
    }

    private NotificationCompat.Builder getNotificationWithProgress() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "default");
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.white_notification_icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE);

        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 40;
        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        return mBuilder;
    }

    private void showNotificationAfterDelay(final NotificationCompat.Builder notification, long delay) {
        final NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            String id = "my_channel_01";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, "Channel name", importance);
            mChannel.enableLights(true);
            mNotificationManager.createNotificationChannel(mChannel);
            notification.setChannelId(id);
        }

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNotificationManager.notify(0, notification.build());
            }
        }, delay);
    }

}

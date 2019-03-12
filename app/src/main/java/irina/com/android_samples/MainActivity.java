package irina.com.android_samples;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

}

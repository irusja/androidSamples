package irina.com.android_samples;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginPress(View view) {
        EditText editTextEmail = findViewById(R.id.editTestEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Login Form")
                .setMessage("You login as " + email)
                .setCancelable(true)
//                .setPositiveButton("ok", new OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Whatever...
//                    }
//                })
            .show();
    }
}

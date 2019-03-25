package irina.com.android_samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onLoginPress(View view) {
        EditText editTextEmail = findViewById(R.id.editTestEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            showErrorPopup();
        } else {
            //go to registration page
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void showErrorPopup() {
        new AlertDialog.Builder(this)
                .setTitle("Empty email/password")
                .setMessage("Please fill in email and password fields!")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .show();
    }
}

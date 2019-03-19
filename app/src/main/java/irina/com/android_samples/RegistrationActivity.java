package irina.com.android_samples;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.apache.commons.lang3.StringUtils;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void onSubmitClick(View view) {
        EditText editTextFirstName = findViewById(R.id.editTestFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        RadioGroup preferredDays = findViewById(R.id.preferred_day);
        CheckBox agreement = findViewById(R.id.agreement);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        if (StringUtils.isBlank(firstName)) {
            showErrorPopup("Empty first name", "Please fill in first name.");
        } else if (StringUtils.isBlank(lastName)) {
            showErrorPopup("Empty last name", "Please fill in last name.");
        } else if (preferredDays.getCheckedRadioButtonId() == -1) {
            showErrorPopup("No selected day", "Please select one more suitable for you day.");
        } else if (!agreement.isChecked()) {
            showErrorPopup("Agreement", "Please agree the terms.");
        } else {
            //go to main page
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void showErrorPopup(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
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

package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class RegistrationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText passwd = (EditText) findViewById(R.id.password_edittext);

        final Button registrationButton = (Button) findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            // Create an explicit Intent for starting the HelloAndroid
            // Activity
            Intent helloAndroidIntent = new Intent(RegistrationScreen.this,
                    MainActivity.class);

            // Use the Intent to start the HelloAndroid Activity
            startActivity(helloAndroidIntent);
            }
        });
        final TextView tv = (TextView) findViewById(R.id.textView);


        // Define a generic listener for all three RadioButtons in the RadioGroup
        final View.OnClickListener radioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                tv.setText(rb.getText() + " chosen");
            }
        };

        final RadioButton male = (RadioButton) findViewById(R.id.male);
        // Called when RadioButton choice1 is clicked
        male.setOnClickListener(radioListener);

        final RadioButton female = (RadioButton) findViewById(R.id.female);
        // Called when RadioButton choice2 is clicked
        female.setOnClickListener(radioListener);

    }

    private boolean checkPassword(Editable uname, Editable passwd) {
        // Just pretending to extract text and check password
        return new Random().nextBoolean();
    }



}

package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    //     allow user to select date/>time they like to be notified with date/timepicker"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginscreen);

        final EditText uname = (EditText) findViewById(R.id.username);
        final EditText passwd = (EditText) findViewById(R.id.password);

        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (checkPassword(uname.getText(), passwd.getText())) {

                    // Create an explicit Intent for starting the HelloAndroid
                    // Activity
                    Intent JourneyIntent = new Intent(LoginActivity.this,
                            Journeys.class);

                    // Use the Intent to start the HelloAndroid Activity
                    startActivity(JourneyIntent);
                } else {
                    uname.setText("");
                    passwd.setText("");
                }
            }
        });
        final Button registrationButton = (Button) findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // Create an explicit Intent for starting the HelloAndroid
                // Activity
                Intent helloAndroidIntent = new Intent(LoginActivity.this,
                        RegistrationScreen.class);

                // Use the Intent to start the HelloAndroid Activity
                startActivity(helloAndroidIntent);
            }
        });
    }

    private boolean checkPassword(Editable uname, Editable passwd) {
        // Just pretending to extract text and check password
        return new Random().nextBoolean();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
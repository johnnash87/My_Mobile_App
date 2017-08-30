package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    //     allow user to select date/>time they like to be notified with date/timepicker"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        setContentView(R.layout.loginscreen);

        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText passwd = (EditText) findViewById(R.id.password_edittext);

        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (checkPassword(uname.getText(), passwd.getText())) {

                    // Create an explicit Intent for starting the HelloAndroid
                    // Activity
                    Intent helloAndroidIntent = new Intent(MainActivity.this,
                            HelloAndroid.class);

                    // Use the Intent to start the HelloAndroid Activity
                    startActivity(helloAndroidIntent);
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
                Intent helloAndroidIntent = new Intent(MainActivity.this,
                        RegistrationScreen.class);

                // Use the Intent to start the HelloAndroid Activity
                startActivity(helloAndroidIntent);
            }
        });

        final TextView checked = (TextView) findViewById(R.id.checkBox);
        final CheckBox checkedbox = (CheckBox) findViewById(R.id.checkBox);
        checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkedbox.isChecked()){
                    checked.setText(R.string.is_checked);
                    checked.setBackgroundColor(getResources().getColor(R.color.button_clicked));

            }
            else{
                checked.setText(R.string.not_clicked);
                checked.setBackgroundColor(getResources().getColor(R.color.white));
            }
            }
        });


        final TextView clickTextView = (TextView) findViewById(R.id.toggleButton);
        final ToggleButton clicked = (ToggleButton) findViewById(R.id.toggleButton);
        clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked.isChecked()) {
                    clickTextView.setText(R.string.say_clicked);
                    clicked.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    clickTextView.setText(R.string.not_clicked);
                    clicked.setBackgroundColor(getResources().getColor(R.color.button_clicked));
                }
            }
        });

        final TextView helloTextView = (TextView) findViewById(R.id.hellotextView);
        final Button helloButton = (Button) findViewById(R.id.hello_Button);
        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helloTextView.setText(R.string.say_hello);
                helloButton.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });}

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

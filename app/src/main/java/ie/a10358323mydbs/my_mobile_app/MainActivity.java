package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity implements OnClickListener{

	private FirebaseAuth firebaseAuth;
	private TextView textViewUserEmail;
    private DatabaseReference databaseReference;
	private Button buttonStation, buttonLogout, buttonJourney, buttonCamera, buttonMap;
	private StorageReference storageReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		firebaseAuth =FirebaseAuth.getInstance();
		buttonStation=(Button) findViewById(R.id.buttonStation);
		buttonJourney=(Button) findViewById(R.id.buttonJourney);
		buttonCamera=(Button) findViewById(R.id.buttonCamera);
		buttonMap=(Button) findViewById(R.id.buttonMap);

		if(firebaseAuth.getCurrentUser()==null)
		{
			finish();
			startActivity(new Intent(this, LoginActivity.class));
		}

		databaseReference = FirebaseDatabase.getInstance().getReference();


		FirebaseUser user = firebaseAuth.getCurrentUser();

		textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
		textViewUserEmail.setText("Welcome to the Dublin bikes "+user.getEmail());
		buttonLogout = (Button)findViewById(R.id.button_Logout);
		storageReference = FirebaseStorage.getInstance().getReference();

		buttonLogout.setOnClickListener(this);
		buttonStation.setOnClickListener(this);
		buttonJourney.setOnClickListener(this);
		buttonCamera.setOnClickListener(this);
		buttonMap.setOnClickListener(this);
	}


	@Override
	public void onClick(View view) {
		if (view==buttonLogout){
			firebaseAuth.signOut();
			finish();
			startActivity(new Intent(this, LoginActivity.class));
		}
		if(view==buttonStation){
			startActivity(new Intent(this, NetworkingAndroidHttpClientJSONActivity.class));
		}
		if(view==buttonJourney){
			startActivity(new Intent(this, Journey.class));
		}
		if(view==buttonCamera){
			startActivity(new Intent(this, CameraUpload.class));
		}
		if(view==buttonMap){
			startActivity(new Intent(this, MapsActivity.class));
		}
	}
}
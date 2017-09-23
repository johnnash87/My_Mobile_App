package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnClickListener{

	private static final int PICK_IMAGE_REQUEST = 234;
	private FirebaseAuth firebaseAuth;
	private TextView textViewUserEmail;
	private Button buttonLogout;
    private DatabaseReference databaseReference;
	private Button buttonSave;
    private EditText editTextName, editTextAddress;
    private Button buttonStation;

	private Button buttonChoose, buttonUpload;
	private ImageView imageView;
	private Uri filePath;
	private StorageReference storageReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firebaseAuth =FirebaseAuth.getInstance();
		imageView = (ImageView) findViewById(R.id.imageView);
		buttonChoose=(Button) findViewById(R.id.buttonChoose);
		buttonUpload =(Button) findViewById(R.id.buttonUpload);
        buttonStation= (Button) findViewById(R.id.buttonStation);

		if(firebaseAuth.getCurrentUser()==null)
		{
			finish();
			startActivity(new Intent(this, LoginActivity.class));
		}

		databaseReference = FirebaseDatabase.getInstance().getReference();

		editTextAddress=(EditText) findViewById(R.id.editTextAddress);
		editTextName=(EditText) findViewById(R.id.editTextName);
		buttonSave =(Button) findViewById(R.id.button_Save);


		FirebaseUser user = firebaseAuth.getCurrentUser();

		textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
		textViewUserEmail.setText("Welcome "+user.getEmail());
		buttonLogout = (Button)findViewById(R.id.button_Logout);
		storageReference = FirebaseStorage.getInstance().getReference();

		buttonChoose.setOnClickListener(this);
		buttonUpload.setOnClickListener(this);
		buttonLogout.setOnClickListener(this);
		buttonSave.setOnClickListener(this);
        buttonStation.setOnClickListener(this);
	}

	private void saveUserInformation(){
		String name = editTextName.getText().toString().trim();
		String address = editTextAddress.getText().toString().trim();

		UserInformation userInformation = new UserInformation(name, address);
		FirebaseUser user = firebaseAuth.getCurrentUser();
		databaseReference.child(user.getUid()).setValue(userInformation);
		Toast.makeText(this,"Information Saved...", Toast.LENGTH_SHORT).show();
	}
	private void showFileChooser(){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,"Select an Image to upload"), PICK_IMAGE_REQUEST);
	}
	private void uploadFile(){
		if(filePath!=null){

		StorageReference riversRef = storageReference.child("images/journey.jpg");

		riversRef.putFile(filePath)
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
						Toast.makeText(getApplicationContext(),"Photo uploaded successfully!", Toast.LENGTH_LONG).show();
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception exception) {
						Toast.makeText(getApplicationContext(),exception.getMessage(), Toast.LENGTH_LONG).show();
					}
				});
	}else{

	}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK &&data !=null &&data.getData()!=null){
			filePath=data.getData();
			try{
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				imageView.setImageBitmap(bitmap);
		}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View view) {
		if (view==buttonLogout){
			firebaseAuth.signOut();
			finish();
			startActivity(new Intent(this, LoginActivity.class));
		}
		if(view==buttonSave){
			saveUserInformation();
		}
		if(view==buttonChoose){
			showFileChooser();
		}
		if(view==buttonUpload){
			uploadFile();
		}
        if (view == buttonStation) {
            startActivity(new Intent(this, NetworkingAndroidHttpClientJSONActivity.class));
        }
    }
}
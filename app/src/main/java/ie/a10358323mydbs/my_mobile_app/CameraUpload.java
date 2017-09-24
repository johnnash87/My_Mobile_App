package ie.a10358323mydbs.my_mobile_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class CameraUpload extends AppCompatActivity {

    private Button mUploadButton;
    private ImageView mImageView;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        mStorage = FirebaseStorage.getInstance().getReference();

        mUploadButton = (Button) findViewById(R.id.buttonUpload2);
        mImageView = (ImageView) findViewById(R.id.imageView2);


        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAMERA_REQUEST_CODE);

            }
        });
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("UserPhotos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CameraUpload.this, "Uploading Finished ...", Toast.LENGTH_LONG).show();
                }
            });
        }
    }*/
          @Override
          protected void onActivityResult(int requestCode, int resultCode, Intent data){
           super.onActivityResult(requestCode, resultCode, data);
           if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

           Bundle extras = data.getExtras();
           Bitmap bitmap = (Bitmap) data.getExtras().get("data");
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
           byte[] dataBAOS = baos.toByteArray();

//set the image into imageview
           mImageView.setImageBitmap(bitmap);
           /*************** UPLOADS THE PIC TO FIREBASE***************/
           //Firebase storage folder where you want to put the image
               mStorage = FirebaseStorage.getInstance().getReference();
//name of the image file (add time to have different files to avoid rewrite on the same file)

           StorageReference imagesRef = mStorage.child("images/journey1.jpg");
//upload image

           UploadTask uploadTask = imagesRef.putBytes(dataBAOS);
           uploadTask.addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception exception) {
                   Toast.makeText(getApplicationContext(), "Sending failed", Toast.LENGTH_SHORT).show();
               }
           }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                   Toast.makeText(getApplicationContext(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
               }
           });
       }
   }
}
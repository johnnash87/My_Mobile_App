package ie.a10358323mydbs.my_mobile_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

public class CameraUpload extends AppCompatActivity {

    private Button mUploadButton;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        mUploadButton = (Button) findViewById(R.id.buttonUpload2);
        mImageView = (ImageView) findViewById(R.id.imageView2);
    }

}

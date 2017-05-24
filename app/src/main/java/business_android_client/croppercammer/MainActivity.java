package business_android_client.croppercammer;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ClipCamera camera;
    private Button btn_shoot;
    private Button btn_cancle;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        camera = (ClipCamera) findViewById(R.id.surface_view);
        btn_shoot = (Button) findViewById(R.id.btn_shoot);
        iv = (ImageView) findViewById(R.id.iv);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_shoot.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_shoot:
                takePhoto();
                break;
            case R.id.btn_cancle:
                finish();
                break;
        }
    }

    public void takePhoto() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/test.jpg";
        camera.takePicture(path);
        iv.setVisibility(View.VISIBLE);
        iv.setImageBitmap(BitmapFactory.decodeFile(path));
    }
}

package business_android_client.croppercammer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ClipCamera.IAutoFocus {

    private ClipCamera camera;
    private Button btn_shoot;
    private Button btn_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 22);
        } else {
            setContentView(R.layout.activity_main);
            initView();
        }

    }

    private void initView() {
        camera = (ClipCamera) findViewById(R.id.surface_view);
        btn_shoot = (Button) findViewById(R.id.btn_shoot);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_shoot.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);
        camera.setIAutoFocus(this);
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
    }


    @Override
    public void autoFocus() {
        camera.setAutoFocus();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 22) {
            for (int i=0;i< permissions.length;i++) {
                String s = permissions[i];
                if (s.equals(Manifest.permission.CAMERA) && grantResults[i]== PackageManager.PERMISSION_GRANTED) {
                    setContentView(R.layout.activity_main);
                    initView();
                }
            }
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                  setContentView(R.layout.activity_main);
//                    initView();
//            }
        }
    }
}

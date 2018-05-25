package com.jkkc.carer.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jkkc.carer.R;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Guan on 2018/5/23.
 */

public class LoginActivity extends AppCompatActivity {

    private ImageView mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//        AppManager.getAppManager().addActivity(this);

        mBtnLogin = (ImageView) findViewById(R.id.btnLogin);


        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .request();


        TextView tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvVersion.setText("版本号：" + AppUtils.getAppVersionName() + AppUtils.getAppVersionCode());




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        PermissionGen.onRequestPermissionsResult(this, requestCode,
                permissions, grantResults);


    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();


            }
        });


    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToastUtils.showShort("请允许所需要的权限");


            }
        });

        ToastUtils.showShort("请允许所需要的权限");

        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .request();


    }

}

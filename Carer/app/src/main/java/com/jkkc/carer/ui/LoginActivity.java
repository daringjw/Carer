package com.jkkc.carer.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.utils.PhoneFormatCheckUtils;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import cn.jpush.android.api.JPushInterface;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Guan on 2018/5/23.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG1 = LoginActivity.class.getSimpleName();
    private ImageView mBtnLogin;
    private TextInputEditText mTieAccount;
    private TextInputEditText mTiePwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//        AppManager.getAppManager().addActivity(this);

        mBtnLogin = (ImageView) findViewById(R.id.btnLogin);
        mTieAccount = (TextInputEditText) findViewById(R.id.tieAccount);
        mTiePwd = (TextInputEditText) findViewById(R.id.tiePwd);

        String account = PrefUtils.getString(getApplicationContext(), "account", null);
        if (!TextUtils.isEmpty(account)) {

            mTieAccount.setText(account);

        }


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


                //login
                final String account = mTieAccount.getText().toString();
                String pwd = mTiePwd.getText().toString();

                if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
                    //都不为空
                    if (PhoneFormatCheckUtils.isMobile(account)) {
                        //手机号，可以登录
                        OkGo.<String>get(Constants.login)
                                .tag(this)
                                .params("phoneNum", account)
                                .params("password", pwd)
                                .params("registerId", JPushInterface.getRegistrationID(LoginActivity.this))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {


                                        String result = response.body().toString();
                                        Gson gson = new Gson();
                                        LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                                        String msg = loginBean.getMsg();
                                        if (msg.contains("登录成功")) {

                                            //恢复极光推送
                                            JPushInterface.resumePush(getApplicationContext());
                                            //保存信息
                                            PrefUtils.setString(getApplicationContext(), "loginBean", result);
                                            PrefUtils.setBoolean(getApplicationContext(), "loginState", true);
                                            //保存账号
                                            PrefUtils.setString(getApplicationContext(), "account", account);

                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }


                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);

                                        ToastUtils.showShort("服务器开小差了");

                                    }
                                });


                    } else {
                        //非手机号
                        ToastUtils.showShort("请输入正确手机号");

                    }


                } else {

                    ToastUtils.showShort("账号和密码不能为空");
                }


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

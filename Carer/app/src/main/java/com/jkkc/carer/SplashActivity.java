package com.jkkc.carer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.jkkc.carer.ui.HomeActivity;
import com.jkkc.carer.ui.LoginActivity;
import com.jkkc.carer.utils.PrefUtils;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 1000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (handler == null) {
            handler = new Handler();
        }

        // 延迟SPLASH_DISPLAY_LENGHT时间然后跳转到MainActivity
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                //sp获取登录状态的boolean value
                boolean loginState = PrefUtils.getBoolean(getApplicationContext(), "loginState", false);
                if (loginState) {
                    //已经登录
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    //未登录
                    Intent intent = new Intent(SplashActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }


            }
        }, SPLASH_DISPLAY_LENGHT);


    }


}

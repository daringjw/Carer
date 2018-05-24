package com.jkkc.carer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jkkc.carer.R;

/**
 * Created by Guan on 2018/5/23.
 */

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//        AppManager.getAppManager().addActivity(this);

        ImageView btnLogin = (ImageView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();


            }
        });





    }

}

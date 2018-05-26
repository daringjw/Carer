package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jkkc.carer.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Guan on 2018/5/26.
 */

public class EditPersonInfoActivity extends AppCompatActivity {

    private TextView mTvSex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_person_info);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        mTvSex = (TextView) findViewById(R.id.tvSex);


        LinearLayout llSex = (LinearLayout) findViewById(R.id.llSex);
        llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(EditPersonInfoActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("修改性别")
                        .setContentText("")
                        .setConfirmText("男")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                mTvSex.setText("男");

                            }
                        })
                        .setCancelText("女")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();

                                mTvSex.setText("女");

                            }
                        })
                        .show();

            }
        });


    }


}

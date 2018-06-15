package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Created by Guan on 2018/6/14.
 */

public class OnlineTrainingActivity extends AppCompatActivity {

    private static final String TAG1 = OnlineTrainingActivity.class.getSimpleName();

    String result;
    LoginBean mLoginBean;
    private VideoView mVvVideo;
    private MediaController mController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_online_training);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

            }
        });

        result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);

        OkGo.<String>get(Constants.onlineVideo)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String result = response.body().toString();


                    }
                });


        mVvVideo = (VideoView) findViewById(R.id.vvVideo);

        //实例化控制器
        mController = new MediaController(this);

        OkGo.<File>get(Constants.onlineFile)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {

                        String path = response.body().getAbsolutePath();
                        Log.d(TAG1,"path="+path);
                        mVvVideo.setVideoPath(path);
                        mController.setMediaPlayer(mVvVideo);
                        mVvVideo.setMediaController(mController);


                    }
                });


        OkGo.<String>get(Constants.fileNumber)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String number = response.body().toString();
                        Log.d(TAG1,"number="+number);

                    }
                });


    }


}

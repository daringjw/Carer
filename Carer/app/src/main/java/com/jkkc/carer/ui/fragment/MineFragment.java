package com.jkkc.carer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.jkkc.carer.R;
import com.jkkc.carer.ui.LoginActivity;
import com.jkkc.carer.ui.activity.ModifyPwdActivity;
import com.jkkc.carer.ui.activity.PersonalInfoActivity;
import com.jkkc.carer.utils.AppManager;
import com.jkkc.carer.utils.PrefUtils;
import com.tencent.bugly.beta.Beta;

import cn.jpush.android.api.JPushInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Guan on 2018/5/24.
 */

public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_mine, null);

        RelativeLayout rlBaseInfo = view.findViewById(R.id.rlBaseInfo);
        RelativeLayout rlModifyPwd = view.findViewById(R.id.rlModifyPwd);
        RelativeLayout rlLogout = view.findViewById(R.id.rlLogout);


        rlBaseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));


            }
        });


        rlModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), ModifyPwdActivity.class));

            }
        });


        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //退出
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("退出")
                        .setContentText("")
                        .setConfirmText("确定")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                                //停止极光推送
                                JPushInterface.stopPush(getActivity());
                                //登录状态变为false
                                PrefUtils.setBoolean(getActivity(),"loginState",false);


                                AppManager.getAppManager().finishAllActivity();
                                startActivity(new Intent(getActivity(), LoginActivity.class));

                            }
                        })
                        .setCancelText("取消")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();


                            }
                        })
                        .show();


            }
        });


        TextView tvVersion = view.findViewById(R.id.tvVersion);
        tvVersion.setText("版本号：" + AppUtils.getAppVersionName() + AppUtils.getAppVersionCode());

        RelativeLayout rlCheckVersion = view.findViewById(R.id.rlCheckVersion);
        rlCheckVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Beta.checkUpgrade();

            }
        });


        return view;

    }


}

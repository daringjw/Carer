package com.jkkc.carer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jkkc.carer.R;
import com.jkkc.carer.ui.LoginActivity;
import com.jkkc.carer.ui.activity.ModifyPwdActivity;
import com.jkkc.carer.ui.activity.PersonalInfoActivity;
import com.jkkc.carer.utils.AppManager;

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

                startActivity(new Intent(getActivity(),PersonalInfoActivity.class));


            }
        });


        rlModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),ModifyPwdActivity.class));

            }
        });


        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppManager.getAppManager().finishAllActivity();
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });



        return view;

    }




}

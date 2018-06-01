package com.jkkc.carer.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.jkkc.carer.R;


/**
 * Created by Guan on 2018/5/28.
 */

public class HealthCheckFragment extends Fragment{

    CallBackValue callBackValue;

    /**
     * fragment与activity产生关联是  回调这个方法
     */
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        callBackValue =(CallBackValue) getActivity();
    }

    //定义一个回调接口
    public interface CallBackValue{
        public void SendMessageValue(String strValue);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_health_check,null);

        RadioGroup rgSelect = view.findViewById(R.id.rgSelect);
        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i){

                    case R.id.rbTiWen:

                        ToastUtils.showShort("体温检测");
                        callBackValue.SendMessageValue("体温检测");

                        break;

                    case R.id.rbXueYa:

                        ToastUtils.showShort("血压检测");
                        callBackValue.SendMessageValue("血压检测");

                        break;

                    case R.id.rbMaiBo:

                        ToastUtils.showShort("脉搏检测");
                        callBackValue.SendMessageValue("脉搏检测");

                        break;
                    case R.id.rbHuXi:

                        ToastUtils.showShort("呼吸检测");
                        callBackValue.SendMessageValue("呼吸检测");


                        break;
                    case R.id.rbNiaoLiang:

                        ToastUtils.showShort("尿量检测");
                        callBackValue.SendMessageValue("尿量检测");

                        break;



                }

            }
        });


        return view;

    }


}

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

public class BedCleanFragment extends Fragment {

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

        View view = View.inflate(getActivity(), R.layout.fragment_bed_clean, null);

        RadioGroup rgSelect = view.findViewById(R.id.rgSelect);
        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i){

                    case R.id.rbHuanChuangDan:

                        ToastUtils.showShort("换床单");
                        callBackValue.SendMessageValue("换床单");

                        break;

                    case R.id.rbHuanBeiRu:

                        ToastUtils.showShort("换被褥");
                        callBackValue.SendMessageValue("换被褥");

                        break;

                    case R.id.rbYuesuYi:

                        ToastUtils.showShort("约束衣");
                        callBackValue.SendMessageValue("约束衣");

                        break;
                    case R.id.rbYuesuDai:

                        ToastUtils.showShort("约束带");
                        callBackValue.SendMessageValue("约束带");


                        break;
                    case R.id.rbYuesuShouTaoClean:

                        ToastUtils.showShort("约束手套清洗");
                        callBackValue.SendMessageValue("约束手套清洗");

                        break;



                }

            }
        });

        return view;

    }


}

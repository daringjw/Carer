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

public class DietMedicineFragment extends Fragment{

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

        public void SendVariety(String str);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_diet_medicine,null);

        RadioGroup rgSelect = view.findViewById(R.id.rgSelect);
        rgSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                callBackValue.SendVariety("饮食服药类");

                switch (i){

                    case R.id.rbThreeMealsReservation:

                        ToastUtils.showShort("三餐餐食预定");
                        callBackValue.SendMessageValue("三餐餐食预定");

                        break;

                    case R.id.rbChuangQianJiuCan:

                        ToastUtils.showShort("床前就餐");
                        callBackValue.SendMessageValue("床前就餐");

                        break;

                    case R.id.rbFuYao:

                        ToastUtils.showShort("服药");
                        callBackValue.SendMessageValue("服药");

                        break;




                }

            }
        });

        return view;

    }


}

package com.jkkc.carer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.jkkc.carer.R;
import com.jkkc.carer.ui.activity.AddCareActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Guan on 2018/5/24.
 */

public class DailyCareFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private static final String TAG1 = DailyCareFragment.class.getSimpleName();
    private static final int ADD_CARE = 101;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_daily_care, null);

        ImageView ivAdd = view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //扫描二维码
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);


            }
        });








        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.d(TAG1, "解析结果:" + result);
//                    ToastUtils.showShort("解析结果:"+result);
                    //扫描成功，添加护理，跳转添加护理界面
                    Intent intent = new Intent(getActivity(), AddCareActivity.class);
                    intent.putExtra("result", result);
                    startActivityForResult(intent, ADD_CARE);


                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort("解析二维码失败");
                }
            }
        } else if (requestCode == ADD_CARE) {

            //添加护理后，提交服务器后的结果
            //刷新当前界面，访问服务器数据


        }


    }
}

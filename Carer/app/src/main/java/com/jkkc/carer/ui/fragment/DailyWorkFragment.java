package com.jkkc.carer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.jkkc.carer.R;
import com.jkkc.carer.ui.activity.OnlineTrainingActivity;
import com.jkkc.carer.ui.activity.SchedulingInformationActivity;
import com.jkkc.carer.ui.activity.ShiftActivity;

/**
 * Created by Guan on 2018/5/24.
 */

public class DailyWorkFragment extends Fragment {

    SliderLayout sliderShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_daily_work, null);

        sliderShow = (SliderLayout) view.findViewById(R.id.slider);

        TextSliderView textSliderView = new TextSliderView(getActivity());
        textSliderView
                .description("示例图片1")
                .image("http://pic.58pic.com/58pic/17/20/87/71U58PICnS7_1024.jpg");

        TextSliderView textSliderView1 = new TextSliderView(getActivity());
        textSliderView1
                .description("示例图片2")
                .image("http://pic.58pic.com/58pic/11/49/33/08j58PICBp9.jpg");

        TextSliderView textSliderView2 = new TextSliderView(getActivity());
        textSliderView2
                .description("示例图片3")
                .image("http://pic.58pic.com/58pic/13/01/42/60r58PICfqi.jpg");


        sliderShow.addSlider(textSliderView);
        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);

        //交接班
        Button btnShift = view.findViewById(R.id.btnShift);
        btnShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ShiftActivity.class);
                startActivity(intent);



            }
        });

        //排班信息
        Button btnSchedulingInformation = view.findViewById(R.id.btnSchedulingInformation);
        btnSchedulingInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SchedulingInformationActivity.class);
                startActivity(intent);

            }
        });

        //在线培训
        Button btnOnlineTraining = view.findViewById(R.id.btnOnlineTraining);
        btnOnlineTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), OnlineTrainingActivity.class);
                startActivity(intent);

            }
        });



        return view;

    }





    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();

    }



}

package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.eagledeveloper.newkpop.R;
import com.eagledeveloper.newkpop.activities.MainActivity;
import com.eagledeveloper.newkpop.services.AlarmStartService;
import com.eagledeveloper.newkpop.services.NotificationServices;
import com.eagledeveloper.newkpop.shake.ShakeService;
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {
    View view;
    @BindView(R.id.sw_shake)
    SwitchCompat swShake;


    @BindView(R.id.sw_auto)
    SwitchCompat swAuto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        getActivity().setTitle("Setting");
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);

        swShake.setChecked(GeneralUtils.getSharedPreferences(getActivity()).getBoolean("shake_unable", false));
        swShake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().startService(new Intent(getActivity(), ShakeService.class));
                    GeneralUtils.putBooleanValueInEditor(getActivity(), "shake_available", true);
                } else {
                    getActivity().stopService(new Intent(getActivity(), ShakeService.class));
                    GeneralUtils.putBooleanValueInEditor(getActivity(), "shake_unable", false);
                }
            }
        });

        swAuto.setChecked(GeneralUtils.getSharedPreferences(getActivity()).getBoolean("alarm_unable", false));
        swAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().startService(new Intent(getActivity(), AlarmStartService.class));
                    GeneralUtils.putBooleanValueInEditor(getActivity(), "alarm_unable", true);
                } else {
                    getActivity().stopService(new Intent(getActivity(), AlarmStartService.class));
                    GeneralUtils.putBooleanValueInEditor(getActivity(), "alarm_unable", false);
                }
            }
        });


//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_1hour:
//
//
//                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 5000);
//
//                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    case R.id.rb_12hour:
//                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
//
//                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 10000);
//
//                        break;
//                    case R.id.rb_24hour:
//                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
//
//                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 20000);
//                        break;
//                }
//            }
//        });
    }
}

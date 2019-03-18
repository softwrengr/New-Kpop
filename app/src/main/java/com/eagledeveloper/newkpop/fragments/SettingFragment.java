package com.eagledeveloper.newkpop.fragments;

import android.content.Context;
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
import com.eagledeveloper.newkpop.utils.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {
    View view;
    @BindView(R.id.my_switch)
    SwitchCompat switchCompat;


    @BindView(R.id.rb_1hour)
    RadioButton rb1Hour;
    @BindView(R.id.rb_12hour)
    RadioButton rb12Hour;
    @BindView(R.id.rb_24hour)
    RadioButton rb24Hour;


    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

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

     /*   switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(), "On", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "off", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1hour:


                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 5000);

                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.rb_12hour:
                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();

                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 10000);

                        break;
                    case R.id.rb_24hour:
                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();

                        GeneralUtils.putIntegerValueInEditor(getActivity(), "wallpaperChangeTime", 20000);
                        break;
                }
            }
        });
    }
}

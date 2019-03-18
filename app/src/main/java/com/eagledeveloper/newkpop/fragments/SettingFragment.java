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
import android.widget.Toast;

import com.eagledeveloper.newkpop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {
    View view;
    @BindView(R.id.my_switch)
    SwitchCompat switchCompat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        getActivity().setTitle("Setting");
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(), "On", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

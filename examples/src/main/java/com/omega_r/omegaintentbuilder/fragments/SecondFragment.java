package com.omega_r.omegaintentbuilder.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.omega_r.libs.omegafragmentbuilder.AppOmegaFragmentBuilder;
import com.omega_r.omegaintentbuilder.R;

import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaFragment;

@OmegaFragment
public class SecondFragment extends BaseFragment {

    @OmegaExtra
    String value;

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppOmegaFragmentBuilder.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(value);
    }
}

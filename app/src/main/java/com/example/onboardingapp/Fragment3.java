package com.example.onboardingapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    TextView tvWelcomeUser;
    CheckBox cbAgree;
    Button buttonFinish;

    public interface OnCheckboxChangeListener {
        void onCheckboxChanged(boolean isChecked);
    }

    OnCheckboxChangeListener checkboxListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnCheckboxChangeListener) {
            checkboxListener = (OnCheckboxChangeListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnCheckboxChangeListener"
            );
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tvWelcomeUser = view.findViewById(R.id.tv_welcome_user);
        cbAgree = view.findViewById(R.id.cb_agree);
        buttonFinish = view.findViewById(R.id.button_finish);

        Bundle bundle = getArguments();

        if (bundle != null) {
            String name = bundle.getString("user_name");
            tvWelcomeUser.setText("Welcome, " + name + "!");
        }

        cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkboxListener.onCheckboxChanged(isChecked);
        });

        return view;
    }

    public void updateButtonState(boolean isChecked) {

        buttonFinish.setEnabled(isChecked);

        if (isChecked) {
            buttonFinish.setText("Finish");
            buttonFinish.setTextColor(Color.WHITE);
            buttonFinish.setBackgroundTintList(
                    ColorStateList.valueOf(
                            getResources().getColor(android.R.color.holo_purple)));

        } else {
            buttonFinish.setText("Continue");
            buttonFinish.setTextColor(Color.parseColor("#BDBDBD"));
            buttonFinish.setBackgroundTintList(
                    ColorStateList.valueOf(Color.parseColor("#333333"))
            );
        }
    }
}
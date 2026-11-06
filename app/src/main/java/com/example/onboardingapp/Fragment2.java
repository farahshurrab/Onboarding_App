
package com.example.onboardingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    EditText etName, etEmail;
    RadioGroup rgGender;

    public interface OnDataPassListener {
        void onDataPass(String name);
    }

    OnDataPassListener dataPassListener;

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);

        if (context instanceof OnDataPassListener) {
            dataPassListener = (OnDataPassListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnDataPassListener"
            );
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);

        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        rgGender = view.findViewById(R.id.rg_gender);

        return view;
    }

    public void sendName() {

        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Please enter your name");
            return;
        }

        dataPassListener.onDataPass(name);
    }
}

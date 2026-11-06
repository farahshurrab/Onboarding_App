
package com.example.onboardingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnDataPassListener,
        Fragment3.OnCheckboxChangeListener {

    Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContinue = findViewById(R.id.button_continue);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new Fragment1())
                    .commit();
        }

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {

            Fragment currentFragment = getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);

            if (currentFragment instanceof Fragment3) {
                buttonContinue.setVisibility(View.GONE);
            } else {
                buttonContinue.setVisibility(View.VISIBLE);
                buttonContinue.setText("Continue");
            }
        });

        buttonContinue.setOnClickListener(v -> {

            Fragment currentFragment = getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);

            if (currentFragment instanceof Fragment1) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Fragment2())
                        .addToBackStack(null)
                        .commit();

            } else if (currentFragment instanceof Fragment2) {

                ((Fragment2) currentFragment).sendName();
            }
        });
    }

    @Override
    public void onDataPass(String name) {

        Bundle bundle = new Bundle();
        bundle.putString("user_name", name);

        Fragment3 fragment3 = new Fragment3();
        fragment3.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCheckboxChanged(boolean isChecked) {

        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof Fragment3) {
            ((Fragment3) currentFragment).updateButtonState(isChecked);
        }
    }
}

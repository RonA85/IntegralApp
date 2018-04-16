package com.example.mac.integralapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ron on 26/03/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    private Unbinder unbinder;
    protected SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract @LayoutRes
    int getLayout();

    protected void requestFocus(View v) {
        v.requestFocus();
        showKeyboard(v);
    }

    /**
     * Shows the soft keyboard
     */
    protected void showKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int msgRes) {
        Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show();
    }


}

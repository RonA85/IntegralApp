package com.example.mac.integralapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.me_input)
    MaterialEditText meInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.btn_add_fragments})
    public void addFragments(View view) {
        switch (view.getId()) {
            case R.id.btn_add_fragments:
                if(!validateInput())break;
                addNestedFragment();
                meInput.setText("");
                break;
        }
    }
    //Add Nested fragments
    private void addNestedFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("number", 1);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("total", Integer.parseInt(meInput.getText().toString()));
        editor.apply();
        NestedFragment nestedFragment = new NestedFragment();
        nestedFragment.setArguments(bundle);
        ft.add(R.id.container, nestedFragment);
        ft.commit();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private boolean validateInput() {
        if (meInput.getText().toString().trim().isEmpty()||meInput.getText().toString().equals("")) {
            showToast(R.string.err_msg_input);
            requestFocus(meInput);
            return false;
        } else return true;
    }

}

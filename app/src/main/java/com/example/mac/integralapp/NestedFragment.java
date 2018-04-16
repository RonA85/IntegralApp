package com.example.mac.integralapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class NestedFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tv_num)
    TextView fragmentNum;
    @BindView(R.id.fragment_background)
    LinearLayout layout;
    private FragmentTransaction ft;
    private int fragIndex;
    private int totalFragments;
    private final int margin = 50;
    public NestedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nested, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences sp = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        fragIndex = getArguments().getInt("number");
        totalFragments = sp.getInt("total", 0);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentNum.setText(String.format(Locale.getDefault(), "%d", fragIndex));
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(fragIndex * margin, fragIndex * margin, fragIndex * margin, fragIndex * margin);
        layout.setLayoutParams(params);
        if (fragIndex < totalFragments) {
            ft = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("number", fragIndex + 1);
            NestedFragment nestedFragment = new NestedFragment();
            nestedFragment.setArguments(bundle);
            ft.add(R.id.container_child, nestedFragment);
            ft.commit();
        }
    }

    @OnClick({R.id.btn_change_color})
    public void changeBackground(View view) {
        switch (view.getId()) {
            case R.id.btn_change_color:
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                layout.setBackgroundColor(color);
                break;
        }
    }

}

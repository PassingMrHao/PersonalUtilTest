package com.mrhao.personalutiltest.myfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrhao.personalutiltest.R;

public class BottomMenuFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vv = LayoutInflater.from(getActivity()).inflate(R.layout.bottommenu_page2, null, false);
        return vv;
    }


}
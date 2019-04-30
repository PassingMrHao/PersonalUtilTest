package com.mrhao.personalutiltest.myactivity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mrhao.personalutiltest.R;

import java.util.ArrayList;
import java.util.List;

public class JavaExeActivity extends AppCompatActivity {

    String[] str1 = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    List<String> list_str = new ArrayList<>();
    List<String> list_str2 = new ArrayList<>();

    int[] week = {1, 2, 3, 4, 5, 6, 7};
    List<Integer> list_week = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_exe);
        ExerciseJava();
    }


    //《HeadFirst》阅读随练
    private void ExerciseJava() {
        //循环遍历

        //for循环
        for (int i = 0; i < str1.length; i++) {
            list_str.add(str1[i]);

        }

        //for超级遍历
        for (String a : str1) {
            list_str2.add(a);

        }

        Log.e("TAGTAG", "List_Str1And2_toString: " + list_str.toString() + "\n" + list_str2.toString());

        //调用indexOf(int)可以查找具体元素在ArrayList集合中的位置(从0开始)，查询的元素在ArrayList集合中必须包含


        Log.e("HHHHYYYYLLLL", "IndexOf(): " + list_str.indexOf("周五"));

    }


}

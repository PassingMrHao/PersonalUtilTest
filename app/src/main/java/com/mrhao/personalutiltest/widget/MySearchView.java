package com.mrhao.personalutiltest.widget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;

/**
 * @Title:
 * @Description: 自定义搜索框带搜索历史，输入框带一件删除
 * @author: MrHao
 * @data: 2019\6\18   16:31
 */


public class MySearchView extends LinearLayout {

    Context mcontext;
    RecordSQLiteOpenHelper helper;
    private BaseAdapter adapter;
    private SearchListView SearchList; //搜索历史记录
    private LinearLayout listView;
    private SQLiteDatabase db;  //SQL数据库
    ClearEditext ce; //自定义带删除的 Editext输入框
    RelativeLayout relay; //搜索按钮
    TextView deletehis; //清空搜索历史记录

    public MySearchView(@NonNull Context context) {
        super(context);
        this.mcontext = context;
        initSQL();
    }

    public MySearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;

        initSQL();
    }

    public MySearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext = context;
        initSQL();
    }


    private void initSQL() {


        //绑定搜索框View视图
        // 2. 实例化数据库SQLiteOpenHelper子类对象
        View vv = LayoutInflater.from(mcontext).inflate(R.layout.myview_search, this);
        ce = vv.findViewById(R.id.mysearch_editext);
        SearchList = vv.findViewById(R.id.search_list);
        relay = vv.findViewById(R.id.search_Button);
        deletehis = vv.findViewById(R.id.search_delete_histroy);
        listView = vv.findViewById(R.id.histroy_list);
//        ce.setFocusable(false);//进入页面后先不获取焦点，否则进入页面后会直接调出文字输入框


        ce.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获取到输入框焦点时
                if (hasFocus) {
                    listView.setVisibility(VISIBLE);
//                    queryData("");
                    querySQLKuDate("");
                } else {
                    listView.setVisibility(GONE);
                }
            }
        });

        //监听搜索输入框文本实时变化
        ce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 每次输入后，模糊查询数据库 & 显示
                // 注：若搜索框为空,则模糊搜索空字符 = 显示所有的搜索历史
                String tempName = ce.getText().toString().trim();
                if (!TextUtils.isEmpty(tempName)) {
//                    queryData(tempName); //在数据库中检索结果
                    querySQLKuDate(tempName);
                }

            }


        });


        //搜索按钮点击事件
        relay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(ce.getText().toString().trim())) {
                    Toast.makeText(mcontext, "The search cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (hasHisDate(ce.getText().toString().trim()) == true) {
                    Toast.makeText(mcontext, "The search is already in the history!", Toast.LENGTH_SHORT).show();
                    return;
                }
                insertData(ce.getText().toString().trim());
//                queryData("");//模糊查询所有搜索记录
                querySQLKuDate(ce.getText().toString().trim());
                listView.setVisibility(VISIBLE);
            }
        });


        //清除搜索历史记录
        deletehis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteSQLData();
            }
        });


        helper = new RecordSQLiteOpenHelper(mcontext);


    }


    //查询数据库
    private void queryData(String tempName) {

        //1.模糊查询 // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                //myaccount:表名  name：字段名
                "select id as _id,name from myaccount where name like '%" + tempName + "%' order by id desc ", null);


        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(mcontext, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        SearchList.setAdapter(adapter);  // 4. 历史搜索记录
        adapter.notifyDataSetChanged();


        //搜索结果，子Item点击事件
        SearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                ce.setText(tv.getText());
            }
        });


    }

    //在库中查询已存放的name数据
    private void querySQLKuDate(String tempName) {

        //1.模糊查询 // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                //myaccount:表名  name：字段名
                "select id as _id,name from sqlku where name like '%" + tempName + "%' order by id desc ", null);


        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(mcontext, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        SearchList.setAdapter(adapter);  // 4. 历史搜索记录
        adapter.notifyDataSetChanged();


        //搜索结果，子Item点击事件
        SearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                ce.setText(tv.getText());
            }
        });

    }


    //往数据库中插入数据
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        //myaccount:表名  name：字段名
        db.execSQL("insert into myaccount(name) values('" + tempName + "')");
        db.close();
    }


    //清空数据库
    private void deleteSQLData() {

        db = helper.getWritableDatabase();
        db.execSQL("delete from myaccount");
        db.close();
        listView.setVisibility(GONE);
    }


    //判断数据库中是否有检索内容
    private boolean hasHisDate(String tempName) {
        // 从数据库中 myaccount表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from myaccount where name =?", new String[]{tempName});
        //  判断是否有下一个
        return cursor.moveToNext();
    }


    //静态类
    public static class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

        private static String name = "accountbook.db"; //数据库名称
        private static Integer version = 1;  //数据库版本

        public RecordSQLiteOpenHelper(Context context) {
            super(context, name, null, version);
        }

        //该方法在没有数据库存情况下才会执行
        @Override
        public void onCreate(SQLiteDatabase db) {
            //建表语句
            String sqlorder = "create table myaccount (id integer primary key autoincrement,name varchar(200))";

            String sqlku = "create table sqlku (id integer primary key autoincrement,name varchar(200))";

            String addsqlku1 = "insert into sqlku(name) values('android')";
            String addsqlku2 = "insert into sqlku(name) values('ios')";
            String addsqlku3 = "insert into sqlku(name) values('Python')";
            String addsqlku4 = "insert into sqlku(name) values('PHP')";
            String addsqlku5 = "insert into sqlku(name) values('JAVA')";
            String addsqlku6 = "insert into sqlku(name) values('CSS')";
            db.execSQL(sqlku);//执行建表语句
            db.execSQL(addsqlku1);
            db.execSQL(addsqlku2);
            db.execSQL(addsqlku3);
            db.execSQL(addsqlku4);
            db.execSQL(addsqlku5);
            db.execSQL(addsqlku6);


            // 打开数据库 & 建立了一个叫 myaccount 的表，里面只有一列name来存储历史记录：
            db.execSQL(sqlorder);//执行建表语句

        }

        //数据库更新时执行该方法
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


}

package com.zhimei.can_delete_listview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import utitls.Chatter;
import widget.MyAdapter;


public class MainActivity extends Activity {
    private LinearLayout linearLayout;
    private TextView textView;

    private ListView listView;
    private List<Chatter> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initView();
    }

    void initView(){
       listView=(ListView)findViewById(R.id.listView);
        datas=new ArrayList<>();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.fkq);

        Chatter chatter=new Chatter(bitmap,"好友","你好","昨天");
        Chatter chatter1=new Chatter(bitmap,"好友","你在干什么","今天下午");
        Chatter chatter2=new Chatter(bitmap,"朋友","hello","昨天");
        Chatter chatter3=new Chatter(bitmap,"亲人","病好了吗","前天");
        Chatter chatter4=new Chatter(bitmap,"老师","作业做完了吗","昨天");
        Chatter chatter5=new Chatter(bitmap,"老师","今天不上课","今天");
        Chatter chatter6=new Chatter(bitmap,"同学","去打球","子阿武");
        Chatter chatter7=new Chatter(bitmap,"同学","去打球","子阿武");
        Chatter chatter8=new Chatter(bitmap,"同学","去打球","子阿武");

        datas.add(chatter);
        datas.add(chatter1);
        datas.add(chatter2);
        datas.add(chatter3);
        datas.add(chatter4);
        datas.add(chatter5);
        datas.add(chatter6);
        datas.add(chatter7);
        datas.add(chatter8);

        MyAdapter adapter=new MyAdapter(this,datas);
        listView.setAdapter(adapter);



    }

}

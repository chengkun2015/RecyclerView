package com.jzhihui.recyclerviewcardview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    String[] strTitle = {"慵懒慢时光", "爵士歌中文", "曾经的的你", "杳无的音信", "粤语的歌", "华语的镜像", "摇滚的Live", "温柔到骨子里"};
    int[] resId = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight};
    private RecyclerView recyclerview;
    private Button btn_add,btn_delete;
    private MyAdapter adapter;
    List<Test> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_delete= (Button) findViewById(R.id.btn_delete);
        initData();


    }

    private void initData() {
        //  final LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        /**
         * 瀑布流效果
         */
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layout);
        recyclerview.setHasFixedSize(true);//适配器内容改变，不会改变RecyclerView的大小
        adapter = new MyAdapter(getData());
        recyclerview.setAdapter(adapter);
        /**
         * 设置显示动画
         */
        recyclerview.setItemAnimator(new DefaultItemAnimator());








        adapter.setItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

//                view.animate().translationZ(15F).setDuration(300).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        view.animate().translationZ(1F).setDuration(500).start();
//                    }
//                }).start();
                view.animate().translationX(15F).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.animate().translationX(1F).setDuration(500).start();
                    }
                }).start();

                deleteRecyclerView(view,position);
            }


        });


        
        
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecyclerView(v, 9);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecyclerView(v, 5);
            }
        });
    }



    private void addRecyclerView(View v, int position) {
        Test item = new Test();
        item.setName("疯狂的摇摆");
        item.setResId(R.drawable.nine);
        data.add(item);
        adapter.notifyItemInserted(position);
    }

    private void deleteRecyclerView(View v, int position) {
        data.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private List<Test> getData() {

        for (int i = 0; i < 8; i++) {
            Test item = new Test();
            item.setName(strTitle[i]);
            item.setResId(resId[i]);
            data.add(item);
        }
        return data;
    }


}

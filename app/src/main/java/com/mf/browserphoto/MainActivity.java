package com.mf.browserphoto;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity  {

    FragmentGrid mFragmentGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("my", "mainactivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载grid， 显示grid中显示多个图片
        mFragmentGrid = new FragmentGrid();
        FragmentTransaction tr= this.getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.frag_root, mFragmentGrid, "grid");
        tr.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public void onBackPressed() {
        /*FragmentManager fm = this.getSupportFragmentManager();
        //fm.popBackStack();
        Log.d("my", "fragments size = " + fm.getFragments().size());

        for(int i=0;i<fm.getFragments().size();fm.getFragments().size()){
            Fragment frag = fm.getFragments().get(i++);
            if(frag!=null) {
                Log.d("my", "tag: " + frag.getTag());
                Log.d("my", "id: " + frag.getId());
            }
        }*/
        FragmentManager fm = this.getSupportFragmentManager();
        Fragment grid = fm.findFragmentByTag("grid");
        Fragment vp = fm.findFragmentByTag("viewpager");

        if(vp!=null &&  vp.isVisible()  ){
            FragmentTransaction tr = fm.beginTransaction();
            tr.hide(vp);
            tr.show(grid);
            tr.commit();
        }else if(grid!=null && grid.isVisible()){
            super.onBackPressed();
        }

    }
}

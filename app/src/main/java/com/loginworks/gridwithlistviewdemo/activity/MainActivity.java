package com.loginworks.gridwithlistviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.loginworks.gridwithlistviewdemo.R;
import com.loginworks.gridwithlistviewdemo.adapter.AnimalAdapter;
import com.loginworks.gridwithlistviewdemo.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mMovieRecycleView;
    private ImageView mImvList, mImvGrid;
    private GridLayoutManager mGridLayoutManager;
    private AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        animalAdapter = new AnimalAdapter(this, Utils.generateMovieItems());
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mMovieRecycleView.setLayoutManager(mGridLayoutManager);
        mMovieRecycleView.setAdapter(animalAdapter);
    }

    private void findViews() {
        mMovieRecycleView = (RecyclerView) findViewById(R.id.rv_movie);
        mImvList = (ImageView) findViewById(R.id.imvList);
        mImvGrid = (ImageView) findViewById(R.id.imvGrid);

        mImvList.setOnClickListener(this);
        mImvGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imvList:
                try {
                    int spanCount = mGridLayoutManager.getSpanCount();
                    if (spanCount != 1) {
                        mGridLayoutManager = new GridLayoutManager(this, 1);
                        mMovieRecycleView.setLayoutManager(mGridLayoutManager);
                        mMovieRecycleView.setAdapter(animalAdapter);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case R.id.imvGrid:
                try {
                    int spanCount = mGridLayoutManager.getSpanCount();
                    if (spanCount != 2) {
                        mGridLayoutManager = new GridLayoutManager(this, 2);
                        mMovieRecycleView.setLayoutManager(mGridLayoutManager);
                        mMovieRecycleView.setAdapter(animalAdapter);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                break;
        }
    }
}

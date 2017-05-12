package com.loginworks.gridwithlistviewdemo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loginworks.gridwithlistviewdemo.R;
import com.loginworks.gridwithlistviewdemo.activity.ShareElementActivity;
import com.loginworks.gridwithlistviewdemo.model.Animal;

import java.util.ArrayList;

/**
 * Created by Ankit Maurya on 12-May-17.
 */

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Animal> animalArrayList;
    private int height, width;

    public AnimalAdapter(Context context, ArrayList<Animal> animalArrayList) {
        this.context = context;
        this.animalArrayList = animalArrayList;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        System.out.println(String.format("Height=%d, Width=%d", height, width));
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_square, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Animal animal = animalArrayList.get(position);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width / 2);
        holder.mCardView.setLayoutParams(params);

        holder.setData(animal, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return animalArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtItem;
        private ImageView imgItem;
        private CardView mCardView;
        private int position;
        private Animal animal;


        public MovieViewHolder(View view) {
            super(view);
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            txtItem = (TextView) view.findViewById(R.id.txtItem);
            mCardView = (CardView) view.findViewById(R.id.movie_cardView);
        }

        public void setListeners() {
            imgItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgItem:

                    Intent intent = new Intent(context, ShareElementActivity.class);
                    intent.putExtra("imageurl", animal.getImageUrl());
                    Pair[] pair = new Pair[1];
                    pair[0] = new Pair<View, String>(imgItem, "transitonImage");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pair);
                    context.startActivity(intent, options.toBundle());

                    break;
                case R.id.movie_cardView:
                    break;
            }
        }


        public void setData(Animal animal, int position) {
            this.txtItem.setText(animal.getImageName());
            this.animal = animal;
            this.position = position;

            Glide.with(context)
                    .load(animal.getImageUrl())
                    .into(this.imgItem);
        }
    }
}

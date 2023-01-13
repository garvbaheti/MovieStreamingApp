package com.example.moviestreamingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviestreamingapp.adapters.MovieAdapter;
import com.example.moviestreamingapp.adapters.MovieItemClickListener;
import com.example.moviestreamingapp.models.Movies;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.models.Slider;
import com.example.moviestreamingapp.adapters.SliderPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slider> lstSliders;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView MoviesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.slider);
        indicator = findViewById(R.id.indicator);
        MoviesRv = findViewById(R.id.movies_rv);

        lstSliders = new ArrayList<>();
        lstSliders.add(new Slider(R.drawable.slide1,"The Wolverine"));
        lstSliders.add(new Slider(R.drawable.slide2,"Fantastic Beasts and Where to Find Them"));
        lstSliders.add(new Slider(R.drawable.slide3,"Taaza Khabar"));
        lstSliders.add(new Slider(R.drawable.slide4,"Chhichhore"));

        SliderPageAdapter adapter = new SliderPageAdapter(this,lstSliders);
        sliderPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderPager,true);


        List<Movies> lstMovies = new ArrayList<>();
        lstMovies.add(new Movies("Moana",R.drawable.moana,R.drawable.moana_wide));
        lstMovies.add(new Movies("Black Panther",R.drawable.blackp,R.drawable.blackp_wide));
        lstMovies.add(new Movies("The Martian",R.drawable.themartian,R.drawable.martian_wide));
        lstMovies.add(new Movies("Freddy",R.drawable.freddy,R.drawable.freddy_wide));
        lstMovies.add(new Movies("Sita Ramam",R.drawable.sitaramam,R.drawable.sita_ramamwide));
        lstMovies.add(new Movies("Monster",R.drawable.monster,R.drawable.monster_wide));

        MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies,this);
        MoviesRv.setAdapter(movieAdapter);
        MoviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onMovieClick(Movies movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this,MovieDetails.class);
        // send movie information to deatil Activity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        //the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());

//        Toast.makeText(this,"item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();

    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<lstSliders.size()-1) {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else
                        sliderPager.setCurrentItem(0);
                }
            });
        }
    }
}
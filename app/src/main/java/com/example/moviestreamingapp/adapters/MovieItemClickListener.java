package com.example.moviestreamingapp.adapters;

import android.widget.ImageView;

import com.example.moviestreamingapp.models.Movies;


public interface MovieItemClickListener {

    void onMovieClick(Movies movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}

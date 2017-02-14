package com.applepluot.flixster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applepluot.flixster.R;
import com.applepluot.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.applepluot.flixster.R.id.tvOverview;
import static com.applepluot.flixster.R.id.tvTitle;

/**
 * Created by achow on 7/31/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView movieImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(tvOverview);
            viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.movieImage);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        assert viewHolder != null;
        assert viewHolder.movieImage != null;
        // Clear out image source
        viewHolder.movieImage.setImageResource(0);
        assert movie != null;
        String imageUri = movie.getPosterPath();
        Picasso.with(getContext()).load(imageUri).into(viewHolder.movieImage);

        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        return convertView;
    }
}

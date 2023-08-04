package sk.dzurikm.yts.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import sk.dzurikm.yts.R;
import sk.dzurikm.yts.adapters.TorrentAdapter;
import sk.dzurikm.yts.models.Movie;

public class MovieDetailBottomSheet extends BottomSheetDialogFragment {
    private Context context;
    private View rootView;
    private Movie movie;
    private boolean more;

    TextView movieHeading,movieRating,movieYear,movieGenres,movieDuration,movieDescription;
    ImageButton seeMoreButton;
    RecyclerView torrentRecycler;
    CardView youtubeTrailer;
    public MovieDetailBottomSheet(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        this.more = false;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        rootView = View.inflate(getContext(), R.layout.movie_detail_layout, null);
        dialog.setContentView(rootView);

        ((View) rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);

        movieHeading =  (TextView) rootView.findViewById(R.id.movieHeading);
        movieGenres =  (TextView) rootView.findViewById(R.id.movieGenres);
        movieRating = (TextView) rootView.findViewById(R.id.movieRating);
        movieYear = (TextView) rootView.findViewById(R.id.movieYear);
        movieDuration = (TextView) rootView.findViewById(R.id.movieDuration);
        movieDescription = (TextView) rootView.findViewById(R.id.movieDescription);
        seeMoreButton = rootView.findViewById(R.id.seeMoreButton);
        torrentRecycler = rootView.findViewById(R.id.torrentRecycler);
        youtubeTrailer = rootView.findViewById(R.id.youtubeTrailer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        torrentRecycler.setLayoutManager(layoutManager);

        movieHeading.setText(movie.getTitle());
        movieRating.setText(String.valueOf(movie.getRating()));
        movieYear.setText(String.valueOf(movie.getYear()));
        movieGenres.setText(String.valueOf(movie.getGenresInString()));
        movieDuration.setText(movie.getFormattedDuration());
        movieDescription.setText(movie.getDescription());
        seeMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSeeMore();
            }
        });
        youtubeTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Link",movie.getTrailerUrl());
                Uri webpage = Uri.parse(movie.getTrailerUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                context.startActivity(intent);
            }
        });

        torrentRecycler.setAdapter(new TorrentAdapter(context,movie.getTorrents(),movie.getTitle()));
    }

    private void toggleSeeMore() {
        final int initialHeight = movieDescription.getHeight();
        final int targetHeight;

        if (more) {
            targetHeight = getResources().getDimensionPixelSize(R.dimen.collapsed_height);
            // Collapse the content
            ObjectAnimator.ofFloat(seeMoreButton, "rotation", 0f).start();
        } else {
            // Expand the content
            movieDescription.measure(View.MeasureSpec.makeMeasureSpec(movieDescription.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            targetHeight = movieDescription.getMeasuredHeight();

            ObjectAnimator.ofFloat(seeMoreButton, "rotation", 180f).start();
        }

        ValueAnimator animator = ValueAnimator.ofInt(initialHeight, targetHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                movieDescription.getLayoutParams().height = value;
                movieDescription.requestLayout();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Clear the height animation
                movieDescription.getLayoutParams().height = targetHeight;
                movieDescription.requestLayout();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(300);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.start();

        more = !more;
    }
}

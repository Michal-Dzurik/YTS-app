package sk.dzurikm.yts.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.views.layouts.NonScrollableGridView;

public class MovieView extends LinearLayout {
    private Context context;
    private ImageView movieImage;
    private TextView movieTitle,movieYear;
    private OnClickListener onClickListener;
    private NonScrollableGridView gridView;

    private int image;
    private String title,imageUrl;
    private int year;

    private MovieDetailBottomSheet movieDetailDialog;
    private Movie movie;
    private FragmentManager fragmentManager;


    public MovieView(Context context) {
        super(context);
        init(context, null);
    }

    public MovieView(Context context, FragmentManager fragmentManager, Movie movie) {
        super(context);
        inflateView(context);

        this.image = movie.getCoverImage();
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.imageUrl = movie.getCoverImageUrl();
        this.movie = movie;
        this.fragmentManager = fragmentManager;
        this.context = context;

        initViews();
        setValues();
    }

    public MovieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MovieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflateView(context);
        this.context = context;

        initViews();

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView);
            image = typedArray.getResourceId(R.styleable.MovieView_movieImage, 0);
            title = typedArray.getString(R.styleable.MovieView_movieTitle);
            year = typedArray.getInteger(R.styleable.MovieView_movieYear,0);
            typedArray.recycle();
        }

        setValues();
    }

    private void initViews() {
        movieImage = findViewById(R.id.movieImage);
        movieTitle = findViewById(R.id.movieTitle);
        movieYear = findViewById(R.id.movieYear);

        movieDetailDialog = new MovieDetailBottomSheet(context);

        movieImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie != null){
                    movieDetailDialog.setMovie(movie);
                    movieDetailDialog.show(fragmentManager,null);
                }
            }
        });

        movieImage.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, movie.getUrl());

                // Title for the chooser dialog
                String chooserTitle = "Share Link via";
                // Create a chooser to show available sharing apps
                Intent chooserIntent = Intent.createChooser(shareIntent, chooserTitle);
                if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(chooserIntent);
                }
                return false;
            }
        });
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.movie_view, this, true);
    }

    private void setValues() {
        if (imageUrl != null){
            loadImage(imageUrl,movieImage);
        }else {
            if (image != 0) {
                setImageResource(image);
            }
        }

        if (title != null) {
            setTitle(title);
        }

        if (year > 1800) {
            setYear(year);
        }
        else movieYear.setText("Undefined");
    }


    private void loadImage(String url,ImageView imageView){
        Glide.with(getContext().getApplicationContext())
                .load(url)   //passing your url to load image.
                .error(R.drawable.ic_image_error)
                .skipMemoryCache(false)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        gridView.reloadMeasurements();
                        return false;
                    }
                })
                .into(imageView);
    }

    public void setImageResource(int resId) {
        movieImage.setImageResource(resId);
    }

    public void setTitle(String text) {
        movieTitle.setText(text);
    }

    public void setYear(int text) {
        movieYear.setText(String.valueOf(text));
    }

    public void setGridView(NonScrollableGridView gridView) {
        this.gridView = gridView;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

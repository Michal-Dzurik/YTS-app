package sk.dzurikm.yts.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.models.Pagination;

public class PaginationView extends LinearLayout {
    private Context context;
    private Pagination pagination;
    private TextView middleText;
    private ImageButton pageBeforeButton,pageAfterButton;
    private OnPageChangeListener onPageChangeListener;

    public interface OnPageChangeListener{
        public void onPageChange(Pagination pagination);
    }

    public PaginationView(Context context) {
        super(context);
        init(context, null);
    }

    public PaginationView(Context context, Pagination pagination) {
        super(context);
        inflateView(context);

        this.context = context;
        this.pagination = pagination;

        init(context, null);
    }

    public PaginationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PaginationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PaginationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflateView(context);
        this.context = context;

        setUpViews();
        setUpEvents();


        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView);
            /*image = typedArray.getResourceId(R.styleable.MovieView_movieImage, 0);
            title = typedArray.getString(R.styleable.MovieView_movieTitle);
            year = typedArray.getInteger(R.styleable.MovieView_movieYear,0);*/
            typedArray.recycle();
        }

        refreshData();

    }

    private void setUpViews() {
        middleText = findViewById(R.id.middleText);

        pageBeforeButton = findViewById(R.id.pageBefore);
        pageAfterButton = findViewById(R.id.pageAfter);
    }

    private void setUpEvents() {
        pageBeforeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pagination.pageNumber--;
                onPageChangeListener.onPageChange(pagination);
            }
        });

        pageAfterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pagination.pageNumber++;
                onPageChangeListener.onPageChange(pagination);
            }
        });
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pagination_view, this, true);
    }

    private void refreshData() {
        if (pagination != null){
            int allPagesCount = pagination.resultsCount % pagination.perPage == 0 ?
                    ((int) (pagination.resultsCount / pagination.perPage)) :
                    ((int) (pagination.resultsCount / pagination.perPage)) + 1;

            middleText.setText(getContext().getString(R.string.pagination_info,pagination.pageNumber, allPagesCount));

            if (pagination.pageNumber == 1){
                pageBeforeButton.setEnabled(false);
                pageBeforeButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.text_grey)));
            }
            else {
                pageBeforeButton.setEnabled(true);
                pageBeforeButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
            }

            if (pagination.pageNumber == allPagesCount){
                pageAfterButton.setEnabled(false);
                pageAfterButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.text_grey)));
            }
            else {
                pageAfterButton.setEnabled(true);
                pageAfterButton.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
            }
        }
    }

    public void setData(Pagination pagination){
        this.pagination = pagination;

        refreshData();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}

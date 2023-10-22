package sk.dzurikm.yts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.models.Torrent;

public class TorrentView extends LinearLayout {
    private Context context;
    private Torrent torrent;
    private TextView torrentName,torrentSize;

    public TorrentView(Context context) {
        super(context);
        init(context, null);
    }

    public TorrentView(Context context, Torrent torrent) {
        super(context);
        inflateView(context);

        this.context = context;

        initViews();
        setValues();
    }

    public TorrentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TorrentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public TorrentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflateView(context);
        this.context = context;

        initViews();

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView);
            /*image = typedArray.getResourceId(R.styleable.MovieView_movieImage, 0);
            title = typedArray.getString(R.styleable.MovieView_movieTitle);
            year = typedArray.getInteger(R.styleable.MovieView_movieYear,0);*/
            typedArray.recycle();
        }

        setValues();
    }

    private void initViews() {
        torrentName = findViewById(R.id.number);
        torrentSize = findViewById(R.id.torrentSize);
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.torrent_view, this, true);
    }

    private void setValues() {
        if (torrent != null){
            torrentName.setText(torrent.getType() + " " + torrent.getQuality());
            torrentSize.setText("(" + torrent.getSize() + ")");
        }
    }

}

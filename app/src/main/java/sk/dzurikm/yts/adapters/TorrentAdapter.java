package sk.dzurikm.yts.adapters;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.helpers.Format;
import sk.dzurikm.yts.models.Torrent;

public class TorrentAdapter extends RecyclerView.Adapter<TorrentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Torrent> torrents;
    private String movieTitle;

    private static final int REQUEST_WRITE_STORAGE = 112;
    public TorrentAdapter(Context context, ArrayList<Torrent> torrents) {
        this.context = context;
        this.torrents = torrents;
    }

    public TorrentAdapter(Context context, ArrayList<Torrent> torrents,String movieTitle) {
        this.context = context;
        this.torrents = torrents;
        this.movieTitle = movieTitle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.torrent_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Torrent torrent = torrents.get(position);

        String torrentNameValue = Format.firstLetterUppercase(torrent.getType()) + " " + torrent.getQuality();

        holder.torrentName.setText(torrentNameValue);
        holder.torrentSize.setText("(" + torrent.getSize() + ")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieTitle != null){
                    startDownload(movieTitle + " " + torrentNameValue,torrent.getUrl());
                }
                else startDownload(torrentNameValue,torrent.getUrl());
            }
        });
    }

    private void startDownload(String fileName,String fileUrl) {
        String sanitizedFileName = Format.sanitizeFileName(fileName);
        Log.d("File downloading",sanitizedFileName + " - " + fileUrl);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(fileUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, sanitizedFileName + ".torrent");
        assert downloadManager != null;
        long downloadId = downloadManager.enqueue(request);

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);

        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_FAILED) {
                @SuppressLint("Range") String reason = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
                Log.e("Download Error", "Download failed: " + reason);
            }
            else if (status == DownloadManager.STATUS_SUCCESSFUL) Toast.makeText(context,fileName + " downloaded successfully",Toast.LENGTH_SHORT).show();
        }
        cursor.close();

    }

    @Override
    public int getItemCount() {
        return torrents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView torrentName,torrentSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            torrentName = itemView.findViewById(R.id.number);
            torrentSize = itemView.findViewById(R.id.torrentSize);
        }
    }
}
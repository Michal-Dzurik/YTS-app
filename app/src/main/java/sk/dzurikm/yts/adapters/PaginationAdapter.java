package sk.dzurikm.yts.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.helpers.Format;
import sk.dzurikm.yts.models.Pagination;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> pageNumbers;
    private String movieTitle;

    private static final int REQUEST_WRITE_STORAGE = 112;
    public PaginationAdapter(Context context, Pagination pagination) {
        this.context = context;
        getPageNumbers(pagination);
    }

    private ArrayList<Integer> getPageNumbers(Pagination pagination) {
        pageNumbers = new ArrayList<>();
        int resultCount = pagination.resultsCount,
            perPage = pagination.perPage;

        int numbers = resultCount % perPage == 0 ?
                ((int) (resultCount / perPage)) :
                ((int) (resultCount / perPage)) + 1;

        Log.d("Numbers","Pagination" + pagination.toString() + " " + numbers);

        for (int i = 0; i < numbers; i++) {
            pageNumbers.add(i+1);
        }

        return pageNumbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pagination_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pageNumber = pageNumbers.get(position);
        holder.number.setText(String.valueOf(pageNumber));
        holder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return pageNumbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
        }
    }
}
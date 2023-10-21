package sk.dzurikm.yts.views.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.constants.Filters;
import sk.dzurikm.yts.models.FilterBundle;
import sk.dzurikm.yts.views.SpinnerView;

public class FilterDialog extends BottomSheetDialogFragment {

    public interface OnDialogDismissListener {
        public void onDismiss(FilterBundle filterBundle);
    }
    private View rootView;
    private Context context;
    private FragmentManager fragmentManager;
    private SpinnerView qualitySpinner,genreSpinner,ratingSpinner,orderBySpinner;
    private Button applyButton;
    private OnDialogDismissListener onDialogDismissListener;
    private FilterBundle filters;
    public FilterDialog(Context context, FragmentManager fragmentManager,FilterBundle filters,OnDialogDismissListener onDialogDismissListener) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.filters = filters;
        this.onDialogDismissListener = onDialogDismissListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        rootView = View.inflate(getContext(), R.layout.filter_dialog_layput, null);
        dialog.setContentView(rootView);

        // Setting background to be transparent because we have rounded corners on background
        ((View) rootView.getParent()).setBackgroundColor(Color.TRANSPARENT);

        init();
    }

    private void init() {
        Log.d("Trace","INIT");
        setUpViews();
        fillSpinners();
        setUpEvents();
    }

    private void setUpViews() {
        qualitySpinner = rootView.findViewById(R.id.qualitySpinner);
        genreSpinner = rootView.findViewById(R.id.genreSpinner);
        ratingSpinner = rootView.findViewById(R.id.ratingSpinner);
        orderBySpinner = rootView.findViewById(R.id.orderSpinner);

        applyButton = rootView.findViewById(R.id.applyFiltersButton);
    }

    private void setUpEvents() {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void fillSpinners(){
        qualitySpinner.setData(Filters.quality);
        genreSpinner.setData(Filters.genres);
        ratingSpinner.setData(Filters.rating);
        orderBySpinner.setData(Filters.filterableFieldsNames);
        orderBySpinner.setValues(Filters.filterableFieldsValues);

        if (filters != null){
            if (filters.quality != null) qualitySpinner.setSelectedData(filters.quality);
            if (filters.genre != null) genreSpinner.setSelectedData(filters.genre);
            if (filters.rating != null) ratingSpinner.setSelectedData(filters.rating);
            if (filters.orderBy != null) orderBySpinner.setSelectedData(filters.orderBy);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        filters.quality = qualitySpinner.getSpinnerValue();
        filters.genre = genreSpinner.getSpinnerValue();
        filters.rating = ratingSpinner.getSpinnerValue();
        filters.orderBy = orderBySpinner.getSpinnerValue();

        onDialogDismissListener.onDismiss(filters);
        super.onDismiss(dialog);
    }
}
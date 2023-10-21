package sk.dzurikm.yts.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.constants.Filters;

public class SpinnerView extends LinearLayout {
    private Context context;
    private String[] data, values;
    private String selectedData;
    private int selectedDataPosition;
    private Spinner spinner;


    public SpinnerView(Context context) {
        super(context);
        init(context, null);
    }

    public SpinnerView(Context context, String[] data) {
        super(context);
        inflateView(context);

        this.context = context;
        this.data = data;

        initViews();
    }

    public SpinnerView(Context context, String[] data,String[] values) {
        super(context);
        inflateView(context);

        this.context = context;
        this.data = data;
        this.values = values;

        initViews();
    }

    public SpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SpinnerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflateView(context);
        this.context = context;

        initViews();

        /*if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView);

            typedArray.recycle();
        }*/

        if (data != null) fillUpSpinner();
    }

    private void fillUpSpinner(){
        Log.d("Trace","Fill spinner up");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner);

        setUpEvents();
    }

    private void setUpEvents() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedData = (String) parentView.getItemAtPosition(position);
                selectedDataPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case when nothing is selected, if needed.
            }
        });
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.spinner_layout, this, true);
    }

    public String getSpinnerValue(){
        if (values != null){
            return values[selectedDataPosition].equals(Filters.ALL) ? null : values[selectedDataPosition];
        }
        else return selectedData.equals(Filters.ALL) ? null : selectedData;
    }

    public void setData(String[] data) {
        this.data = data;
        if (data != null || data.length != 0) fillUpSpinner();
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public void setSelectedData(String toSelect){
        selectedDataPosition = getItemPosition(toSelect);
        selectedData = toSelect;
        spinner.setSelection(selectedDataPosition);
    }

    private int getItemPosition(String item){
        String[] arr = (values != null ? values : data);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) return i;
        }

        return -1;
    }
}

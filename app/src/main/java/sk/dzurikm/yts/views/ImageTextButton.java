package sk.dzurikm.yts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import sk.dzurikm.yts.R;

public class ImageTextButton extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    private LinearLayout button;
    private OnClickListener onClickListener;

    public ImageTextButton(Context context) {
        super(context);
        init(context, null);
    }

    public ImageTextButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageTextButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ImageTextButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_text_button, this, true);

        imageView = findViewById(R.id.buttonImage);
        textView = findViewById(R.id.buttonTitle);
        button = findViewById(R.id.button);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextButton);
            int imageResId = typedArray.getResourceId(R.styleable.ImageTextButton_imageSrc, 0);
            String text = typedArray.getString(R.styleable.ImageTextButton_title);
            typedArray.recycle();

            if (imageResId != 0) {
                setImageResource(imageResId);
            }

            if (text != null) {
                setTextViewText(text);
            }
        }
    }

    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public void setTextViewText(String text) {
        textView.setText(text);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

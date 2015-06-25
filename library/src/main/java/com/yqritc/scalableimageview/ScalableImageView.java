package com.yqritc.scalableimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yqritc on 2015/06/18.
 */
public class ScalableImageView extends ImageView {

    protected ScalableType mScalableType = ScalableType.NONE;

    public ScalableImageView(Context context) {
        this(context, null);
    }

    public ScalableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs == null) {
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.scaleStyle, 0, 0);
        if (a == null) {
            return;
        }

        int scaleType = a.getInt(R.styleable.scaleStyle_scalableType, ScalableType.NONE.ordinal());
        a.recycle();
        mScalableType = ScalableType.values()[scaleType];
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        scaleImageSize();
    }

    public void setScalableType(ScalableType scalableType) {
        setScaleType(ScaleType.MATRIX);
        mScalableType = scalableType;
    }

    private void scaleImageSize() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        int imageWidth = drawable.getIntrinsicWidth();
        int imageHeight = drawable.getIntrinsicHeight();
        if (imageWidth <= 0 || imageHeight <= 0) {
            return;
        }

        int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        if (viewWidth <= 0 || viewHeight <= 0) {
            return;
        }

        Size viewSize = new Size(viewWidth, viewHeight);
        Size imageSize = new Size(imageWidth, imageHeight);

        ScaleManager scaleManager = new ScaleManager(viewSize, imageSize);
        Matrix matrix = scaleManager.getScaleMatrix(mScalableType);
        if (matrix != null) {
            setImageMatrix(matrix);
        }
    }
}

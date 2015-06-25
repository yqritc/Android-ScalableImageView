package com.yqritc.scalableimageview.sample;

import com.yqritc.scalableimageview.ScalableImageView;
import com.yqritc.scalableimageview.ScalableType;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yqritc on 2015/06/14.
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {

    @DrawableRes
    private int mImageResId;
    private LayoutInflater mLayoutInflater;

    public SampleAdapter(Context context) {
        super();
        mImageResId = R.drawable.landscape_sample;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_sample_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        ScalableType scalableType = ScalableType.values()[position];
        holder.mTextView.setText(context.getString(R.string.sample_scale_title, position,
                scalableType.toString()));

        holder.mImageView.setImageResource(mImageResId);
        holder.mImageView.setScalableType(scalableType);
        holder.mImageView.requestLayout();
    }

    @Override
    public int getItemCount() {
        return ScalableType.values().length;
    }

    public void setImageResId(@DrawableRes int id) {
        mImageResId = id;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ScalableImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.title_text);
            mImageView = (ScalableImageView) view.findViewById(R.id.image_view);
        }
    }
}

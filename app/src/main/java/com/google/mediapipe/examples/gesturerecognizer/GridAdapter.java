package com.google.mediapipe.examples.gesturerecognizer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] signLanguageName;
    int[] image;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] signLanguageName, int[] image) {
        this.context = context;
        this.signLanguageName = signLanguageName;
        this.image = image;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return signLanguageName.length;
    }

    @Override
    public Object getItem(int position) {
        return signLanguageName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.grid_image);
            holder.textView = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(image[position]);
        holder.textView.setText(signLanguageName[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, FullScreenImageActivity.class);
                intent.putExtra(FullScreenImageActivity.EXTRA_IMAGE_RES_ID, image[position]);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}


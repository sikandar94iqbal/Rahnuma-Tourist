package com.example.sikandar.rahnuma_tourist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sikandar on 4/18/2017.
 */

public class weatherAdapter extends ArrayAdapter<weather2> {

    Context context;
    int layoutResourceId;
    ArrayList<weather2> checkedobjects = new ArrayList<weather2>();
    ArrayList<weather2> data = new ArrayList<weather2>();

    public weatherAdapter(Context context, int layoutResourceId, ArrayList<weather2> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        weatherHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new weatherHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.checkBox = (CheckBox) row.findViewById(R.id.checkBox);

            row.setTag(holder);
        } else {
            holder = (weatherHolder) row.getTag();
        }

        // weather weather = data[position];
        holder.txtTitle.setText(data.get(position).get_name());

        holder.checkBox.setOnCheckedChangeListener(myCheckChangList);
        holder.checkBox.setTag(position);
        // holder.checkBox.setChecked(p.box);


        Uri myUri = Uri.parse(data.get(position).get_pic().toString());

        //String picture = "https://sikandariqbal.net/Rahnuma/images/pic1.jpg";
        String picture = data.get(position).get_pic().toString();
        new DownLoadImageTask(holder.imgIcon).execute(picture);

        //  holder.imgIcon.setImageBitmap(mIcon_val);

        // holder.imgIcon.setImageResource();

        return row;
    }

    static class weatherHolder {
        ImageView imgIcon;
        TextView txtTitle;
        CheckBox checkBox;
    }

    weather2 getProduct(int position) {
        return (getItem(position));
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {

                checkedobjects.add(getProduct((Integer) buttonView.getTag()));

            } else {
                checkedobjects.remove(getProduct((Integer) buttonView.getTag()));

            }
        }
    };


    public ArrayList<weather2> getSelectedData() {
        return checkedobjects;
    }


}


class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected Bitmap doInBackground(String... urls) {
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try {
            InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            logo = BitmapFactory.decodeStream(is);
        } catch (Exception e) { // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}

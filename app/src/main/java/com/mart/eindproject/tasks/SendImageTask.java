package com.mart.eindproject.tasks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mart.eindproject.util.PokemonUtil;

import java.io.InputStream;

public class SendImageTask  extends AsyncTask<String, Void, Bitmap> {

    private final View v;

    public SendImageTask(View v) {
        this.v = v;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
        String path = MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(), result, "", null);
        Uri screenshotUri = Uri.parse(path);

        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        intent.setType("image/*");
        PokemonUtil.getActivity(v).startActivity(Intent.createChooser(intent, "Share image via..."));
    }
}
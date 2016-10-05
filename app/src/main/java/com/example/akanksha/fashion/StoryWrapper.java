package com.example.akanksha.fashion;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import com.example.akanksha.fashion.util.RoposoUtil;

/**
 * Created by akanksha on 02/04/16.
 */
public class StoryWrapper {
    private StoryObject story;
        private Bitmap imageBitmap = null;
        private CustomAdapter custA;

        public StoryWrapper(StoryObject sd) {
            this.story = sd;
            // TO BE LOADED LATER - OR CAN SET TO A DEFAULT IMAGE
            this.imageBitmap = null;
        }

        public StoryObject getStory() {
            return story;
        }

        public void setStory(StoryObject story) {
            this.story = story;
        }

        public Bitmap getImagebitmap() {
            return imageBitmap;
        }

        public CustomAdapter getAdapter() {
            return custA;
        }

        public void setAdapter(CustomAdapter sta) {
            this.custA = sta;
        }

        public void loadImage(CustomAdapter sta) {
            // HOLD A REFERENCE TO THE ADAPTER
            this.custA = sta;
            if (story.getSi() != null && !story.getSi().equals("")) {
                new ImageLoadTask().execute(story.getSi());
            }
        }

        // ASYNC TASK TO AVOID CHOKING UP UI THREAD
        private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

            @Override
            protected void onPreExecute() {
                Log.i("ImageLoadTask", "Loading image...");
            }

            // PARAM[0] IS IMG URL
            protected Bitmap doInBackground(String... param) {
                Log.i("ImageLoadTask", "Attempting to load image URL: " + param[0]);
                try {
                    Bitmap b = RoposoUtil.getBitmapFromURL(param[0]);
                    return b;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            protected void onPostExecute(Bitmap ret) {
                if (ret != null) {
                    Log.i("ImageLoadTask", "Successfully loaded " + story.getId() + " image");
                    imageBitmap = ret;
                    if (custA != null) {
                        // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
                        custA.notifyDataSetChanged();
                    }
                } else {
                    Log.e("ImageLoadTask", "Failed to load " + story.getId() + " image");
                }
            }
        }
}

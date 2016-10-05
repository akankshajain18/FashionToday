package com.example.akanksha.fashion.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.akanksha.fashion.AppController;
import com.example.akanksha.fashion.FashionDiva;
import com.example.akanksha.fashion.StoryObject;
import com.example.akanksha.fashion.StoryWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by akanksha on 30/03/16.
 */
public class RoposoUtil {
    static ArrayList list_story = null;

    //public static Hashtable<String, FashionDiva> readJsonFile_(Context context) throws IOException
    public static ArrayList readJsonFile_(Context context) throws IOException
    {
        list_story = new ArrayList<StoryWrapper>();
        Hashtable<String, StoryObject> hashtbl_story  =  AppController.getInstance().getStory();
        Hashtable<String, FashionDiva> hashtbl_author  =  AppController.getInstance().getAuthor();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("Story.json")));
            Gson gson = new GsonBuilder().create();
            StoryObject[] storyD = gson.fromJson(reader, StoryObject[].class);
            for(int i =0;i<storyD.length;i++) {
                // Think of putting this in HashTable
                list_story.add(new StoryWrapper(storyD[i]));
                hashtbl_story.put(storyD[i].getId(), storyD[i]);
            }

            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("author.json")));
            gson = new GsonBuilder().create();
            FashionDiva[] fashionDiva = gson.fromJson(reader, FashionDiva[].class);
            for(int i =0;i<fashionDiva.length;i++) {
                // Think of putting this in HashTable
                hashtbl_author.put(fashionDiva[i].getId(), fashionDiva[i]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getCause());
        }

        // printHashtable(hashtbl_fashion);
        AppController.getInstance().setHm(hashtbl_story);
        AppController.getInstance().setAuthor(hashtbl_author);
        return list_story;
    }


    public static void printStoryMap(Hashtable<String, StoryObject> hm) {

        Set<String> mykeys = hm.keySet();
        Iterator iter = mykeys.iterator();
        int i = 0;
        while (iter.hasNext()) {

            String key = (String) iter.next();
            Log.d("Hashtable Record#", "" + i);

            Log.d("Hashtable content:", hm.get(key).getDb());
            Log.d("Hashtable content:", hm.get(key).getDescription());
            Log.d("Hashtable content:", hm.get(key).getSi());
            Log.d("Hashtable content:", hm.get(key).getTitle());
            Log.d("Hashtable content:", hm.get(key).getType());
            Log.d("Hashtable content:", "" + hm.get(key).getComment_count());
            Log.d("Hashtable content:", "" + hm.get(key).getLikes_count());
            i++;
        }
    }

    public static void printAuthorMap(Hashtable<String, FashionDiva> hm) {

        Set<String> mykeys = hm.keySet();
        Iterator iter = mykeys.iterator();
        int i = 0;
        while (iter.hasNext()) {

            String key = (String) iter.next();
            Log.d("Hashtable Record#", "" + i);

            Log.d("Hashtable content:", hm.get(key).getAbout());
            Log.d("Hashtable content:", hm.get(key).getHandle());
            Log.d("Hashtable content:", hm.get(key).getId());

            Log.d("Hashtable content:", hm.get(key).getImage());
            Log.d("Hashtable content:", hm.get(key).getUrl());
            Log.d("Hashtable content:", hm.get(key).getUsername());
            Log.d("Hashtable content:", ""+hm.get(key).getFollowers());

            Log.d("Hashtable content:", ""+hm.get(key).getFollowing());
            i++;
        }
    }



    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

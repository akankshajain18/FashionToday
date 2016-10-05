package com.example.akanksha.fashion;

/**
 * Created by akanksha on 01/04/16.
 */

import android.app.Application;
import java.util.Hashtable;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public Hashtable<String, StoryObject> stories = new Hashtable<String, StoryObject>();
    public Hashtable<String, FashionDiva> author = new Hashtable<String, FashionDiva>();
    private static AppController Instance;

    public static synchronized AppController getInstance() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public Hashtable<String, StoryObject> getStory() {
        return stories;
    }

    public Hashtable<String, FashionDiva> getAuthor() {
        return author;
    }

    public void setHm(Hashtable<String, StoryObject> hm) {
        this.stories = stories;
    }

    public void setAuthor(Hashtable<String, FashionDiva> author) {
        this.author = author;
    }
}
package com.example.akanksha.fashion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.akanksha.fashion.CustomAdapter.ViewHolder;
import com.example.akanksha.roposo.R;
import com.example.akanksha.fashion.util.RoposoUtil;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity implements OnItemClickListener{
    private Context mContext;
    ArrayList<StoryWrapper> list_story = null;
    ListView l = null;
    private CustomAdapter customA;
    public static final int FOLLOW_CHANGED = 10001;
    private static final String TAG =CustomAdapter.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=getBaseContext();
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        l = (ListView)findViewById(R.id.list);
        try {
            list_story = RoposoUtil.readJsonFile_(mContext);
            customA = new CustomAdapter(mContext, list_story);
            l.setAdapter(customA);
            for (StoryWrapper sw : list_story) {
                // START LOADING IMAGES FOR EACH STORY
                sw.loadImage(customA);
            }

            l.setOnItemClickListener(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" E :" +e);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        else if(id==android.R.id.home)
        {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putParcelable("story", list_story.get(position).getStory()); // Here we are sending full object of each story instead of this we can only send id of the story, since we are maintaining each story object in hashmap.
        i.putExtras(b);
        i.setClass(getApplicationContext(), StoryDescription.class);
        startActivityForResult(i,FOLLOW_CHANGED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==FOLLOW_CHANGED)
        {
            if(resultCode==RESULT_OK)
            {
                if(data!=null&&list_story!=null) {
                    int intitalIndex = l.getFirstVisiblePosition();
                    int lastVisibleIndex = l.getLastVisiblePosition();
                    String id = data.getStringExtra("author_id");
                    boolean is_follow = AppController.getInstance().getAuthor().get(id).is_following();
                    Log.d(TAG, "intitalIndex, lastVisibleIndex : " + intitalIndex + lastVisibleIndex);
                    View view;
                    ViewHolder vh;
                    for(int i =0; i<=lastVisibleIndex-intitalIndex ;i++) {
                        view = l.getChildAt(i);
                        vh = (ViewHolder) view.getTag();
                        if ((list_story.get(i+intitalIndex).getStory().getDb()).equals(id)) {
                            Log.d(TAG, i + "index matched");
                            if (is_follow)
                                vh.follow.setText(mContext.getString(R.string.unfollow));
                            else
                                vh.follow.setText(mContext.getString(R.string.follow));
                        }

                    }//End of for loop

                }// End of Data if
            }// End of Result if
        }
    }
}
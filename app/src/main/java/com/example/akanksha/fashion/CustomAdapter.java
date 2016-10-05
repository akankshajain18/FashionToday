package com.example.akanksha.fashion;

/**
 * Created by akanksha on 30/03/16.
 */


import android.content.Context;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.example.akanksha.roposo.R;

import java.util.ArrayList;

//@SuppressLint("InflateParams")
public class CustomAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<StoryWrapper> list;
    private static final String TAG =CustomAdapter.class.getSimpleName();


    public CustomAdapter(Context context, ArrayList<StoryWrapper> list) {
        mContext= context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        Log.d(TAG, "GET View Called : " + position);
        ViewHolder holder;

        StoryWrapper sw = list.get(position);
        StoryObject sd = sw.getStory();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_row, null);
            holder.follow = (Button) convertView.findViewById(R.id.btn_follow_main);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.pic = (ImageView) convertView.findViewById(R.id.iv_Pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String title = "";
        if(sd.getTitle()!=null && !sd.getTitle().isEmpty())
            title = sd.getTitle();

        String username;
        final FashionDiva f = AppController.getInstance().getAuthor().get(sd.getDb());
        if(f!=null)
            username =  " by " + f.getUsername();
        else
            username =  " by " + "Author Unknown";

        /*parent.invalidate();
        parent.refreshDrawableState();
        */
        if ( sw.getImagebitmap() != null){
            holder.pic.setImageBitmap(sw.getImagebitmap());
        } else {
            holder.pic.setImageResource(R.drawable.noimg);
        }

        // Color the text.
        holder.title.setText(title + username, BufferType.SPANNABLE);
        Spannable s = (Spannable)holder.title.getText();
        int start = title.length();
        int end = start + username.length();
        s.setSpan(new ForegroundColorSpan(0xFFFF0000), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button b= (Button)v;
                //Toast.makeText(parent.getContext(), "Position of item clicked" + position, Toast.LENGTH_SHORT).show();
                    FashionDiva tAuthor = AppController.getInstance().getAuthor().get(list.get(position).getStory().getDb());
                    tAuthor.setIs_following(!tAuthor.is_following());
                    AppController.getInstance().getAuthor().put(tAuthor.getId(), tAuthor);
                    int intitalIndex = ((ListView)parent).getFirstVisiblePosition();
                    int lastVisibleIndex= ((ListView)parent).getLastVisiblePosition();
                    Log.d(TAG, "intitalIndex, lastVisibleIndex : " + intitalIndex + " " + lastVisibleIndex);
                    View view;
                    ViewHolder vh;
                    for(int i =0; i<=lastVisibleIndex- intitalIndex;i++)
                    {
                        int currentIndex = i +intitalIndex;
                        view= ((ListView)parent).getChildAt(i);
                        vh=(ViewHolder)view.getTag();
                        if((list.get(currentIndex).getStory().getDb()).equals(tAuthor.getId())) {
                            Log.d(TAG, i + "index matched");
                            if (tAuthor.is_following())
                                vh.follow.setText(mContext.getString(R.string.unfollow));
                            else
                                vh.follow.setText(mContext.getString(R.string.follow));
                        }

                    }
            }
        });

        if( f!=null && f.is_following() ) { // following : display unfollow
            holder.follow.setText(R.string.unfollow);
        } else if(f!= null) {
            holder.follow.setText(R.string.follow); //Not Following : show display follow
        } else {
            holder.follow.setVisibility(View.GONE); // As no author present
        }

        return convertView;
    }

    public static class ViewHolder {
        TextView title;
        Button follow;
        ImageView pic;
    }

}

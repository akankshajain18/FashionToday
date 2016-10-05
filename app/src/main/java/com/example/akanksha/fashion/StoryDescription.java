package com.example.akanksha.fashion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.akanksha.roposo.R;

public class StoryDescription extends Activity {

    TextView title, author, description, about;
    Button follow;
    boolean isChanged= false;
    FashionDiva fAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_description);
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        title= (TextView) findViewById(R.id.tv_titledetail);
        author = (TextView) findViewById(R.id.tv_authorname);
        description=(TextView)findViewById(R.id.tv_descriptiondetail);
        follow = (Button) findViewById(R.id.btn_follow);
        about = (TextView)findViewById(R.id.tv_aboutdetail);
        Bundle b = this.getIntent().getExtras();
        final StoryObject sd = b.getParcelable("story"); // Author
        //Set Title
        if(sd.getTitle()!=null&&!sd.getTitle().isEmpty())
            title.setText(sd.getTitle());
        else
            title.setText("Unknown Story");

        //Set Author
        fAuthor = AppController.getInstance().getAuthor().get(sd.getDb());
        String authorName= "";
        if(fAuthor!=null) {
            authorName = fAuthor.getUsername();
            if (!authorName.isEmpty())
                author.setText(authorName);
            else
                author.setText("Unknown Author");

            //Set About Author
            String aboutAuthor = fAuthor.getAbout();
            if (aboutAuthor != null && !aboutAuthor.isEmpty())
                about.setText(aboutAuthor);
            else
                findViewById(R.id.view_aboutauthor).setVisibility(View.GONE);

            //Following Button
            if(fAuthor.is_following())
                follow.setText(getString(R.string.unfollow));
            else
                follow.setText(getString(R.string.follow));

            follow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    isChanged = !isChanged;
                    fAuthor.setIs_following(!fAuthor.is_following());
                    if(fAuthor.is_following())
                        follow.setText(getString(R.string.unfollow));
                    else
                        follow.setText(getString(R.string.follow));
                }
            });

        }

        //Set Description
        if(sd.getDescription()!=null&&!sd.getDescription().isEmpty())
            description.setText(sd.getDescription());
        else {
            findViewById(R.id.tv_desription).setVisibility(View.GONE);
            findViewById(R.id.view_afterDescription).setVisibility(View.GONE);
            description.setVisibility(View.GONE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story_description, menu);
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
            this.finishThisActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void finishThisActivity()
    {
        Intent i = new Intent();
        i.putExtra("author_id",fAuthor.getId());

        if(isChanged)
            setResult(RESULT_OK,i);
        else
            setResult(RESULT_CANCELED);

        finish();


    }

    @Override
    public void onBackPressed() {
        finishThisActivity();
        super.onBackPressed();
    }
}


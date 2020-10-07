package com.example.matrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements EventFragment.OnItemSelectListener,
        CommentFragment.OnItemSelectListener{
    private EventFragment listFragment;
    private CommentFragment gridFragment;

    @Override
    public void onCommentSelected(int position) {
        listFragment.onItemSelected(position);
    }


    @Override
    public void onItemSelected(int position) {
        if (!isTablet()) {
            Fragment fragment = CommentFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.event_container, fragment).addToBackStack(null).commit();
        } else {
            gridFragment.onItemSelected(position);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Show different fragments based on screen size.
        //if (findViewById(R.id.fragment_container) != null) {
//        if (savedInstanceState == null) {
//            Fragment fragment = isTablet() ? new  CommentFragment() : new EventFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
        //add list view
        if (getSupportFragmentManager().findFragmentById(R.id.event_container) == null) {
            listFragment = new EventFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.event_container, listFragment).commit();
        }

        //add Gridview
        if (getSupportFragmentManager().findFragmentById(R.id.comment_container) == null && isTablet()) {
            gridFragment = new CommentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.comment_container, gridFragment).commit();
        }

//
//        // Get ListView object from xml.
//        ListView eventListView = (ListView) findViewById(R.id.event_list);
//
//        // Initialize an adapter.
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
////                this,
////                R.layout.event_item,
////                R.id.event_name,
////                getEventNames());
//
//        // Assign adapter to ListView.
//        EventAdapter adapter = new EventAdapter(this);
//        eventListView.setAdapter(adapter);
    }
    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    /**
     * A dummy function to get fake event names.
     */
//    private String[] getEventNames() {
//        String[] names = {
//                "Event1", "Event2", "Event3",
//                "Event4", "Event5", "Event6",
//                "Event7", "Event8", "Event9",
//                "Event10", "Event11", "Event12"};
//        return names;
//    }


}

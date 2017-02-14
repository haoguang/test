package com.example.lawre.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawre.myapplication.FriendManagement.FriendListFragment;
import com.example.lawre.myapplication.entity.User;
import com.example.lawre.myapplication.others.CircleTransform;
import com.example.lawre.myapplication.others.SessionManagement;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MeetingApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManagement session;
    private TextView userName;
    private TextView userEmail;
    private ImageView profilePic;

    private User user;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_app);

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        //retrieve user information and set value to the nav header
        user = session.getUserDetails();
        setValueToNavHeader();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        UpcomingMeeting upcomingMeeting = new UpcomingMeeting();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relative_layout_fragment, upcomingMeeting).commit();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setValueToNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        userName = (TextView) header.findViewById(R.id.userName);
        userEmail = (TextView) header.findViewById(R.id.userEmail);
        profilePic = (ImageView) header.findViewById(R.id.userProfilePic);


        userName.setText(user.getUsername());
        userEmail.setText(user.getEmail());
        Picasso.with(this)
                .load(user.getProfilePic())
                .transform(new CircleTransform())
                .resize(250, 250)
                .centerInside()
                .into(profilePic);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                //close the app when the back button is pressed twice
                finish();
                System.exit(0);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meeting_app, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Organization_Meeting) {


            OrganizationMeeting organizationMeeting = new OrganizationMeeting();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment,organizationMeeting ).commit();


            // Handle the camera action
        } else if (id == R.id.Meeting_History) {
            MeetingHistory meetingHistory = new MeetingHistory();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment, meetingHistory).commit();

        } else if (id == R.id.Notifcation) {
            NotificationActivity notification = new NotificationActivity();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment,notification).commit();

        } else if (id == R.id.Log_Out) {
            session.logoutUser();
        } else if (id == R.id.upcoming_meeting) {
            UpcomingMeeting upcomingMeeting = new UpcomingMeeting();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment, upcomingMeeting).commit();

        } else if (id == R.id.nav_friend){
            Bundle bundle = new Bundle();
            bundle.putString("userID", user.getUserID());
            FriendListFragment friendlistFragment = new FriendListFragment();
            friendlistFragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment, friendlistFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void viewMeetingDetail(View v) {

        Intent intent = new Intent(getApplicationContext(), MeetingInfo.class);
        startActivity(intent);

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MeetingApp Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

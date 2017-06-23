package cn.bjtu.activity;

import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import cn.bjtu.R;




public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {





    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_base, null);
        FrameLayout frameLayout = (FrameLayout) drawerLayout.findViewById(R.id.content_frame);
        // 将传入的layout加载到activity_base的content_frame里面
        View view = getLayoutInflater().inflate(layoutResID, frameLayout, true);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
           // DrawerLayout drawer = (DrawerLayout) drawerLayout.findViewById(R.id.drawer_layout);
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        NavigationView navigationView = (NavigationView) drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        super.setContentView(drawerLayout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        int id = item.getItemId();


        if (id == R.id.login) {
            navigationView.getMenu().setGroupVisible(R.id.login_menu, true);
            navigationView.getMenu().setGroupVisible(R.id.guest_menu, false);
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

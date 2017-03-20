package com.boommba.mvpexample.app.main;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.boommba.mvpexample.app.R;
import com.boommba.mvpexample.app.custom.ResideMenu;
import com.boommba.mvpexample.app.custom.ResideMenuItem;
import com.boommba.mvpexample.app.main.posts.menuitems.CalendarFragment;
import com.boommba.mvpexample.app.main.posts.menuitems.Feed;
import com.boommba.mvpexample.app.main.posts.menuitems.ProfileFragment;
import com.boommba.mvpexample.app.main.posts.menuitems.SettingsFragment;


public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem itemHome,itemHome1,itemHome2;
    private ResideMenuItem itemProfile,itemProfile1,itemProfile2;
    private ResideMenuItem itemCalendar,itemCalendar1,itemCalendar2;
    private ResideMenuItem itemSettings,itemSettings1,itemSettings2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.reside_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new Feed());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.yellow_theme);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.7f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.ic_launcher,     "Home");
        itemProfile  = new ResideMenuItem(this,  R.drawable.ic_launcher,  "Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.ic_launcher, "Calendar");
        itemSettings = new ResideMenuItem(this,  R.drawable.ic_launcher, "Settings");

        itemHome1     = new ResideMenuItem(this,  R.drawable.ic_launcher,     "Home");
        itemProfile1  = new ResideMenuItem(this,  R.drawable.ic_launcher,  "Profile");
        itemCalendar1 = new ResideMenuItem(this, R.drawable.ic_launcher, "Calendar");
        itemSettings1 = new ResideMenuItem(this,  R.drawable.ic_launcher, "Settings");

        itemHome2     = new ResideMenuItem(this,  R.drawable.ic_launcher,     "Home");
        itemProfile2  = new ResideMenuItem(this, R.drawable.ic_launcher,  "Profile");
        itemCalendar2 = new ResideMenuItem(this,  R.drawable.ic_launcher, "Calendar");
        itemSettings2 = new ResideMenuItem(this,  R.drawable.ic_launcher, "Settings");


        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings2, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new Feed());
        }else if (view == itemProfile){
            changeFragment(new ProfileFragment());
        }else if (view == itemCalendar){
            changeFragment(new CalendarFragment());
        }else if (view == itemSettings){
            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(final Fragment targetFragment){
        resideMenu.clearIgnoredViewList();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, targetFragment, "fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();

            }
        }, 200);
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}

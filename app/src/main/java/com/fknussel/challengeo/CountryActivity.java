package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;


public class CountryActivity extends ActionBarActivity {

    private Country country;
    private int selectedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        setContentView(R.layout.activity_country_info);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CountryInfoFragmentPagerAdapter(getSupportFragmentManager()));

        viewPager.getCurrentItem();

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        // We start off positioned on the first tab
        this.selectedTab = 0;

        // Attach the page change listener to tab strip and **not** the view pager inside the activity
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

                Fragment currentFragment;

                selectedTab = position;

                currentFragment = getSupportFragmentManager()
                        .findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + position);

                // Could be null if not instantiated yet
                if (currentFragment != null) {

                    // No need to call if fragment's onDestroyView() has since been called
                    if (currentFragment.getView() != null) {
                        Updatable updatable = (Updatable) currentFragment;
                        updatable.updateDisplay();
                    }
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return this.country;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_country_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_random) {

            // Get Country
            String name = AppHelper.getRandomCountryName();
            int index = AppHelper.listNames.indexOf(name);
            Country country = AppHelper.listCountries.get(index);

            // Register the new random country within the hosting activity
            setCountry(country);

            setTitle(country.getName());

            Fragment currentFragment = getSupportFragmentManager()
                    .findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + selectedTab);

            Updatable updatable = (Updatable) currentFragment;
            updatable.updateDisplay();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

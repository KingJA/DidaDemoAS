package com.dida.first.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dida.first.R;
import com.dida.first.fragment.Store_All_Fr;
import com.dida.first.fragment.Store_Home_Fr;
import com.dida.first.fragment.Store_Sort_Fr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-10-27.
 */
public class Store_Activity extends AppCompatActivity {

    private ViewPager view_pager;
    private TabLayout tab_layout;
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    private List<String> tabList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.nav_icon_2);
        ab.setDisplayHomeAsUpEnabled(true);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tabList.add("首页");
        tabList.add("全部");
        tabList.add("分类");
        tab_layout.addTab(tab_layout.newTab().setText(tabList.get(0)));
        tab_layout.addTab(tab_layout.newTab().setText(tabList.get(1)));
        tab_layout.addTab(tab_layout.newTab().setText(tabList.get(2)));
        fragmentList.add(new Store_Home_Fr());
        fragmentList.add(new Store_All_Fr());
        fragmentList.add(new Store_Sort_Fr());

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        view_pager.setAdapter(fragmentAdapter);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tab_layout.setupWithViewPager(view_pager);
        tab_layout.setTabsFromPagerAdapter(fragmentAdapter);

    }

    class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        显示搜索框
//        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("query", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("newText",newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Log.i("action_search","action_search");
                return true;
            case android.R.id.home:
                Log.i("home","home");
                return true;
            case R.id.action_share:
                Log.i("action_share","action_share");
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

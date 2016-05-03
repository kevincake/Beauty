package cn.ifreedomer.beauty.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.ViewPagerFragmentAdapter;
import cn.ifreedomer.beauty.fragment.CourseFragment;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    @Bind(R.id.tabHost)
    MaterialTabHost tabHost;
    @Bind(R.id.pager)
    ViewPager pager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    Resources res = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        res = getResources();
        initFragments();
        initPageAndTabHost();

    }

    private void initPageAndTabHost() {
        // init view pager
        ViewPagerFragmentAdapter  pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(getIcon(i))
                            .setTabListener(this)
            );
        }
    }

    private void initFragments() {
        fragments.add(new CourseFragment());
        fragments.add(new CourseFragment());
        fragments.add(new CourseFragment());
        fragments.add(new CourseFragment());

    }


    /*
    * It doesn't matter the color of the icons, but they must have solid colors
    */
    private Drawable getIcon(int position) {
        switch (position) {
            case 0:
                return res.getDrawable(R.mipmap.ic_menu);
            case 1:
                return res.getDrawable(R.mipmap.ic_earth);
            case 2:
                return res.getDrawable(R.mipmap.ic_person_center);
            case 3:
                return res.getDrawable(R.mipmap.ic_person_center);
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
// when the tab is clicked the pager swipe content to the tab position
        pager.setCurrentItem(tab.getPosition());
//        getActionBarToolbar().set
//        if (tab.getPosition() == 1&&mMenu!=null) {
//            getMenuInflater().inflate(R.menu.search_menu, mMenu);
//
//
//        }else if(tab.getPosition() == 0&&mMenu!=null){
//            getMenuInflater().inflate(R.menu.menu_add,mMenu);
////            mActionBarToolbar.setMenu(mMenu);
//        }
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
    }
}

package cn.ifreedomer.beauty.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.ActivityStackManager;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.ViewPagerFragmentAdapter;
import cn.ifreedomer.beauty.fragment.CourseFragment;
import cn.ifreedomer.beauty.fragment.MomentsFragment;
import cn.ifreedomer.beauty.fragment.SocialFragment;
import cn.ifreedomer.beauty.fragment.personcenter.PersonCenterFragment;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends BaseActivity implements MaterialTabListener {

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
    boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, R.string.exit_str, Toast.LENGTH_SHORT)
                        .show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                ;

                return false;

            } else {
                ActivityStackManager.getScreenManager().popAllActivity();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                //
            }
        }

        return false;
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
        fragments.add(new SocialFragment());
        fragments.add(new MomentsFragment());
        fragments.add(new PersonCenterFragment());

    }


    /*
    * It doesn't matter the color of the icons, but they must have solid colors
    */
    private Drawable getIcon(int position) {
        switch (position) {
            case 0:
                return res.getDrawable(R.mipmap.tutourial_icon);
            case 1:
                return res.getDrawable(R.mipmap.group_icon);
            case 2:
                return res.getDrawable(R.mipmap.share_icon);
            case 3:
                return res.getDrawable(R.mipmap.personal_center_icon);
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

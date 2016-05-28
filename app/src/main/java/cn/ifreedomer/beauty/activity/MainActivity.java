package cn.ifreedomer.beauty.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.ActivityStackManager;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.ViewPagerFragmentAdapter;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.fragment.ArticleFragment;
import cn.ifreedomer.beauty.fragment.CourseFragment;
import cn.ifreedomer.beauty.fragment.SocialFragment;
import cn.ifreedomer.beauty.fragment.personcenter.PersonCenterFragment;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.widget.IconTableView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tabHost)
    TabLayout tabHost;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.deploy_comment_fb)
    FloatingActionButton deployCommentFb;
    @Bind(R.id.deploy_article_fb)
    FloatingActionButton deployArticleFb;
    @Bind(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;
    //    @Bind(R.id.action_b)
//    FloatingActionButton actionB;
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
//        actionB.setTitle("HELLO WORLD");
        initVoidMenu();

    }

    private void initVoidMenu() {

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
                Process.killProcess(Process.myPid());
                System.exit(0);
                //
            }
        }

        return false;
    }

    private void initPageAndTabHost() {
        // init view pager
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);
        tabHost.setupWithViewPager(pager);
        for (int i = 0; i < tabHost.getTabCount(); i++) {
            IconTableView iconTableView = new IconTableView(this);
            iconTableView.setIcon(getIcon(i));
            tabHost.getTabAt(i).setCustomView(iconTableView);

        }
    }

    private void initFragments() {
        fragments.add(new CourseFragment());
        Bundle bundle = new Bundle();
        bundle.putInt(IntentConstants.SOCIAL_SHOWTYPE, SocialFragment.COMMON_TYPE);
        SocialFragment socialFragment = new SocialFragment();
        socialFragment.setArguments(bundle);
        fragments.add(socialFragment);
        fragments.add(new ArticleFragment());
        fragments.add(new PersonCenterFragment());

    }

    private int getIcon(int position) {
        switch (position) {
            case 0:
                return (R.mipmap.tutourial_icon);
            case 1:
                return R.mipmap.group_icon;
            case 2:
                return R.mipmap.share_icon;
            case 3:
                return R.mipmap.personal_center_icon;
        }
        return R.mipmap.share_icon;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.deploy_comment_fb, R.id.deploy_article_fb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deploy_comment_fb:
                IntentUtils.startDeployCommentActivity(this);
                break;
            case R.id.deploy_article_fb:
                IntentUtils.startGoodListActivity(this);
//                IntentUtils.startDeployCourseActivity(this);
                break;
        }
    }

//    @OnClick(R.id.action_b)
//    public void onClick() {
//    }
}

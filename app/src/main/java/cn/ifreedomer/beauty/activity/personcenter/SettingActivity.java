package cn.ifreedomer.beauty.activity.personcenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.SettingAdapter;
import cn.ifreedomer.beauty.entity.SettingItemInfo;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<SettingItemInfo> settingItemInfo = new ArrayList<>();
        for (int i=1;i<6;i++){
            SettingItemInfo itemInfo = new SettingItemInfo();
            itemInfo.setContent("通知");
            itemInfo.setIcon(R.mipmap.setting1);
            itemInfo.setType(i%2);
            settingItemInfo.add(itemInfo);
        }
        recycleview.setAdapter(new SettingAdapter(this,0,settingItemInfo));
        recycleview.setLayoutManager(new LinearLayoutManager(SettingActivity.this));
//        recycleview.addItemDecoration(new CommonItemDecorate(SettingActivity.this, DividerItemDecoration.VERTICAL_LIST));

    }
}

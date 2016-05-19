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
import cn.ifreedomer.beauty.adapter.FollowAdapter;
import cn.ifreedomer.beauty.entity.FollowEntity;

public class FollowsActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follows);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<FollowEntity> followEntitys = new ArrayList<>();
        for (int i=0;i<10;i++){
            FollowEntity f = new FollowEntity();
            followEntitys.add(f);
        }

        recycleview.setAdapter(new FollowAdapter(this,0,followEntitys));
        recycleview.setLayoutManager(new LinearLayoutManager(FollowsActivity.this));
    }

}

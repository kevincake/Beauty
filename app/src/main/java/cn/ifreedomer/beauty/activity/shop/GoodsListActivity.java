package cn.ifreedomer.beauty.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.GoodsListRvAdapter;
import cn.ifreedomer.beauty.decorate.VerticalSpaceItemDecoration;
import cn.ifreedomer.beauty.entity.Goods;
import cn.ifreedomer.beauty.util.DensityUtil;

public class GoodsListActivity extends BaseActivity {
    private List<Goods> goodsList = new ArrayList<>();
    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods();
            goodsList.add(goods);

        }
        recycleview.setAdapter(new GoodsListRvAdapter(this, 0, goodsList));
        recycleview.addItemDecoration(new VerticalSpaceItemDecoration(DensityUtil.dip2px(this,getResources().getDimension(R.dimen.dimen_dp2))));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recycleview.setLayoutManager(staggeredGridLayoutManager);
    }


}

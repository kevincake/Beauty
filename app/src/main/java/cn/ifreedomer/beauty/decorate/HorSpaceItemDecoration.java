package cn.ifreedomer.beauty.decorate;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HorSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mHorSpaceWidth;

    public HorSpaceItemDecoration(int mVerticalSpaceHeight) {
        this.mHorSpaceWidth = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = mHorSpaceWidth;
        outRect.right = mHorSpaceWidth;
    }
}
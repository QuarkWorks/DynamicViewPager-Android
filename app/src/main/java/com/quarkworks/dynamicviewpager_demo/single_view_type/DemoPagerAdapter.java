package com.quarkworks.dynamicviewpager_demo.single_view_type;

import android.view.View;
import android.view.ViewGroup;

import com.quarkworks.dynamicviewpager.DynamicPagerAdapter;

import java.util.ArrayList;

/**
 * @author jacobamuchow@gmail.com (Jacob Muchow)
 */
public class DemoPagerAdapter extends DynamicPagerAdapter {

    private ArrayList<Integer> values = new ArrayList<>();

    public DemoPagerAdapter() {
        for(int i = 0; i < 30; i++) {
            values.add(i);
        }

        setCallbacks(new Callbacks() {
            @Override
            public void onDiscardFinished(int position, View view) {
                if(position != POSITION_NOT_FOUND) {
                    values.remove(position);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public View instantiateView(ViewGroup container, int position) {
        final PagerView pagerView = new PagerView(container.getContext());

        pagerView.setPagerViewCallbacks(new PagerView.PagerViewCallbacks() {
            @Override
            public void dismissClicked(final int position) {
                discardView(pagerView);
            }
        });

        return pagerView;
    }

    @Override
    public void updateView(View view, int position) {
        if(view instanceof PagerView) {
            ((PagerView) view).setViewData(values.get(position));
        }
    }

    @Override
    public int getCount() {
        return values.size();
    }
}
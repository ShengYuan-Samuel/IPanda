package com.jiyun.ipanda.view.chinaliveview.fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jiyun.ipanda.App;
import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseFragment;
import com.jiyun.ipanda.chinalivepopup.ClassifyAdapter;
import com.jiyun.ipanda.chinalivepopup.ItemDragHelperCallback;
import com.jiyun.ipanda.chinalivepopup.TagBean;
import com.jiyun.ipanda.contract.ChinaLiveContract;
import com.jiyun.ipanda.model.entity.cctvliveentity.CCTVBeans;
import com.jiyun.ipanda.presenter.ChinaLivePresenter;
import com.jiyun.ipanda.view.MainActivity;
import com.jiyun.ipanda.view.chinaliveview.adapter.ChinaAdapter;
import com.jiyun.ipanda.view.pandaliveview.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ChinaLiveFragment extends BaseFragment<ChinaLivePresenter> implements ChinaLiveContract.View, View.OnClickListener {

    private List<TagBean> items = new ArrayList<>();
    private List<TagBean> otherItems = new ArrayList<>();
    private ClassifyAdapter adapter;
    @BindView(R.id.chinalive_tab)
    TabLayout chinaliveTab;
    @BindView(R.id.chinalive_addBut)
    ImageView chinaliveAddBut;
    @BindView(R.id.chinalive_vp)
    ViewPager chinaliveVp;
    private PopupWindow popupWindow;
    private ImageView chinalive_delete;
    private RecyclerView recy;
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private VpAdapter vpAdapter;
    private List<CCTVBeans.TablistBean> mLists;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china_live;
    }

    @Override
    protected void init() {
        mLists = new ArrayList<>();
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();

        LinearLayout linearLayout = (LinearLayout) chinaliveTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.tab_shape));

        vpAdapter = new VpAdapter(getActivity().getSupportFragmentManager(), mTitle, mFragments);
        chinaliveVp.setAdapter(vpAdapter);
        chinaliveTab.setupWithViewPager(chinaliveVp);
        //chinaliveTab.removeAllTabs();
        //vpAdapter.notifyDataSetChanged();
        showPopupWindow();
    }

    @Override
    protected void loadData() {
        presenter.getChinaLiveData();

    }

    @Override
    public void updateTitle() {
        ((MainActivity) App.context).getTvTitle().setText("直播中国");
        ((MainActivity) App.context).getImageHudong().setVisibility(View.INVISIBLE);
        ((MainActivity) App.context).getImageSign().setVisibility(View.INVISIBLE);
    }


    @OnClick(R.id.chinalive_addBut)
    public void onViewClicked() {
        popupWindow.showAtLocation(getView().findViewById(R.id.chinalive_lin), Gravity.CENTER,0,0);
    }
    @Override
    public void showChinaLiveData(CCTVBeans cctvBean) {
        for (CCTVBeans.TablistBean tablistBean : cctvBean.getTablist()) {
            mLists.add(tablistBean);
            TagBean tagBean = new TagBean(tablistBean.getTitle(), tablistBean.getUrl(), tablistBean.getType(), tablistBean.getOrder());
            items.add(tagBean);

        }
        for (CCTVBeans.TablistBean mList : mLists) {
            ChinaLiveViewFragment chinaLiveViewFragment = new ChinaLiveViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", mList.getUrl());
            chinaLiveViewFragment.setArguments(bundle);
            String title = mList.getTitle();
            mTitle.add(title);
            mFragments.add(chinaLiveViewFragment);
        }
        vpAdapter.notifyDataSetChanged();
        for (CCTVBeans.AlllistBean alllistBean : cctvBean.getAlllist()) {
            TagBean tagBean = new TagBean(alllistBean.getTitle(), alllistBean.getUrl(), alllistBean.getType(), alllistBean.getOrder());
            otherItems.add(tagBean);
        }
        adapter.notifyDataSetChanged();


        Log.d("ChinaLiveFragment", "cctvBean.getTablist().size():" + cctvBean.getTablist().size());
        Log.d("ChinaLiveFragment", "cctvBean.getAlllist().size():" + cctvBean.getAlllist().size());
    }

    @Override
    public void showPopupWindow() {

        View view = getLayoutInflater().inflate(R.layout.chinalive_popupwindow_view, null);
        chinalive_delete = view.findViewById(R.id.chinalive_delete);
        chinalive_delete.setOnClickListener(this);
        recy = view.findViewById(R.id.recy);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        //将ItemTouchHelper和RecyclerView建立关联
        helper.attachToRecyclerView(recy);

        adapter = new ClassifyAdapter(getContext(), helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                //从效果可知，当是两个头布局的时候要占据一行
                return viewType == ClassifyAdapter.TYPE_MY || viewType == ClassifyAdapter.TYPE_OTHER ? 1 : 3;
            }
        });
        recy.setAdapter(adapter);
        adapter.setOnMyChannelItemClickListener(new ClassifyAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "要跳转了", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setmCompleteListener(new ClassifyAdapter.OnCompleteListener() {
            @Override
            public void onComplete(List<TagBean> list) {
                Log.d("ChinaLiveFragment", "list.size():" + list.size());

                for (TagBean item : list) {
                    ChinaLiveViewFragment chinaLiveViewFragment = new ChinaLiveViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", item.getUrl());
                    chinaLiveViewFragment.setArguments(bundle);
                    String title = item.getTitle();
                    mTitle.add(title);
                    mFragments.add(chinaLiveViewFragment);
                }
                vpAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "完成了", Toast.LENGTH_SHORT).show();
            }
        });
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());


    }

    @Override
    public void onClick(View v) {
        popupWindow.dismiss();
    }
}

package com.ulang.modelmvp.mvpreference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulang.modelmvp.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MVPFragment extends MVPBaseFragment<MVPContract.View<MVPItem>, MVPPresenter> implements MVPContract.View<MVPItem>, IListener {

    private static final String TAG = MVPFragment.class.getSimpleName();

    private String param;

    public MVPFragment() {
        // Required empty public constructor
    }

    public static MVPFragment newInstance(String param) {
        MVPFragment fragment = new MVPFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            param = getArguments().getString("param");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected MVPPresenter createPresenter() {
        return new MVPPresenter(param, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_mvpbase, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView(View v) {

    }

    private void initData() {
        mPresenter.start();
    }

    @Override
    public void dismissLoadingViews() {

    }

    @Override
    public void showLoadingViews() {

    }

    @Override
    public void showErrorViews(int errorCode, String msg) {

    }

    @Override
    public void refreshContentView(ArrayList<MVPItem> contentList) {

    }

    @Override
    public void loadMoreContentView(ArrayList<MVPItem> contentList) {

    }

    @Override
    public void onRefresh() {
        mPresenter.requestRefresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.requestLoadMore();
    }


}

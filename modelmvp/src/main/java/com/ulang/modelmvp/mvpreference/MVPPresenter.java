package com.ulang.modelmvp.mvpreference;

import java.util.ArrayList;

public class MVPPresenter extends MVPBasePresenter<MVPContract .View<MVPItem>> implements MVPContract .InteractionListener<ArrayList<MVPItem>>,MVPContract.Presenter{

    private MVPContract .View<MVPItem> mView;
    private MVPContract .Model mModel;
    private String param;
    private ArrayList<MVPItem> mList;
    private boolean isLoading = false;
    private boolean isLoadMore = false;

    public MVPPresenter (String param, MVPContract .View<MVPItem> view){
        this.param= param;
        this.mView = view;
        mModel = new MVPModel(param,this);
    }

    @Override
    public void onInteractionSuccess(ArrayList<MVPItem> list) {
        isLoading = false;
        if(isLoadMore){
            this.mList.addAll(list);
            mView.loadMoreContentView(list);
        } else {
            this.mList = list;
            mView.refreshContentView(list);
        }
        mView.dismissLoadingViews();
    }

    @Override
    public void onInteractionFail(int errorCode, String errorMsg) {
        isLoading = false;
        mView.dismissLoadingViews();
        mView.showErrorViews(errorCode, errorMsg);
    }

    @Override
    public synchronized void requestRefresh() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        isLoadMore = false;
        mModel.loadContent(false,null);
    }

    @Override
    public synchronized void requestLoadMore() {
        if (isLoading) {
            return;
        }
        if (mList == null || mList.size() == 0) {
            return;
        }
        isLoading = true;
        isLoadMore = true;
        mModel.loadContent(true,null);
    }

    @Override
    public void start() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        isLoadMore = false;
        mView.showLoadingViews();
        mModel.loadContent(false,null);
    }

    @Override
    public void update() {

    }
}

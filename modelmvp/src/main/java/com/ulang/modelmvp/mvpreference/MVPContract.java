package com.ulang.modelmvp.mvpreference;

import java.util.ArrayList;

public class MVPContract {

    public interface Model {
        //请求数据
        void loadContent(boolean isLoadMore, String lastKey);
    }

    public interface View<T> {
        //销毁加载页面
        void dismissLoadingViews();

        //展示加载页面
        void showLoadingViews();

        //展示异常页面
        void showErrorViews(int errorCode, String msg);

        //刷新块数据的内容
        void refreshContentView(ArrayList<T> contentList);

        //加载更多块数据的内容
        void loadMoreContentView(ArrayList<T> contentList);
    }

    public interface Presenter {
        //下拉刷新请求
        void requestRefresh();

        //加载更多数据
        void requestLoadMore();
    }

    public interface InteractionListener<T> {
        //请求成功
        void onInteractionSuccess(T t);

        //请求失败
        void onInteractionFail(int errorCode, String errorMsg);
    }

}

package com.ulang.modelmvp.mvpreference;

import java.util.ArrayList;

public class MVPModel implements MVPContract.Model {

    private MVPContract.InteractionListener<ArrayList<MVPItem>> mListener;

    private String param;

    public MVPModel(String param, MVPContract.InteractionListener<ArrayList<MVPItem>> listener) {
        this.param = param;
        this.mListener = listener;
    }

    @Override
    public void loadContent(boolean isLoadMore, String lastKey) {
        //网络请求
        //数据处理
        //成功或者失败的回调
        //伪代码
        if(true){
            mListener.onInteractionSuccess(null);
        }else{
            mListener.onInteractionFail(110,"错误信息");
        }
    }
}

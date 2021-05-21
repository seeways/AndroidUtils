package com.jty.myutils.recyclerview.base;


/**
 * created by Taoyuan on 2021/5/21
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}

package com.willfng.hfrvadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * extension of RecyclerView.Adapter to management data.
 *
 * Created by will on 15/9/2.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> mList;
    public Context mContext;
    public OnItemClickLitener mOnItemClickLitener;


    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    public void setData(ArrayList<T> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return mList;
    }

    public void setData(T[] list) {
        ArrayList<T> arrayList = new ArrayList<>(list.length);
        for (T t : list) {
            arrayList.add(t);
        }
        setData(arrayList);
    }


    public void addData(int position, T item) {
        if (mList != null && position < mList.size()) {
            mList.add(position, item);
            notifyMyItemInserted(position);
        }
    }

    public void removeData(int position) {
        if (mList != null && position < mList.size()) {
            mList.remove(position);
            notifyMyItemRemoved(position);
        }
    }

    protected void notifyMyItemInserted(int position){
        notifyItemInserted(position);
    }

    protected void notifyMyItemRemoved(int position){
        notifyItemRemoved(position);
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



}

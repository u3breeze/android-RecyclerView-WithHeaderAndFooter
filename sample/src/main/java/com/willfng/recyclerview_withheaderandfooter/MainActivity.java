package com.willfng.recyclerview_withheaderandfooter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willfng.hfrvadapter.BaseRecyclerViewAdapter;
import com.willfng.hfrvadapter.HFRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        // set Adapter
        final HFAdapter hfAdapter = new HFAdapter(this);
        recyclerView.setAdapter(hfAdapter);

        // setLayoutManager:LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // setLayoutManager:GridLayoutManager
//        final GridLayoutManager manager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(manager);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if ( (hfAdapter.hasHeader() && hfAdapter.isHeader(position)) ||
//                        hfAdapter.hasFooter() && hfAdapter.isFooter(position) )
//                    return manager.getSpanCount();
//
//                return 1;
//            }
//        });

        //add header
        View headerView = LayoutInflater.from(this).inflate(R.layout.header, recyclerView, false);
        hfAdapter.setHeaderView(headerView);
        //add footer
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer, recyclerView, false);
        hfAdapter.setFooterView(footerView);

        //debug data
        ArrayList<String> data = new ArrayList<>();
        for (int i=0; i<8; i++){
            data.add(String.format("Item %d", i));
        }
        hfAdapter.setData(data);

    }


    /**
     * Extends HFRecyclerViewAdapter to add Header/Footer view.
     */
    class HFAdapter extends HFRecyclerViewAdapter<String, HFAdapter.DataViewHolder>{

        public HFAdapter(Context context) {
            super(context);
        }

        @Override
        public DataViewHolder onCreateDataItemViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.data_item, parent, false);
            return new DataViewHolder(v);
        }

        @Override
        public void onBindDataItemViewHolder(DataViewHolder holder, int position) {
            holder.itemTv.setText(getData().get(position));
        }

        class DataViewHolder extends RecyclerView.ViewHolder{
            TextView itemTv;
            public DataViewHolder(View itemView) {
                super(itemView);
                itemTv = (TextView)itemView.findViewById(R.id.itemTv);
            }
        }
    }

    /**
     * SampleAdapter without Header/Footer view.
     */
//    class SampleAdapter extends BaseRecyclerViewAdapter<String>{
//
//        public SampleAdapter(Context context) {
//            super(context);
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.data_item, parent, false);
//            return new DataViewHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//            DataViewHolder holder = (DataViewHolder)viewHolder;
//            holder.itemTv.setText(getData().get(position));
//        }
//        class DataViewHolder extends RecyclerView.ViewHolder{
//            TextView itemTv;
//            public DataViewHolder(View itemView) {
//                super(itemView);
//                itemTv = (TextView)itemView.findViewById(R.id.itemTv);
//            }
//        }
//    }

}

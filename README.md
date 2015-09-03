# android-RecyclerView-WithHeaderAndFooter
Extension of RecyclerView.Adapter. Can add header/footer to RecyclerView for LinearLayoutManager and GridLayoutManager.

# Screenshot
<p>
   <img src="https://raw.github.com/u3breeze/android-RecyclerView-WithHeaderAndFooter/master/screenshot_list.png" width="320" alt="Screenshot"/>
   <img src="https://raw.github.com/u3breeze/android-RecyclerView-WithHeaderAndFooter/master/screenshot_grid.png" width="320" alt="Screenshot"/>
</p>

# Usage

###  Add Header/Footer View to RecyclerView
###  Step 1

* Create Adapter Class which extends HFRecyclerViewAdapter

```java
class HFAdapter extends HFRecyclerViewAdapter<String>{

    public HFAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataItemViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindDataItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DataViewHolder holder = (DataViewHolder)viewHolder;
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
```

###  Step 2

* Use Adapter to make RecyclerView with header/footer.

```java
RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
// set Adapter
final HFAdapter hfAdapter = new HFAdapter(this);
recyclerView.setAdapter(hfAdapter);

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
```

* For GridLayoutManager

```java
final GridLayoutManager manager = new GridLayoutManager(this, 3);
recyclerView.setLayoutManager(manager);
//Set header/footer's spanCount to match horizontal of parent.
manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
    @Override
    public int getSpanSize(int position) {
        if ( (hfAdapter.hasHeader() && hfAdapter.isHeader(position)) ||
                hfAdapter.hasFooter() && hfAdapter.isFooter(position) )
            return manager.getSpanCount();

        return 1;
    }
});
```



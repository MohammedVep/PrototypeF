package com.georgebrown.prototype1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.georgebrown.prototype1.Model.Restaurant;
import com.georgebrown.prototype1.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> mValues;
    private ItemClickListener clickListener;

    public RestaurantAdapter(List<Restaurant> items,ItemClickListener clickListener) {

        mValues = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mAddressView.setText(mValues.get(position).getAddress());
        holder.mTagsView.setText(mValues.get(position).getTags());
        holder.mTaskView.setText(mValues.get(position).getTasks());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick((position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameView;
        public final TextView mAddressView;
        public final TextView mTaskView;
        public final TextView mTagsView;
        public Restaurant mItem;

        public ViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            mNameView = binding.itemName;
            mAddressView = binding.itemAddress;
            mTaskView = binding.itemTask;
            mTagsView = binding.itemTags;

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
    public interface ItemClickListener{
        public void onItemClick(int possition);
    }

    public void filterList(ArrayList<Restaurant> filterList){
        mValues = filterList;
        notifyDataSetChanged();
    }
}


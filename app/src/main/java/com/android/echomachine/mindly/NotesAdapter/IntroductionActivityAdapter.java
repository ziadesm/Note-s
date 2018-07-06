package com.android.echomachine.mindly.NotesAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.echomachine.mindly.database.NoteDatabase;
import com.android.echomachine.mindly.modle.NoteModle;
import com.android.echomachine.mindly.R;

import java.util.ArrayList;
import java.util.List;

public class IntroductionActivityAdapter extends RecyclerView.Adapter<IntroductionActivityAdapter.NoteHolder> {

    private List<NoteModle> mItems;
    private Context mContext;
    private NoteDatabase ndb;

    public IntroductionActivityAdapter(List<NoteModle> items, Context context) {
        mItems = items;
        mContext = context;
    }

    public void setItemCount() {
        mItems.clear();
        mItems.addAll(ndb.getAllNotes());
        notifyDataSetChanged();
    }

    public void onDeleteItem() {
        mItems.clear();
        mItems.addAll(ndb.getAllNotes());
    }

    public void removeItemSelected(int selected) {
        if (mItems.isEmpty()) return;
        mItems.remove(selected);
        notifyItemRemoved(selected);
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_view, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(IntroductionActivityAdapter.NoteHolder holder, int position) {
        NoteModle noteModle = mItems.get(position);
        holder.mTitleView.setText(noteModle.getTitle());
        holder.mDateView.setText(noteModle.getDate());
        holder.mTimeView.setText(noteModle.getTime());
        holder.mImageView.setImageResource(R.drawable.recycle_circle_image_view);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItemsCount() {
        mItems.clear();
        mItems.addAll(ndb.getAllNotes());
        notifyDataSetChanged();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTitleView;
        private TextView mDateView;
        private TextView mTimeView;

        public NoteHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.recycle_image_view);
            mTitleView = itemView.findViewById(R.id.recycle_title_view);
            mDateView = itemView.findViewById(R.id.recycle_date_view);
            mTimeView = itemView.findViewById(R.id.recycle_time_view);
        }
    }
}

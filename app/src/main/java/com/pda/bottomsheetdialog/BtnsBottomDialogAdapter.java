package com.pda.bottomsheetdialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BtnsBottomDialogAdapter extends RecyclerView.Adapter<BtnsBottomDialogAdapter.VH> {
    private static final String TAG = "BtnsBottomDialogAdapter";

    private Context context;
    private List<DialogBtnData> btnList;
    private OnItemClickListener itemClickListener;

    public BtnsBottomDialogAdapter(Context context, List<DialogBtnData> btnList) {
        this.context = context;
        this.btnList = btnList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.btns_item_layout, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        DialogBtnData curData = btnList.get(position);
        if (curData == null || TextUtils.isEmpty(curData.getTitle())) {
            return;
        }
        holder.itemView.setOnClickListener(v -> {
            //item 点击事件
            Log.d(TAG, "onClick position:" + position + ", data:" + curData);
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, position);
            }
        });
        holder.titleTv.setText(curData.getTitle());
    }

    @Override
    public int getItemCount() {
        return btnList.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static class VH extends RecyclerView.ViewHolder {

        private TextView titleTv;

        public VH(@NonNull View view) {
            super(view);
            titleTv = view.findViewById(R.id.tv_dialog_btn);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
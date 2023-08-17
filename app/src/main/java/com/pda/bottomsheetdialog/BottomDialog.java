package com.pda.bottomsheetdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

/**
 * 自定义底部弹出dialog
 */
public class BottomDialog implements View.OnClickListener, BtnsBottomDialogAdapter.OnItemClickListener {
    private static final String TAG = "BottomDialog";

    private Context context;
    private BottomSheetDialog dialog;
    private TextView titleTv;
    private TextView contentTv;
    private TextView leftBtnTv;
    private TextView rightBtnTv;

    private OnBtnClickListener listener;
    private RecyclerView recyclerView;
    private BtnsBottomDialogAdapter adapter;
    private BtnsBottomDialogAdapter.OnItemClickListener listener1;

    /**
     * 基本样式
     *
     * @param context
     */
    public BottomDialog(Context context) {
        this.context = context;
        dialog = new BottomSheetDialog(context, R.style.BottomSheetEdit);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_dialog_layout, null);
        dialog.setContentView(view);
        // 将BottomSheetDialog背景设为透明
        FrameLayout bottom = dialog.findViewById(R.id.design_bottom_sheet);
        if (bottom != null) {
            bottom.setBackgroundResource(android.R.color.transparent);
            //解决BottomSheetDialog底部弹出框 横屏显示不全的问题
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom);
            /**
             * 控制展开跟收缩
             *
             * STATE_EXPANDED 展开状态
             * STATE_COLLAPSED 收缩状态
             * STATE_DRAGGING 正在拖动状态
             * STATE_HIDDEN 隐藏状态
             */
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        titleTv = view.findViewById(R.id.tv_title);
        contentTv = view.findViewById(R.id.tv_content);
        leftBtnTv = view.findViewById(R.id.tv_leftBtn);
        rightBtnTv = view.findViewById(R.id.tv_rightBtn);
        leftBtnTv.setOnClickListener(this);
        rightBtnTv.setOnClickListener(this);
    }

    /**
     * 列表样式
     *
     * @param context
     * @param btnList
     */
    public BottomDialog(Context context, List<DialogBtnData> btnList) {
        dialog = new BottomSheetDialog(context, R.style.BottomSheetEdit);
        View view = LayoutInflater.from(context).inflate(R.layout.btn_bottom_dialog_layout, null);
        dialog.setContentView(view);
        // 将BottomSheetDialog背景设为透明
        FrameLayout bottom = dialog.findViewById(R.id.design_bottom_sheet);
        if (bottom != null) {
            bottom.setBackgroundResource(android.R.color.transparent);
            //解决BottomSheetDialog底部弹出框 横屏显示不全的问题
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottom);
            /**
             * 控制展开跟收缩
             *
             * STATE_EXPANDED 展开状态
             * STATE_COLLAPSED 收缩状态
             * STATE_DRAGGING 正在拖动状态
             * STATE_HIDDEN 隐藏状态
             */
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }


        recyclerView = view.findViewById(R.id.rcv_btn_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BtnsBottomDialogAdapter(context, btnList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        titleTv = view.findViewById(R.id.tv_bottom_dialog_title);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public BottomDialog setDismissListener(DialogInterface.OnDismissListener dismissListener) {
        dialog.setOnDismissListener(dismissListener);
        return this;
    }

    public BottomDialog setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
        }
        return this;
    }

    public BottomDialog setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            contentTv.setVisibility(View.VISIBLE);
            contentTv.setText(content);
        }
        return this;
    }

    public BottomDialog setLeftBtn(String btn) {
        if (!TextUtils.isEmpty(btn)) {
            leftBtnTv.setVisibility(View.VISIBLE);
            leftBtnTv.setText(btn);
        }
        return this;
    }

    public BottomDialog setRightBtn(String btn) {
        if (!TextUtils.isEmpty(btn)) {
            rightBtnTv.setVisibility(View.VISIBLE);
            rightBtnTv.setText(btn);
        }
        return this;
    }

    public BottomDialog setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_leftBtn:
                dismiss();
                if (listener != null) {
                    listener.onLeftBtnClick(v);
                }
                break;
            case R.id.tv_rightBtn:
                dismiss();
                if (listener != null) {
                    listener.onRightBtnClick(v);
                }
                break;
            default:
                break;
        }
    }

    public void setOnItemClickListener(BtnsBottomDialogAdapter.OnItemClickListener itemClickListener) {
        this.listener1 = itemClickListener;
    }

    @Override
    public void onItemClick(View view, int position) {
        dismiss();
        if (listener != null) {
            listener1.onItemClick(view, position);
        }
    }

    public interface OnBtnClickListener {
        void onLeftBtnClick(View view);

        void onRightBtnClick(View view);
    }

}
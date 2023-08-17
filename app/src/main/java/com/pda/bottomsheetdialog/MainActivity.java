package com.pda.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        //普通底部弹出窗
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = false ? "您当前可升级更新到V" + 1.1 : "您当前已是最新版本";
                BottomDialog dialog = new BottomDialog(MainActivity.this);
                dialog.setTitle("检查更新").setContent(text).setLeftBtn("取消");
                if (false) {
                    dialog.setRightBtn("点此下载最新版");
                }
                //自定义Dialog按钮监听
                dialog.setOnBtnClickListener(new BottomDialog.OnBtnClickListener() {
                    @Override
                    public void onLeftBtnClick(View view) {
                    }

                    @Override
                    public void onRightBtnClick(View view) {
                    }
                }).show();

            }
        });
        //列表底部弹出窗
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DialogBtnData> btnList = new ArrayList<>();
                btnList.add(new DialogBtnData(1, "保存"));
                btnList.add(new DialogBtnData(2, "取消"));
                BottomDialog dialog = new BottomDialog(MainActivity.this, btnList);

                dialog.setOnItemClickListener(new BtnsBottomDialogAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int pos) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        }).start();
                    }
                });
                dialog.show();
            }
        });
    }
}
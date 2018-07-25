package com.took.firstgit.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.took.firstgit.R;

public class BottomDialogView extends Dialog {
    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private Button  button1,button2,cancelBut;
    private Context context;

    public BottomDialogView(@NonNull Context context, boolean isCancelable, boolean isBackCancelable) {
        this(context, R.style.MyDialog,isCancelable,isBackCancelable);
    }

    public BottomDialogView(@NonNull Context context, int themeResId, boolean isCancelable, boolean isBackCancelable) {
        super(context, themeResId);
        this.context = context;
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_bottom_dialog_view);

        button1 = findViewById(R.id.takePhoto);
        button2 = findViewById(R.id.choosePhoto);
        cancelBut = findViewById(R.id.btn_cancel);

        initListener();

        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);

        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    private void initListener(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getName(), "拍照");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getName(), "从相册选择");
            }
        });
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

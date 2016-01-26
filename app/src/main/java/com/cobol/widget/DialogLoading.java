package com.cobol.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.widgetoauth.R;


/**
 * 解决问题：loadingDialog。
 *
 * @1 任意界面实现加载dialog。
 * <p/>
 * Created by 谢秋鹏 on 2015/7/9.
 */
public class DialogLoading extends Dialog {
    //
    private static DialogLoading instance = null;

    //
    private DialogLoading(Context context) {
        super(context, android.R.style.Theme_Dialog);
        FrameLayout layout = (FrameLayout) LayoutInflater.from(instance.getContext()).inflate(R.layout.dialog_loading, null);
        instance.setContentView(layout);
        instance.setCancelable(false);
        instance.setCanceledOnTouchOutside(false);
        instance.getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    //
    public static DialogLoading getDialog(Context context) {
        if (instance == null) {
            instance = new DialogLoading(context);
        }
        startAnimation();
        return instance;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        instance = null;
    }

    //
    private static void startAnimation() {
        ImageView imageView = (ImageView) instance.findViewById(R.id.iv_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
        instance.show();
    }
}

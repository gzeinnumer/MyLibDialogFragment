package com.gzeinnumer.mylibdialogfragment;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyLibDialog extends DialogFragment {

    protected double canvasWidth = 0.9;
    protected boolean isFullScreen = false;
    protected boolean enableBackButton = false;

    public static final String TAG = "BaseDialogFragment";

    protected int animationStyle = R.style.DialogStyle_In;

    public MyLibDialog() {
    }

    public MyLibDialog(int animationStyle) {
        this.animationStyle = animationStyle;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = animationStyle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, animationStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (enableBackButton) {
                    getDialog().dismiss();
                }
            }
        };
    }

    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width;
        int height;

        if (isFullScreen) {
            width = WindowManager.LayoutParams.MATCH_PARENT;
            height = size.y - getToolBarHeight();

            window.setLayout(width, height);

            window.setGravity(Gravity.BOTTOM);
        } else {
            width = size.x;
            height = WindowManager.LayoutParams.WRAP_CONTENT;

            if (canvasWidth >= 0.1 && canvasWidth <= 1.0) {
                window.setLayout((int) (width * canvasWidth), height);
            } else {
                window.setLayout((int) (width * 0.9), height);
            }
            window.setGravity(Gravity.CENTER);
        }
    }

    public int getToolBarHeight() {
        int[] attrs = new int[]{R.attr.actionBarSize};
        TypedArray ta = getContext().obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }

    protected void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    protected void setCanvasWidth(double canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    protected void enableBackButton(boolean enableBackButton) {
        this.enableBackButton = enableBackButton;
    }
}

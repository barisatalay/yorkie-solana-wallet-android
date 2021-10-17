package com.barisatalay.yorkiewallet.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

import com.barisatalay.yorkiewallet.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.Theme_YorkieWallet_DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.loading_dialog);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void showLoading() {
        if (!isShowing()) {
            show();
        }
    }

    public void hideLoading() {
        if (isShowing()) {
            dismiss();
        }
    }

}

package com.xandone.dog.wcapp.uitils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.xandone.dog.wcapp.interfs.MaterialDialogOnclickListener;

/**
 * author: Admin
 * created on: 2019/7/3 18:36
 * description:
 */
public class MaterialDialogUtils {
    public static void showSimpleDialog(Context context, String content, String negativeText, String positiveText,
                                        final MaterialDialogOnclickListener listener) {
        new MaterialDialog.Builder(context)
                .content(content)
                .title("提示")
                .negativeText(negativeText)
                .positiveText(positiveText)
                .canceledOnTouchOutside(false)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            dialog.dismiss();
                            listener.onCancle();
                        }
                        if (which == DialogAction.POSITIVE) {
                            dialog.dismiss();
                            listener.onConfirm();
                        }
                    }
                })
                .show();
    }

}

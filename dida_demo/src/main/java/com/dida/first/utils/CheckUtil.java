package com.dida.first.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by KingJA on 2016-1-22.
 */
public class CheckUtil {

    public static boolean checkPhoneFormat(String phone){
        // 判断非空
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showMyToast("手机号码不能为空");
            return false;
        }

        // 判断手机号格式
        if (!Pattern.matches(
                "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$", phone)) {
            ToastUtil.showMyToast("手机号码格式不对");
            return false;
        }
        return true;
    }
    public static boolean isEmpty(String content,String tip){
        // 判断非空
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showMyToast(tip);
            return false;
        }
        return true;
    }
    public static boolean checkPasswordFormat(String password) {
        // 判断非空
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showMyToast("密码不能为空");
            return false;
        }
        if (!Pattern.matches("[a-zA-Z0-9]{6,12}", password)) {
            ToastUtil.showMyToast("密码须为6-12位字母或数字组合");
            return false;
        } else {
            return true;
        }
    }
}

package com.dida.first.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.UUID;


public class StringUtil {
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	public static String getUUIDString(){
		String UUIDString = UUID.randomUUID().toString();
		String newUUIDString = UUIDString.replace("-", "");
		String substring = newUUIDString.substring(0, 10);
		return substring;
	}

	public static void  setRichText(TextView textView ,@DrawableRes int id,String text){
		SpannableString spanString = new SpannableString("*  "+text);
		Drawable drawable = ContextCompat.getDrawable(UIUtils.getContext(), id);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
		spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(spanString);
	}

	public static String getDoubleNum(int num){
		DecimalFormat df = new DecimalFormat("00");
		return df.format(num);
	}

}

/**
 * 
 */
package com.dida.first.view.AbsListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

/**
 * @author		KingJA 
 * @data		2015-8-18 下午2:21:53 
 * @use			
 *
 */
public class MyListView extends ListView{
	public MyListView(Context context) {    
        super(context);    
    }    
      
    public MyListView(Context context, AttributeSet attrs) {    
        super(context, attrs);    
    }    
      
    public MyListView(Context context, AttributeSet attrs, int defStyle) {    
        super(context, attrs, defStyle);    
    }  
    @Override    
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {    
    
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,    
                MeasureSpec.AT_MOST);    
        super.onMeasure(widthMeasureSpec, expandSpec);    
    }      
}

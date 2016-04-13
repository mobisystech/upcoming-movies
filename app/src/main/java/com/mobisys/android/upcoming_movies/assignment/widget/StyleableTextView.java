package com.mobisys.android.upcoming_movies.assignment.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mobisys.android.upcoming_movies.assignment.R;
import com.mobisys.android.upcoming_movies.assignment.utils.UiUtil;


public class StyleableTextView extends TextView {

	public StyleableTextView(Context context, String font) {
		super(context);
		setCustomFont(context, font);
	}

	public StyleableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		UiUtil.setCustomFont(this, context, attrs,
				R.styleable.StyleableTextView,
				R.styleable.StyleableTextView_font);
	}

	public StyleableTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		UiUtil.setCustomFont(this, context, attrs,
                R.styleable.StyleableTextView,
                R.styleable.StyleableTextView_font);
	}

	private void setCustomFont(Context context, String font){
		UiUtil.setCustomFont(this, context, font);
	}
}

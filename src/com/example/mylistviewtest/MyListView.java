package com.example.mylistviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MyListView extends ListView implements OnTouchListener {
	private GestureDetector detector;
	private Button button;
	private LinearLayout layout;
	private DelItemListener delItemListener;

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		detector = new GestureDetector(context, new MySimpleOnGestureListener());
		setOnTouchListener(this);
	}

	class MySimpleOnGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float startx = e1.getX();
			float starty = e1.getY();

			float endx = e2.getX();
			float endy = e2.getY();
			if (Math.abs(endx - startx) > Math.abs(endy - starty)) {
				final int pointToPosition = pointToPosition((int) e1.getX(),
						(int) e1.getY());
				final ViewGroup view = (ViewGroup) getChildAt(pointToPosition
						- getFirstVisiblePosition());
				button = (Button) view.findViewById(R.id.del);
				if (button == null) {
					LayoutInflater.from(getContext()).inflate(
							R.layout.mybutton, view, true);
					button = (Button) view.findViewById(R.id.del);
					button.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							delItemListener.delete(pointToPosition);
							((ViewGroup) button.getParent()).removeView(button);
						}
					});

				} else {
					ViewGroup group1 = (ViewGroup) view
							.findViewById(R.id.button);
					view.removeView(group1);
				}

			}
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		detector.onTouchEvent(ev);
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		layout = (LinearLayout) findViewById(R.id.button);
		if (layout != null) {
			((ViewGroup) layout.getParent()).removeView(layout);
		}
		return false;
	}

	public interface DelItemListener {
		void delete(int position);
	}

	public void SetDelItemListener(DelItemListener delItemListener) {
		this.delItemListener = delItemListener;
	}
}

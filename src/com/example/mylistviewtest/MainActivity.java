package com.example.mylistviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mylistviewtest.MyListView.DelItemListener;

public class MainActivity extends Activity {
	private MyListView listView;
	private myAdaptor adapter;
	private List<String> strings = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (MyListView) findViewById(R.id.listview);
		intitList();
		adapter = new myAdaptor(getApplicationContext(), R.layout.itemlayout,
				strings);
		listView.SetDelItemListener(new DelItemListener() {

			@Override
			public void delete(int position) {
				strings.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
		listView.setAdapter(adapter);
	}

	private void intitList() {
		strings.add(1 + "");
		strings.add(11 + "");
		strings.add(12 + "");
		strings.add(13 + "");
		strings.add(14 + "");
		strings.add(15 + "");
		strings.add(16 + "");
		strings.add(17 + "");
		strings.add(18 + "");
		strings.add(19 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");
		strings.add(21 + "");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class myAdaptor extends ArrayAdapter<String> {

		public myAdaptor(Context context, int resource, List<String> objects) {
			super(context, resource, objects);
			this.context = context;
		}

		Context context;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.itemlayout, null);
				holder = new Holder();
				holder.textView = (TextView) convertView
						.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.textView.setText(getItem(position));
			return convertView;
		}

	}

	public class Holder {
		TextView textView;
	}
}

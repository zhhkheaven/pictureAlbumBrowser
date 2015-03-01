/**
 * pictureAlbumBowser 
 * Author : Z
 */
package com.binaryw.picturealbumbowser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	GridView grid;
	ImageView imageView;
	ImageSwitcher switcher;
	int[] imageIds = new int[] { R.drawable.m1, R.drawable.m2, R.drawable.m3,
			R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7,
			R.drawable.m8, R.drawable.m9, R.drawable.m10, R.drawable.m11,
			R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15,
			R.drawable.m16, R.drawable.m17, R.drawable.m18, R.drawable.m19,
			R.drawable.m20 };

	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.main_layout);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		grid = (GridView) findViewById(R.id.grid);
		String[] from = { "image" };
		int[] to = { R.id.mini_picture };
		// 创建simpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.mini_picture, from, to);
		// 填充数据
		grid.setAdapter(simpleAdapter);

		imageView = (ImageView) findViewById(R.id.imageView);
		
		//下面是添加带有动画效果的浏览方式
		// 获取显示图片的ImageSwitcher
		switcher = (ImageSwitcher) findViewById(R.id.switcher);
		// 为ImageSwitcher设置图片切换的动画效果
		switcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				// 创建ImageView对象
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				// 返回ImageView对象
				return imageView;
			}
		});
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				imageView.setImageResource(imageIds[position]);
				switcher.setImageResource(imageIds[position]);
				Log.w("click", String.valueOf(position));
			}
		});
	}
}
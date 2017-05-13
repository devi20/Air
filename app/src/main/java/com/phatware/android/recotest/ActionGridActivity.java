package com.phatware.android.recotest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 
 * @author manish.s
 *
 */

public class ActionGridActivity extends Activity {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;
	String sign;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actiongrid);
		sign = getIntent().getStringExtra("sign");
		dbHelper = new DBHelper(getApplicationContext());

		Bitmap silent = BitmapFactory.decodeResource(this.getResources(), R.drawable.silent);
		Bitmap airplane = BitmapFactory.decodeResource(this.getResources(), R.drawable.airplane);
		Bitmap bluetooth = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluetooth);
		Bitmap data = BitmapFactory.decodeResource(this.getResources(), R.drawable.data);
		Bitmap domain = BitmapFactory.decodeResource(this.getResources(), R.drawable.domain);
		Bitmap lock = BitmapFactory.decodeResource(this.getResources(), R.drawable.lock48);
		Bitmap flashlight = BitmapFactory.decodeResource(this.getResources(), R.drawable.flashlight48);
		Bitmap marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
		Bitmap open = BitmapFactory.decodeResource(this.getResources(), R.drawable.open_app);
		Bitmap phone = BitmapFactory.decodeResource(this.getResources(), R.drawable.phone);
		Bitmap settings = BitmapFactory.decodeResource(this.getResources(), R.drawable.settings);
		Bitmap shake = BitmapFactory.decodeResource(this.getResources(), R.drawable.shake);
		Bitmap wifi = BitmapFactory.decodeResource(this.getResources(), R.drawable.wifi);




		gridArray.add(new Item(silent,"silent"));
		gridArray.add(new Item(airplane,"airplane"));
		gridArray.add(new Item(bluetooth,"bluetooth"));
		gridArray.add(new Item(data,"data"));
		gridArray.add(new Item(domain,"internet access"));
		gridArray.add(new Item(lock,"lock"));
		gridArray.add(new Item(flashlight,"flashlight"));
		gridArray.add(new Item(marker,"location"));
		gridArray.add(new Item(open,"installedapps"));
		gridArray.add(new Item(phone,"call"));
		gridArray.add(new Item(settings,"settings"));
		gridArray.add(new Item(shake,"vibration"));
		gridArray.add(new Item(wifi,"wifi"));


		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				dbHelper.updateAcion(sign,gridArray.get(i).getTitle());
				Toast.makeText(getApplicationContext(),"Action Assigned",Toast.LENGTH_LONG).show();
				onBackPressed();
			}
		});
	}

}

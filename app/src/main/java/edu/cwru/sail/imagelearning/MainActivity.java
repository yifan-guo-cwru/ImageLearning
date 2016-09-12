package edu.cwru.sail.imagelearning;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

	private ImageView iv;
	private final String imgDir = Environment.getExternalStorageDirectory().toString() + "/DCIM/";
	//Make sure that this part is dynamically defined by the Browse Folder and
	// your CSV file name is "THE_SAME_FOLDER_NAME.csv"

	public void openFolder()
	{
	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/DCIM/");
	intent.setDataAndType(uri, imgDir);
	startActivity(Intent.createChooser(intent, "Open folder"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

		iv = (ImageView) findViewById(R.id.imageView);

		File img = new File(imgDir+File.separator+"016_spontaneous_smile_2.mp4/016_spontaneous_smile_2_001.jpg");

		if (img.exists()) {
			//Loading Image from URL
			Picasso.with(this)
					//.load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")
					.load(img)
					//.placeholder(R.drawable.placeholder)   // optional
					//.error(R.drawable.error)      // optional
					.resize(800,800)                        // optional
					.into(iv);


		}
		//setting the function of button
		Button button_1 = (Button) findViewById(R.id.button1);
		button_1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)  {



				try {
					String filename = imgDir+File.separator+"016_spontaneous_smile_2.mp4.csv";
					Log.e("hello", "1 ");
					CSVWriter writer = null;
					Log.e("hello", "2 ");
					writer = new CSVWriter(new FileWriter(filename));
					Log.e("hello", "3 ");
					String[] entries = "016_spontaneous_smile_2_001.jpg, 1".split(",");
					Log.e("hello", "4 ");
					Log.d("A", String.valueOf(entries));
					writer.writeNext(entries);
					Log.e("hello", "5 ");
					writer.close();
					Log.e("hello", "6 ");
					Toast.makeText(getApplicationContext(), "Hello2", Toast.LENGTH_SHORT).show();

				} catch (IOException e) {
					Log.e("hello", "aaaaaaaaa ");
				}



			}
		});





	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


}

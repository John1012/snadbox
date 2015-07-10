package com.johnjohn.demoapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.johnjohn.demoapp.com.johnjohn.demoapp.youbike.DownloadJsonAsyncTask;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.button)
    Button button;

    private String getVersionAndNumber() {
        try {
            final PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            return "VersionName[" + packageInfo.versionName + "],versionNumber[" + Integer.toString(packageInfo.versionCode) + "]";
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Log.i("MainActivity", getVersionAndNumber());
        new DownloadJsonAsyncTask().execute();
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

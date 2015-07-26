package com.johnjohn.demoapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnjohn.demoapp.youbike.Station;
import com.johnjohn.demoapp.youbike.StationAdapter;
import com.johnjohn.demoapp.youbike.StationDeserializer;
import com.johnjohn.demoapp.youbike.YouBike;
import com.johnjohn.demoapp.youbike.YouBikeDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    final String TAG= "MainActivity";
    ListView listView;
    ProgressDialog mProgress;
    List<Station> mStations=null;
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
    protected void onResume() {
        super.onResume();
        if(mStations == null) {
            mProgress = new ProgressDialog(this);
            mProgress.setTitle("Loading");
            mProgress.setMessage("Wait while loading...");
            mProgress.show();
        }
        else {
            listView.setAdapter(new StationAdapter(this, mStations));
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", getVersionAndNumber());
        listView = (ListView)findViewById(R.id.custom_list);
        if ((bundle != null) && (bundle.getSerializable("stations") != null)) {
            Log.i(TAG,"restore data");
            mStations = bundle.getParcelableArrayList("stations");
        }
        else
            new DownloadJsonTask().execute();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Log.i(TAG,"save data");
        state.putParcelableArrayList("stations", (ArrayList<Station>) mStations);
    }

    private void showData(List<Station> stations)
    {
        mStations=stations;
        listView.setAdapter(new StationAdapter(this, stations));
        mProgress.dismiss();
    }

    private class DownloadJsonTask extends AsyncTask<String,String,YouBike> {
        private final String YOUBIKE_URL = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=ddb80380-f1b3-4f8e-8016-7ed9cba571d5";
        @Override
        protected YouBike doInBackground(String... params) {
            YouBike youbike = null;
            try {

                URL url = new URL(YOUBIKE_URL);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                // Configure GSON
                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(YouBike.class, new YouBikeDeserializer());
                gsonBuilder.registerTypeAdapter(Station.class, new StationDeserializer());
                final Gson gson = gsonBuilder.create();
                youbike = gson.fromJson(reader, YouBike.class);
                System.out.println(youbike.toString());
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return youbike;
        }

        @Override
        protected void onPostExecute(YouBike youBike) {
            super.onPostExecute(youBike);
            showData(youBike.stations);
        }
    }
}

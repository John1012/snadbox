package com.johnjohn.demoapp.youbike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.johnjohn.demoapp.R;
import java.util.List;

/**
 * Created by john on 2015/7/25.
 */
public class StationAdapter extends BaseAdapter {
    private List<Station> stations;
    private LayoutInflater layoutInflater;

    public StationAdapter(Context cxt,List<Station> stations) {
        this.stations=stations;
        this.layoutInflater=LayoutInflater.from(cxt);
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Object getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.station_row, null);
            holder = new ViewHolder();
            holder.stationView = (TextView) convertView.findViewById(R.id.tvStation);
            holder.parkView = (TextView) convertView.findViewById(R.id.tvPark);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.stationView.setText(stations.get(position).getName());
        holder.parkView.setText(stations.get(position).getAvailableParks()+"/"+stations.get(position).getTotalParks());
        return convertView;
    }

    static class ViewHolder {
        TextView stationView;
        TextView parkView;
    }
}

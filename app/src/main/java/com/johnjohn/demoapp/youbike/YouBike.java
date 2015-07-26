package com.johnjohn.demoapp.youbike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2015/7/25.
 */
public class YouBike {
    public int count;
    public List<Station> stations=new ArrayList<Station>();

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("count:"+count+"\n");
        for(Station s:stations)
            sb.append(s.toString()+"\n");
        return sb.toString();
    }
}

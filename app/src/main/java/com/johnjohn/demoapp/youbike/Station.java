package com.johnjohn.demoapp.youbike;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john on 2015/7/25.
 */
public class Station implements Parcelable {
    String name;
    int totalParks;
    int availableParks;

    public Station() {}

    public Station(Parcel p) {
        name=p.readString();
        totalParks=p.readInt();
        availableParks=p.readInt();
    }

    public String getName() {
        return name;
    }

    public int getTotalParks() {
        return totalParks;
    }

    public int getAvailableParks() {
        return availableParks;
    }

    public String toString()
    {
        return name+"("+availableParks+"/"+totalParks+")";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(totalParks);
        out.writeInt(availableParks);
    }

    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        
        public Station createFromParcel(Parcel p) {
            return new Station(p);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}

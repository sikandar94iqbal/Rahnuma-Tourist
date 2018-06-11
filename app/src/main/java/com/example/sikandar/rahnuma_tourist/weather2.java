package com.example.sikandar.rahnuma_tourist;

/**
 * Created by Sikandar on 4/30/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sikandar on 4/18/2017.
 */

//public class weather {
//    public String icon;
//    public String title;
//    public weather(){
//        super();
//    }
//
//    public weather(String name, String pic) {
//        super();
//        this.icon = pic;
//        this.title =name;
//    }
//
//    public String get_name(){
//        return this.title;
//    }
//
//    public String get_pic(){
//        return this.icon;
//    }
//}

/*
public class weather{
    String name;
    int price;
    String pic;
    boolean box;


    weather(String _describe, int _price, String _image, boolean _box) {
        name = _describe;
        price = _price;
        this.pic = _image;
        box = _box;
    }

    public String get_name(){
        return  this.name;
    }

    public String get_pic(){
        return  this.pic;
    }
}*/

public class weather2 implements Parcelable {
    String name;
    int price;
    String pic;
    boolean box;
    String guide_id;


    weather2(String _describe, int _price, String _image, boolean _box) {
        name = _describe;
        price = _price;
        this.pic = _image;
        box = _box;

    }

    public String get_name(){
        return  this.name;
    }

    public String get_guide_id(){
        return  this.guide_id;
    }

    public String get_pic(){
        return  this.pic;
    }

    protected weather2(Parcel in) {
        name = in.readString();
        price = in.readInt();
        pic = in.readString();
        box = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(pic);
        dest.writeByte((byte) (box ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<weather> CREATOR = new Parcelable.Creator<weather>() {
        @Override
        public weather createFromParcel(Parcel in) {
            return new weather(in);
        }

        @Override
        public weather[] newArray(int size) {
            return new weather[size];
        }
    };
}

package com.example.sikandar.rahnuma_tourist;

/**
 * Created by Sikandar on 4/20/2017.
 */

public class Product {
    String name;
    int price;
    int image;
    boolean box;


    Product(String _describe, int _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}
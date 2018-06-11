package com.example.sikandar.rahnuma_tourist;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        setUpMapIfNeeded();
//    }

    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Inflate the layout for this fragment
        // don't look at this layout it's just a listView to show how to handle the keyboard
      //  View view = inflater.inflate(R.layout.main, container, false);

         ID = getIntent().getStringExtra("EXTRA_SESSION_ID");
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        int x=6;
        mapFragment.getMapAsync(this);



//     setUpMapIfNeeded();



//        return view;
    }


    public void setUpMapIfNeeded() {


        if (mMap == null) {
            Toast.makeText(this,"Empty",Toast.LENGTH_LONG);
        }






        final LatLng MOUNTAIN_VIEW = new LatLng(30.3753, 69.3451);

        LatLng pakistan = new LatLng(30.3753, 69.3451);

//      mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        android.app.FragmentManager fr = getActivity().getFragmentManager();





        mMap.addMarker(new MarkerOptions().position(new LatLng(31.5546, 74.3572)).title("Lahore")).showInfoWindow(); //Lahore
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.7294, 73.0931)).title("Islamabad")).showInfoWindow(); //islamabad
        mMap.addMarker(new MarkerOptions().position(new LatLng(24.8615, 67.0099)).title("Karachi")).showInfoWindow();//karachi
        mMap.addMarker(new MarkerOptions().position(new LatLng(31.4187, 73.0791)).title("Faisalabad")).showInfoWindow();//faisalabad
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.1830, 66.9987)).title("Quetta")).showInfoWindow();//Quetta
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.1984, 71.4687)).title("Multan")).showInfoWindow();//Multan
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.0150, 71.5805)).title("Peshawar")).showInfoWindow();//Peshawar

        //  final Dialog dialog = new Dialog(getActivity());

        //mMap.addMarker(new MarkerOptions().position(new LatLng(33.7294, 73.0931)).title("Lahore")); //lahore

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(pakistan));


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                .zoom(20)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
       mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pakistan, 5));

      //  dialog.setContentView(R.layout.custom_dialoge);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker marker) {

                   // Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast

                AlertDialog alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle(marker.getTitle().toString().toUpperCase());
                final String selected_city = marker.getTitle().toString();
                // Setting Dialog Message
                alertDialog.setMessage("Do you want to visit "+marker.getTitle().toString());

                // Setting Icon to Dialog
               // alertDialog.setIcon(R.drawable.tick);

                // Setting OK Button


                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                        intent.putExtra("city", selected_city);
                        intent.putExtra("EXTRA_SESSION_ID", ID);
                        startActivity(intent);



//                        startActivity(new Intent(MainActivity.this,Main4Activity.class));
                       // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on cancel", Toast.LENGTH_SHORT).show();
                    }
                });

//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Write your code here to execute after dialog closed
//                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                    }
//                });

                // Showing Alert Message
                alertDialog.show();











                return true;
            }



        });


        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       setUpMapIfNeeded();
    }
}

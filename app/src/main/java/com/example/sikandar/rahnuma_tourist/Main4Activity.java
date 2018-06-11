package com.example.sikandar.rahnuma_tourist;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.scalified.fab.ActionButton;

import java.util.ArrayList;

//public class Main4Activity extends AppCompatActivity {
//String url = "https://sikandariqbal.net/Rahnuma/test";
//
//    ArrayList<String> check_box_data = new ArrayList<String>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        String s = getIntent().getStringExtra("city");
//
//
//        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);
//        AlertDialog alertDialog = new AlertDialog.Builder(
//                Main4Activity.this).create();
//
//
//        alertDialog.setTitle("Selected city");
//
//
//        alertDialog.setMessage(s);
//
//
//
//        alertDialog.show();
//
//
//
//
//        final ListView lv= (ListView) findViewById(R.id.lv);
//        final Downloader d=new Downloader(this,url,lv,s,check_box_data);
//
//
//
//                                       d.execute();
//
//
//
//
//actionButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//
//    }
//});
//
//
//
//
//    }
//}



public class Main4Activity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    //listAdapter boxAdapter;
    String url = "https://sikandariqbal.net/Rahnuma/test";
    public Downloader d;

String selected_city;

    String ID;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        selected_city = getIntent().getStringExtra("city");

ID = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Button next = (Button) findViewById(R.id.next);
        AlertDialog alertDialog = new AlertDialog.Builder(
                Main4Activity.this).create();


        alertDialog.setTitle("Selected city");


        alertDialog.setMessage(selected_city);



        //alertDialog.show();


        final ListView lvMain = (ListView) findViewById(R.id.lvMain);


        d=new Downloader(this,url,lvMain,selected_city);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

              /*  final long[] checkedIds = lvMain.getCheckItemIds();
                for (int i=0;i<checkedIds.length;i++)
                    Toast.makeText(getApplicationContext(),checkedIds.toString(),Toast.LENGTH_LONG);*/

            }
        });
      d.execute();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(Main4Activity.this,Main5Activity.class);
                intent.putExtra("checkedData", d.getCheckedData());
                intent.putExtra("EXTRA_SESSION_ID", ID);
                intent.putExtra("city", selected_city);
                startActivity(intent);
            }
        });
    }


}

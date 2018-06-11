package com.example.sikandar.rahnuma_tourist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amazon on 4/10/2017.
 */
public class Parser extends AsyncTask <Void,Integer,Integer>{

    public listAdapter boxAdapter;

    Context c;
    String data;
    ListView lv;
    ProgressDialog pd;
//    ArrayList<String> check_box_data = new ArrayList<String>();
    ArrayList<String> players=new ArrayList<>();
    weatherAdapter Wadapter;
    ArrayList<weather2> data_list = new ArrayList<weather2>();

    public listAdapter get_adapter(){
        return this.boxAdapter;
    }

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
       // this.check_box_data = check_box_data;
    }

    public ArrayList<weather2> getcheckeddata(){
        return Wadapter.getSelectedData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parsing Data");
        pd.setMessage("Parsing.....please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parser();


    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==1)
        {

             Wadapter = new weatherAdapter(c, R.layout.row,data_list);
            lv.setAdapter(Wadapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                }
            });
        }
        else
        {
            Toast.makeText(c,"Unable to parse data",Toast.LENGTH_SHORT).show();


        }
        pd.dismiss();
    }
    private int parser()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;
            players.clear();
            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String name=jo.getString("sub_city_name");
                String pic = jo.getString("pic");
               // players.add(name);
                data_list.add(new weather2(name,i,pic,false));
            }
return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

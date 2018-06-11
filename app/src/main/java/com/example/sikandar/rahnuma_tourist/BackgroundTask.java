package com.example.sikandar.rahnuma_tourist;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;


public class BackgroundTask extends AsyncTask<String,String,String> {
    AlertDialog alertDialog;
    Context ctx;
    ProgressDialog pd;
    BackgroundTask(Context ctx)
    {

        this.ctx=ctx;
    }


    @Override
    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(ctx).create();
//        alertDialog.setTitle("Login Information");

        pd=new ProgressDialog(ctx);
        pd.setTitle("Please wait");
        pd.setMessage("");
        pd.show();
    }


    protected String doInBackground(String... params) {
        String reg_url="https://sikandariqbal.net/Rahnuma/tourist_reg";
        String login_url="https://sikandariqbal.net/Rahnuma/tourist_login";
        String method=params[0];
        if(method.equals("register")) {
            String first_name = params[1];
            String last_name = params[2];
            String user_name = params[3];
            String password = params[4];
            String mobile = params[5];
            String email = params[6];



         //   String main_city_id = params[7];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("first_name", "UTF-8")+"="+URLEncoder.encode(first_name, "UTF-8")+"&"+URLEncoder.encode("last_name", "UTF-8")+"="+URLEncoder.encode(last_name, "UTF-8")+"&"+
                        URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+ URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("mobile", "UTF-8")+"="+URLEncoder.encode(mobile, "UTF-8")+"&"+ URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "RegSuccess";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if(method.equals("login"))
        {
            String login_name = params[1];
            String login_pass = params[2];

//            pd=new ProgressDialog(ctx);
//            pd.setTitle("Signing in");
//            pd.setMessage("please wait..");
//            pd.show();

            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("login_name", "UTF-8")+"="+URLEncoder.encode(login_name, "UTF-8")+"&"+URLEncoder.encode("login_pass", "UTF-8")+"="+URLEncoder.encode(login_pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

//
//    @Override
//    protected void onProgressUpdate(String... values) {
//        super.onProgressUpdate(values);
//    }

    @Override
    protected void onPostExecute(String result) {

        if(result.contains("shit")) {
            Toast.makeText(ctx, "Wrong Cradentials, Try again", Toast.LENGTH_LONG).show();
            pd.dismiss();
        }


        else if(result.contains("RegSuccess")){
            pd.dismiss();
            Toast.makeText(ctx, "Registered", Toast.LENGTH_LONG).show();
        }
        else {

//            alertDialog.setMessage(result);
//            alertDialog.show();

pd.dismiss();
            Intent intent = new Intent(ctx, MainActivity.class);
            intent.putExtra("EXTRA_SESSION_ID", result);
            ctx.startActivity(intent);

        }



//            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
//ctx.startActivity(new Intent(ctx,MainActivity.class));




    }
}

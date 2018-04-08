package com.example.customqrscan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by D on 4/9/2018.
 */

public class Page2Activity extends AppCompatActivity {
    private TextView QrString;
    private TextView JsonString;
    private  JsonItem a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        QrString = findViewById(R.id.QrString);
        JsonString = findViewById(R.id.JsonString);


        //Json Parsing(Decode)
        JSONObject mainObject = null;
        try {
            mainObject = new JSONObject(GlobalConstants.QRString);
            String  uniName = mainObject.getString("name");//child
            String uniURL = mainObject.getString("url");//child
            a = new JsonItem(uniName,uniURL);
            QrString.setText("Decode: \n"+"Name is : "+a.getName()+"\n"+"Url is : "+a.getUrl());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonSring = gson.toJson(a);
        JsonString.setText(jsonSring);



    }
}




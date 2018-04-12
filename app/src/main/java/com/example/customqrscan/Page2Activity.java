package com.example.customqrscan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by D on 4/9/2018.
 */

public class Page2Activity extends AppCompatActivity {
    private TextView Voucher;
    private TextView drID;
    private TextView Location;
    private TextView Quotax;
    private JsonItem a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        Voucher = findViewById(R.id.QrString);
        drID = findViewById(R.id.JsonString);
        Location = findViewById(R.id.Location);
        Quotax = findViewById(R.id.quota);

        Quotax.setText("222");


        //Json Parsing(Decode) String -> Json

        JSONObject mainObject = null;
        try {
            mainObject = new JSONObject(GlobalConstants.QRString);
            //String voucher = mainObject.getString("voucher");
            String voucher = mainObject.getString("voucher");
            String DrID = mainObject.getString("DrID");
            String location = mainObject.getString("location");
            String quota = mainObject.getString("quota");

            JsonItem2 a = new JsonItem2(voucher,DrID,location,quota);

            Voucher.setText("醫療單據編號 : "+a.getVoucher());
            drID.setText("醫生編號 : "+a.getDrID());
            Location.setText(a.getLocation());
            Quotax.setText("quota: "+a.getQuota());

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,"error: "+e,Toast.LENGTH_LONG).show();

        }


//        try {
//
//            mainObject = new JSONObject(GlobalConstants.QRString);
//            Log.e("page2aftersetting","SS");Log.e("page2aftertry","SS");

//            String   voucher = mainObject.getString(" voucher");//child  <--!!!! Bug one space before voucher...call crash below
//           **** could check by JSONException e. As it is qrScan(not emulator) better to use Toast.makeText,e to show it
//           **** The above is running fine -> if encounter error Go to throw and the below is skipped
//            String DrID = mainObject.getString("DrID");//child
//            String location = mainObject.getString("location");
//            String quota = mainObject.getString("quota");
//           // a = new JsonItem( voucher,DrID,location,quota);
//            Voucher.setText("醫療單據編號 : "+voucher);
//            drID .setText("醫生編號 : "+DrID);
//            Location.setText(location);
//            //Quota.setText("quota: "+a.getQuota());
//            Quotax.setText(quota);
//            Log.e("page2aftersettingF","SS");
//
//} catch (JSONException e) {
//        e.printStackTrace();
//        }
//
//        //Json Parsing(Encode) Json -> String
//        Gson gson = new Gson();
//        String jsonSring = gson.toJson(a);
//
//
//
//



    }

}




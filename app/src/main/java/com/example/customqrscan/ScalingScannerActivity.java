package com.example.customqrscan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by D on 4/8/2018.
 */

public class ScalingScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler  {

    private static final String FLASH_STATE = "FLASH_STATE";

    private ZXingScannerView mScannerView;
    private boolean mFlash;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scaling_scanner);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);


        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        // You can optionally set aspect ratio tolerance level
        // that is used in calculating the optimal Camera preview size
        mScannerView.setAspectTolerance(0.2f);
        mScannerView.startCamera();
        mScannerView.setFlash(mFlash);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
//        Toast.makeText(this, "Contents = " + rawResult.getText() +
//                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this,MainActivity.class));
        String result = rawResult.getText();

        if(!result.equals("")) {
           if(!TextUtils.isDigitsOnly(result)) {
               GlobalConstants.QRString = result;


               // Note:
               // * Wait 2 seconds to resume the preview.
               // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
               // * I don't know why this is the case but I don't have the time to figure out.




               Toast.makeText(this, "onScanQRCodeSuccess" + GlobalConstants.QRString, Toast.LENGTH_SHORT).show();
               startActivity(new Intent(this, Page2Activity.class));
           }else{
               Toast.makeText(this,"Scan Fails,Please try again"+result,Toast.LENGTH_SHORT).show();
               Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mScannerView.resumeCameraPreview(ScalingScannerActivity.this);
                   }
               }, 300);

           }
        }
    }

}

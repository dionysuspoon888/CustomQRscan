package com.example.customqrscan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by D on 4/13/2018.
 */

public class genqrcode extends Activity {
    ImageView iv_qrcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genqrcode);
        iv_qrcode = findViewById(R.id.iv_qrcode);
        //Json Parsing(Encode) Json -> String
        Gson gson = new Gson();
        JsonItem a = new JsonItem("LXXXXXX","G999","聯合醫務(中環)","5");
        String jsonSring = gson.toJson(a);

        JsonItem2 b = new JsonItem2("LXXXXXX","G999","聯合醫務(中環)","3");
        String jsonSring2 = gson.toJson(b);
        iv_qrcode.setImageBitmap(generateQRCode(jsonSring2));


    }

    public Bitmap generateQRCode(String text) {

        Bitmap mBitmap=null;
//        LogUtil.info("generateQRCode", "generateQRCode:" + (TextUtils.isEmpty(qrMbid) || mBitmap == null || !mbid.equals(qrMbid)) + ", " + TextUtils.isEmpty(mbid)
//                + ", " + (mBitmap == null) + ", " + (!mbid.equals(qrMbid)) +", "+fullCardNo);



        QRCodeWriter writer = new QRCodeWriter();
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0); /* default = 4 */
            BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, (int) convertDpToPixel(166, this), (int) convertDpToPixel(166, this), hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    mBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return mBitmap;

//     imgQRCode.setImageBitmap(mBitmap);


//        try {
//            // generate a 150x150 QR code
//            Bitmap bm = encodeAsBitmap(barcode_content, BarcodeFormat.QR_CODE, 150, 150);
//
//            if(bm != null) {
//                image_view.setImageBitmap(bm);
//            }
//        } catch (WriterException e) { //eek }
    }


    public Bitmap getBarCode(String data) {
//        Bitmap mBitmap = null;
//        com.google.zxing.Writer c9 = new Code39Writer();

        int width;
        int height;
//        if(Utils.getScreenWidth(mContext) <= 1080){
        width = (int) convertDpToPixel(280, this);
        height = (int) convertDpToPixel(66, this);
        MultiFormatWriter writer = new MultiFormatWriter();
        String finalData = Uri.encode(data);

        // Use 1 as the height of the matrix as this is a 1D Barcode.
        BitMatrix bm = null;
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0); /* default = 4 */
            bm = writer.encode(finalData, BarcodeFormat.CODE_128, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
//            LogUtil.info("getBarCode", "getBarCode:"+e.toString());
        }
        Bitmap imageBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        for (int i = 0; i < bm.getWidth(); i++) {
            // Paint columns of width 1
            int[] column = new int[height];
            Arrays.fill(column, bm.get(i, 0) ? Color.BLACK : Color.TRANSPARENT);
            imageBitmap.setPixels(column, 0, 1, i, 0, 1, height);
        }
        return imageBitmap;
    }



    public static float convertDpToPixel(float dp, Context context) {
        float px = dp * getDensity(context);
        return px;
    }

    public static float convertPixelToDp(float px, Context context) {
        float dp = px / getDensity(context);
        return dp;
    }

    public static float getDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
}

package com.zyl.tradeplatform.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhangyinglong on 2017/1/13.
 */
public class CompressImageUtils {
    /**
     * 图片压缩：比例压缩->质量压缩
     *
     * @author  zhangyinglong
     * @date    2017/1/13 16:18
     * @param context
     * @param filePath  图片路径
     */
    public static File compressImage(Context context, String filePath) {
        //比例压缩
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, 720, 1280);
        options.inJustDecodeBounds = false;
        Bitmap bitmapOld = BitmapFactory.decodeFile(filePath, options);
        //质量压缩
        ByteArrayOutputStream bos = new ByteArrayOutputStream();            //输出流
        bitmapOld.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        int option = 100;
        while (bos.toByteArray().length / 1024 > 100) {
            bos.reset();//清空输出流
            option -= 10;
            bitmapOld.compress(Bitmap.CompressFormat.JPEG, option, bos);
            if (option == 1) {
                break;
            }
        }
        bitmapOld.recycle();
        File compressFile = null;
        BufferedOutputStream bufferOS = null;
        try {
            compressFile = new File(context.getCacheDir() + File.separator + UUID.randomUUID().toString() + ".jpeg");
            if (!compressFile.exists()) {
                compressFile.createNewFile();
            }
            bufferOS = new BufferedOutputStream(new FileOutputStream(compressFile));
            bufferOS.write(bos.toByteArray());
            bufferOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferOS != null) {
                    bufferOS.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d("CompressImageUtil", compressFile.length() / 1024 + "kb");

        return compressFile;
    }
    /**
     * 计算缩小比例.
     *
     * @param width     实际宽度
     * @param height    实际高度
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     * @return
     */
    private static int calculateInSampleSize(int width, int height, int reqWidth, int reqHeight) {
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }

            final float totalPixels = width * height;

            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }
}

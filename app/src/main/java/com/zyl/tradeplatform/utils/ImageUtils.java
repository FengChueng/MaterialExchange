package com.zyl.tradeplatform.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhouyou on 2016/6/22.
 * Class desc:
 */
public class ImageUtils {

    public static String compressImage(String srcPath) {
        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;// 这里设置高度为800f
            float ww = 480f;// 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;// be=1表示不缩放
            if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;// 设置缩放比例
            // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            return compressImage(srcPath, bitmap);// 压缩好比例大小后再进行质量压缩
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return srcPath;
    }

    private static String compressImage(String srcPath, Bitmap image) throws IOException {

        Bitmap.CompressFormat Format = Bitmap.CompressFormat.JPEG;
		/*if(srcPath.toLowerCase().endsWith(".png")) {
			Format = Bitmap.CompressFormat.PNG;
		}*/

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Format, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            if(options > 20) {
                options -= 20;//每次都减少10
            } else {
                break;
            }
            image.compress(Format, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }

        String path = findTemFilePath(srcPath);
        FileOutputStream output = new FileOutputStream(path);
        output.write(baos.toByteArray());
        output.flush();
        output.close();

        return path;
    }

    private static String findTemFilePath(String path) {
        String tempath = "";
        File temDir = new File(tempath);
        if(!temDir.exists()) {
            temDir.mkdirs();
        }
        return tempath + UUID.randomUUID() + "_tem" + path.substring(path.lastIndexOf("."));
    }
}

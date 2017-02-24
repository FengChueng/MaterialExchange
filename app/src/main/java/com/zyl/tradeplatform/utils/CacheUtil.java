package com.zyl.tradeplatform.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * 缓存管理
 * @author lanbao
 */
public class CacheUtil {

	/** 是否有缓存 */
	public static boolean isCache = true;

	/**
	 * 获取应用缓存
	 * 
	 * @return size
	 */
	public static String getCacheSize(Context context) {
		long cacheSize = 0;
		try {
			cacheSize = getFolderSize(context.getCacheDir());
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				cacheSize += getFolderSize(context.getExternalCacheDir());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getFormatSize(cacheSize);
		}
		if (cacheSize == 0) {
			isCache = false;
		} else {
			isCache = true;
		}
		return getFormatSize(cacheSize);
	}

	/**
	 * 清除应用缓存
	 * 
	 * @return true: 清除成功 false 清除失败
	 */
	public static boolean cleanCache(Context context) {
		boolean isCleanSuccess = false;
		isCleanSuccess = deleteDir(context.getCacheDir());
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			isCleanSuccess = deleteDir(context.getExternalCacheDir());
		}
		return isCleanSuccess;
	}

	private static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	
	public static long getFolderSize(File file) throws Exception {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 格式化单位
	 * 
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			// return size + "Byte";
			return "0K";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}
}

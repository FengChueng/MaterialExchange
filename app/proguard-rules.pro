# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSoft\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontskipnonpubliclibraryclasses #不去忽略非公共的库类
-dontoptimize		#优化  不优化输入的类文件
-dontpreverify           # 混淆时是否做预校验

-verbose                # 混淆时是否记录日志

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

#---------------------------------默认保留区---------------------------------
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆


-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#不混淆R类
-keep public class cn.nighter.tianxiamendian.R$*{
    public static final int *;
}
#-keep class **.R$* {*;}

-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment
#忽略警告
-ignorewarning

# 泛型与反射
-keepattributes Signature#类型转换错误
-keepattributes EnclosingMethod
-keepattributes *Annotation*
# json解析
-keep class org.json.** {*;}

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }


# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

# support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

# ignore log
-assumenosideeffects class android.util.Log {
   public static *** d(...);
   public static *** v(...);
   public static *** w(...);
 }

#---------------------------------实体类---------------------------------
-keep class cn.nighter.tianxiamendian.entity.** { *; }

#---------------------------------第三方包-------------------------------

#hiflying(wifiBinding)
-keep class com.hiflying.**{*;}

#httpmime
-keep class org.apache.http.entity.mime.**{*;}

#微信支付
-keep class com.tencent.** { *;}
-dontwarn com.tencent.mm.**
##微信支付
#-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
#-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
#-keep class com.tencent.wxop.** { *; }
#-dontwarn com.tencent.mm.**
#-keep class com.tencent.mm.**{*;}
#-keepclasseswithmembernames class ** {
#}

#sharesdk
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}

-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

##httpclient (org.apache.http.legacy.jar)
#-dontwarn android.net.compatibility.**
#-dontwarn android.net.http.**
#-dontwarn com.android.internal.http.multipart.**
#-dontwarn org.apache.commons.**
#-dontwarn org.apache.http.**
#-dontwarn org.apache.http.protocol.**
#-keep class android.net.compatibility.**{*;}
#-keep class android.net.http.**{*;}
#-keep class com.android.internal.http.multipart.**{*;}
#-keep class org.apache.commons.**{*;}
#-keep class org.apache.org.**{*;}
#-keep class org.apache.harmony.**{*;}
-keep class org.apache.http.** { *; }
-keep class org.apache.commons.codec.** { *; }
-keep class org.apache.commons.logging.** { *; }
-keep class android.net.compatibility.** { *; }
-keep class android.net.http.** { *; }
-keep class com.android.internal.http.multipart.** { *; }
-dontwarn org.apache.http.**
-dontwarn android.webkit.**

## nineoldandroids-2.4.0.jar
-keep public class com.nineoldandroids.** {*;}

#百度地图混淆代码
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

#GalleryFinal(图片选择框架)
-keep class cn.finalteam.galleryfinal.widget.*{*;}
-keep class cn.finalteam.galleryfinal.widget.crop.*{*;}
-keep class cn.finalteam.galleryfinal.widget.zoonview.*{*;}

#gson
-keep class com.google.**{*;}

#xutils
#-keep class com.lidroid.** { *; }
#-libraryjars libs/xUtils-2.6.14.jar
-keep class com.lidroid.xutils.** { *; }
-keep public class * extends com.lidroid.xutils.**
-keepattributes Signature
-keepattributes *Annotation*
-keep public interface com.lidroid.xutils.** {*;}
-dontwarn com.lidroid.xutils.**
#个推
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#PhotoPicker
-dontwarn me.iwf.photopicker.**
-keep class me.iwf.photopicker.**{*;}

#umeng
-dontwarn com.umeng.**
-keep class com.umeng.**{*;}
-keep class u.aly.**{*;}
-keep class com.google.**{*;}

#acp动态权限处理
-keep class com.mylhyl.acp.**{*;}

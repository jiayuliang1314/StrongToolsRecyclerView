package glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import glide.Singleton;
import glide.support.CircleBorderTransformation;
import glide.support.FileUtil;
import glide.support.IImageManager;
import glide.support.ImageConfig;
import glide.support.ImageListener;
import glide.support.LoadOption;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//import com.ljy.devring.DevRing;
//import com.ljy.devring.image.support.CircleBorderTransformation;
//import com.ljy.devring.image.support.IImageManager;
//import com.ljy.devring.image.support.ImageConfig;
//import com.ljy.devring.image.support.ImageListener;
//import com.ljy.devring.image.support.LoadOption;
//import com.ljy.devring.util.FileUtil;

/**
 * author:  ljy
 * date:    2018/3/14
 * description: Glide图片加载管理者
 * <p>
 * https://www.jianshu.com/p/2942a57401eb
 */

public class GlideManager implements IImageManager {

    private static final Singleton<GlideManager> gDefault = new Singleton<GlideManager>() {
        @Override
        protected GlideManager create(Context context) {
            return new GlideManager(context);
        }
    };
    private Context mContext;
    private ImageConfig mImageConfig;
    private ExecutorService cacheThreadPool;

    private GlideManager(Context context) {
        ImageConfig imageConfig = new ImageConfig();
        imageConfig
//                .setLoadingResId(R.mipmap.ic_image_load)//设置“加载中”状态时显示的图片
//                .setErrorResId(R.mipmap.ic_image_load)//设置“加载失败”状态时显示的图片
                .setIsShowTransition(false)//设置是否开启状态切换时的过渡动画，默认false
//                .setIsUseOkhttp(false)//设置是否使用okhttp3作为网络组件，默认true
//                .setMemoryCacheSize(size)//设置内存缓存大小，不建议设置，使用框架默认设置的大小即可
//                .setBitmapPoolSize(size)//设置Bitmap池大小，设置内存缓存大小的话一般这个要一起设置，不建议设置，使用框架默认设置的大小即可
//                .setDiskCacheFile(file)//设置具体的磁盘缓存地址，传入的file需为文件夹
//                .setDiskCacheSize(200*1024*1024)//设置磁盘缓存大小，单位byte，默认250M
                .setIsDiskCacheExternal(false);//设置磁盘缓存地址是否在外部存储中，默认false
        init(context, imageConfig);
    }

    public static GlideManager getInstance(Context context) {
        return gDefault.get(context);
    }

    @Override
    public void init(Context context, ImageConfig imageConfig) {
        mContext = context;
        mImageConfig = imageConfig;
    }

    public ImageConfig getImageConfig() {
        return mImageConfig;
    }

    @Override
    public void loadNet(String url, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(url), null).into(imageView);
    }

    @Override
    public void loadNet(String url, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load(url), loadOption).into(imageView);
    }

    @Override
    public void setImageResource(ImageView imageView, int resId) {
        load(Glide.with(imageView.getContext()).load(resId), null).into(imageView);
    }

    @Override
    public void loadRes(int resId, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(resId), null).into(imageView);
    }

    @Override
    public void loadRes(int resId, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load(resId), loadOption).into(imageView);
    }

    @Override
    public void loadAsset(String assetName, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load("file:///android_asset/" + assetName), null).into(imageView);
    }

    @Override
    public void loadAsset(String assetName, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load("file:///android_asset/" + assetName), loadOption).into(imageView);
    }

    @Override
    public void loadFile(File file, ImageView imageView) {
        load(Glide.with(imageView.getContext()).load(file), null).into(imageView);
    }

    @Override
    public void loadFile(File file, ImageView imageView, LoadOption loadOption) {
        load(Glide.with(imageView.getContext()).load(file), loadOption).into(imageView);
    }

    @Override
    public void preLoad(String url) {
        Glide.with(mContext).load(url).preload();
    }

    @Override
    public void getBitmap(Context context, String url, final ImageListener<Bitmap> imageListener) {
        RequestOptions options = new RequestOptions()
                .override(Target.SIZE_ORIGINAL); //指定图片大小(原图)
        Glide.with(context).asBitmap().load(url).apply(options).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                if (imageListener != null) {
                    imageListener.onFail(e);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                if (imageListener != null) {
                    imageListener.onSuccess(resource);
                }
                return false;
            }
        }).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Override
    public void downLoadImage(final Context context, final String url, final File targetFile, final ImageListener<File> imageListener) {
        if (cacheThreadPool == null) {
            cacheThreadPool = Executors.newCachedThreadPool();
        }

        cacheThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    File sourceFile = Glide.with(context).asFile().load(url).submit().get();
                    if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                        imageListener.onSuccess(targetFile);//回调在后台线程
                    }
                } catch (Exception exception) {
                    if (imageListener != null) {
                        imageListener.onFail(exception);//回调在后台线程
                    }
                }
            }
        });
    }

    @Override
    public void downLoadImage(final Context context, final String[] url, final File targetFile, final ImageListener<File> imageListener) {
        if (cacheThreadPool == null) {
            cacheThreadPool = Executors.newCachedThreadPool();
        }

        cacheThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    File sourceFile = Glide.with(context).asFile().load(url[0]).submit().get();
                    if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                        imageListener.onSuccess(targetFile);//回调在后台线程
                    }
                } catch (Throwable exception) {
                    try {
                        File sourceFile = Glide.with(context).asFile().load(url[1]).submit().get();
                        if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                            imageListener.onSuccess(targetFile);//回调在后台线程
                        }
                    } catch (Throwable exception1) {
                        try {
                            File sourceFile = Glide.with(context).asFile().load(url[2]).submit().get();
                            if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                                imageListener.onSuccess(targetFile);//回调在后台线程
                            }
                        } catch (Throwable exception2) {
                            try {
                                File sourceFile = Glide.with(context).asFile().load(url[3]).submit().get();
                                if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                                    imageListener.onSuccess(targetFile);//回调在后台线程
                                }
                            } catch (Throwable exception3) {
                                if (imageListener != null) {
                                    imageListener.onFail(exception);//回调在后台线程
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void clearMemoryCache() {
        //Glide要求清除内存缓存需在主线程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Glide.get(mContext).clearMemory();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearMemory();
                }
            });
        }
    }

    @Override
    public void clearDiskCache() {
        //Glide要求清除内存缓存需在后台程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(mContext).clearDiskCache();
        }
    }

    public void loadNet(String[] servers, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        //You can’t start or clear loads in RequestListener or Target callbacks
        //https://muyangmin.github.io/glide-docs-cn/doc/debugging.html#you-cant-start-or-clear-loads-in-requestlistener-or-target-callbacks
        Glide.with(imageView.getContext()).load(servers[0] + url).apply(requestOptions).error(
                Glide.with(imageView.getContext()).load(servers[1] + url).apply(requestOptions).error(
                        Glide.with(imageView.getContext()).load(servers[2] + url).apply(requestOptions).error(
                                Glide.with(imageView.getContext()).load(servers[3] + url).apply(requestOptions)
                        )
                )
        ).into(imageView);
//        Glide.with(imageView.getContext()).load(servers[0] + url).apply(requestOptions).addListener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                Glide.with(imageView.getContext()).load(servers[1] + url).apply(requestOptions).addListener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Glide.with(imageView.getContext()).load(servers[2] + url).apply(requestOptions).addListener(new RequestListener<Drawable>() {
//                            @Override
//                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                Glide.with(imageView.getContext()).load(servers[3] + url).apply(requestOptions).addListener(new RequestListener<Drawable>() {
//                                    @Override
//                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//
//                                        return true;
//                                    }
//
//                                    @Override
//                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                        return false;
//                                    }
//                                }).into(imageView);
//                                return true;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                return false;
//                            }
//                        }).into(imageView);
//                        return true;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                }).into(imageView);
//                return true;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                return false;
//            }
//        }).into(imageView);
    }

    private RequestBuilder load(RequestBuilder requestBuilder, LoadOption loadOption) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//        mImageConfig = DevRing.ringComponent().imageConfig();
        //使用全局的配置进行设置
        if (loadOption == null) {
            if (mImageConfig.isShowTransition()) {
                requestBuilder.transition(DrawableTransitionOptions.withCrossFade(600));
            }

            if (mImageConfig.getLoadingResId() > 0) {
                requestOptions.placeholder(mImageConfig.getLoadingResId());
            }

            if (mImageConfig.getErrorResId() > 0) {
                requestOptions.error(mImageConfig.getErrorResId());
            }
        }
        //使用临时的配置进行设置
        else {
            if (loadOption.isShowTransition()) {
                requestBuilder.transition(DrawableTransitionOptions.withCrossFade(600));
            }

            if (loadOption.getLoadingResId() > 0) {
                requestOptions.placeholder(loadOption.getLoadingResId());
            }

            if (loadOption.getErrorResId() > 0) {
                requestOptions.error(loadOption.getErrorResId());
            }

            CircleBorderTransformation circleTransformation = null;
//            CropCircleTransformation circleTransformation = null;
            RoundedCornersTransformation roundedCornersTransformation = null;
            BlurTransformation blurTransformation = null;
            GrayscaleTransformation grayscaleTransformation = null;

            if (loadOption.isCircle()) {
//                circleTransformation = new CropCircleTransformation();
                int borderWidth = loadOption.getBorderWidth();
                int borderColor = loadOption.getBorderColor();
                if (borderWidth > 0 && borderColor != 0) {
                    circleTransformation = new CircleBorderTransformation(borderWidth, borderColor);
                } else {
                    circleTransformation = new CircleBorderTransformation();
                }
            } else if (loadOption.getRoundRadius() > 0) {
                roundedCornersTransformation = new RoundedCornersTransformation(loadOption.getRoundRadius(), 0);
            }

            if (loadOption.getBlurRadius() > 0) {
                blurTransformation = new BlurTransformation(loadOption.getBlurRadius());
            }

            if (loadOption.isGray()) {
                grayscaleTransformation = new GrayscaleTransformation();
            }

            MultiTransformation multiTransformation = getMultiTransformation(new CenterCrop(), circleTransformation, roundedCornersTransformation, blurTransformation, grayscaleTransformation);
            if (multiTransformation != null) requestOptions.transform(multiTransformation);
        }
        return requestBuilder.apply(requestOptions);
    }

    private MultiTransformation getMultiTransformation(Transformation... transformations) {
        List<Transformation> list = new ArrayList<>();

        for (int i = 0; i < transformations.length; i++) {
            if (transformations[i] != null) list.add(transformations[i]);
        }

        if (list.isEmpty()) {
            return null;
        } else {
            return new MultiTransformation(list);
        }
    }


}

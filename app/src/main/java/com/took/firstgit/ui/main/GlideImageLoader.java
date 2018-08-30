package com.took.firstgit.ui.main;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.took.firstgit.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    private Context _context;

    public GlideImageLoader(Context context) {
        this._context = context;
    }

    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }

    public GlideImageLoader() {
    }

    @Override
    public void displayImage(Context context, Object object, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法

        String test_img = "http://www.sinaimg.cn/dy/slidenews/1_img/2017_50/88490_1556737_720730.jpg";
        test_img = (String) object;
        if(_context == null) return;
        Activity activity = (Activity) _context;
        if(activity.isFinishing()) return;
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .error(R.mipmap.ic_launcher);

        Glide.with(activity)
                .load(test_img)
                .apply(options)
                .into(imageView);
    }
}
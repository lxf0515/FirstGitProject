package com.took.firstgit.ui.picture;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.zxing.Result;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.took.firstgit.R;
import com.took.firstgit.utils.scan.ScannImageUtils;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;

import static com.lzy.imagepicker.ui.ImageGridActivity.EXTRAS_TAKE_PICKERS;

public class PictureSelectActivity extends AppCompatActivity{
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    ArrayList<ImageItem> images = null;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 1;               //允许选择图片最大数

    private Button pzBut,xcBut;
    private ImageView showImage;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_picture_select);
        context = this;
        selImageList = new ArrayList<>();
        initImagePicker();

        pzBut     = findViewById(R.id.o_picture_pz_but);
        xcBut     = findViewById(R.id.o_picture_xc_but);
        showImage = findViewById(R.id.o_picture_img);

        setListener();

        /**权限申请**/
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .send();
    }

    private void initImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void setListener(){
        pzBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.getInstance().setSelectLimit(maxImgCount);
                //ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(context, ImageGridActivity.class);
                intent.putExtra(EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }
        });
        xcBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selImageList != null ) selImageList.clear();
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount);
                //ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(context, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    try {
                        selImageList.addAll(images);
                        System.out.println("selImageList path is " + selImageList.get(0).path);
                        Glide.with(this).clear(showImage);
                        ImagePicker.getInstance().getImageLoader().displayImage((Activity) context, selImageList.get(0).path, showImage, 0, 0);

                        Result result = ScannImageUtils.decodeBarcodeRGB(selImageList.get(0).path,true);
                        if(result != null){
                            System.out.println("result is " + result.toString());
                        }else {
                            System.out.println("result is null" );
                        }
                    }catch (Exception e){
                        System.out.println("Exception is " + e.toString());
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    ImagePicker.getInstance().getImageLoader().displayImage((Activity) context, selImageList.get(0).path, showImage, 0, 0);
                }
            }
        }
    }



}

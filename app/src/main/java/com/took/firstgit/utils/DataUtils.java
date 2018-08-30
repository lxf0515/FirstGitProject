package com.took.firstgit.utils;

import com.took.firstgit.ui.auto.other.CardItem;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    /**
     * 获取图片集合
     * @return
     */
    public static List<String> getBannerList(){
        List<String> list = new ArrayList<>();
        list.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        list.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        list.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");

        return list;
    }

    /**
     * 获取图片集合
     * @return
     */
    public static List<CardItem> getBannerList2(){
        List<CardItem> list = new ArrayList<>();
        CardItem cardItem1 = new CardItem("","http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        list.add(cardItem1);
        CardItem cardItem2 = new CardItem("","http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        list.add(cardItem2);
        CardItem cardItem3 = new CardItem("","http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        list.add(cardItem3);
        return list;
    }

    /**
     * 获取图片集合
     * @return
     */
    public static List<String> getBannerTitleList(){
        List<String> list = new ArrayList<>();
        list.add("Banner1");
        list.add("Banner2");
        list.add("Banner3");
        return list;
    }

    /**
     * 获取数据集合
     * @param page
     * @return
     */
    public static List<String> getDataList(int page){
        List<String> list = new ArrayList<>();
        for(int i=0;i< 25;i++ ){
            list.add("List " + page + " == " + i);
        }
        return list;
    }
}

package com.github.tyranitarx.Kyaru.Utils;


public class NlpUtil {
    public static void main(String args[]){
        String setu = "[mirai:source:122623,-370915150]东方色图[mirai:at:3044668489]";
        String reg = "\\[[^\\u4e00-\\u9fa5]*\\]";
        String x =setu.replaceAll(reg,"");
        String keyword[] = x.split("色图");
        System.out.println(keyword[0]);
    }
}

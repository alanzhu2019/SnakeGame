package com.alan.udp;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread  实现多线程同步下载图片
public class TestThrad2 extends Thread{
    private String url;  //图片地址
    private String name;  //保存的文件地址

    public TestThrad2(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        webDownloader wd  = new webDownloader();
        wd.downloader(url,name);
        System.out.println("下载的文件名称为：" + name);
    }


    public static void main(String[] args) {
        TestThrad2 ts1 = new TestThrad2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/28/kuangstudy64a54e27-7e96-4b9f-9fdc-4398a7b39eef.png","src\\statics\\1.png");
        TestThrad2 ts2 = new TestThrad2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/13/kuangstudy5584e809-f8cd-42c5-b8e2-cc2e69a2f9a8.png","src\\statics\\2.png");
        TestThrad2 ts3 = new TestThrad2("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/04/13/kuangstudy4abb3661-55d7-4e27-94bb-f666134b92ba.jpg","src\\statics\\3.png");
        ts1.start();
        ts2.start();
        ts3.start();
    }

}



//下载器
class webDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题！");
        }
    }
}

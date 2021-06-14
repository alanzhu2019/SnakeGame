package com.alan.snake;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {

    //定部图片
    public static URL headerurl = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerurl);

    //头部
    public static URL upurl = Data.class.getResource("/statics/up.png");
    public static URL downurl = Data.class.getResource("/statics/down.png");
    public static URL lefturl = Data.class.getResource("/statics/left.png");
    public static URL righturl = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upurl);
    public static ImageIcon down = new ImageIcon(downurl);
    public static ImageIcon left = new ImageIcon(lefturl);
    public static ImageIcon right = new ImageIcon(righturl);

    //身体
    public static URL bodyurl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyurl);

    //实物
    public static URL foodurl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodurl);
}

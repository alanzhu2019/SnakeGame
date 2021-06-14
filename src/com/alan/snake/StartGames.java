package com.alan.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //绘制一个静态窗口 JFrame
        JFrame jFrame = new JFrame("alan-贪吃蛇小游戏");
        //设置界面大小
        jFrame.setBounds(10,10,900,720);
        jFrame.setResizable(false);//窗口大小则不可改变
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭实践

        jFrame.add(new GamePanel());

        jFrame.setVisible(true);//显示可见性



    }
}

package com.alan.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int lenth;//蛇的长度
    int[] snakeX = new int[600]; //蛇的坐标X
    int[] snakeY = new int[500];//蛇的坐标Y
    String fx ;  //右：R  左：L   上：U    下:D

    boolean isStatrt = false; //游戏是否开始

    int delay =100 ;
    Timer timer = new Timer(delay,this);//定时器

    //定义一个食物
    int foodX;
    int foodY;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;

    //积分系统
    int score;

    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start(); //让时间动起来
    }

    //初始化
    public void init(){
        fx = "R";
        lenth = 3;
        snakeX[0] = 100;snakeY[0] = 100;  //头部坐标
        snakeX[1] = 75 ;snakeY[1] = 100;//第一个身体的坐标
        snakeX[2] = 50 ;snakeY[2] = 100; //第二个身体的坐标

        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);
        score = 0;
    }


    //首先要有个画板：    画界面，画蛇
    //Graphics  画笔

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  //清屏
        this.setBackground(Color.white);//设置背景颜色
        //绘制头部广告栏
        Data.header.paintIcon(this,g,25,11);

        //绘制游戏区域
        g.fillRect(25,75,850,600);


        //画一条静态的小蛇
        if (fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }


        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);  //蛇的身体长度通过lenth来控制
        }

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,10));
        g.drawString("长度：" + lenth,750,35);
        g.drawString("分数：" + score,750,50);
        //画食物
        Data.food.paintIcon(this,g,foodX,foodY);

        //游戏提示：是否开始
        if (isStatrt == false){
            g.setColor(Color.WHITE);  //设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40)); //设置字体
            g.drawString("按下空格开始游戏",300,300);
        }

        //失败提醒
        if (isFail){
            g.setColor(Color.RED);  //设置画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40)); //设置字体
            g.drawString("游戏结束，按空格重新开始",200,300);
        }


    }



    //键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
       //键盘按下，未释放
        //获取键盘按下的键位
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE){ //按下空格键启动或暂停
            if (isFail){
                isFail = false;
                init();
            }else {
                isStatrt = !isStatrt;
            }
            repaint(); //刷新界面
        }

        if (keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        }else if (keyCode == KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }
    }


    //定时器，监听时间，帧 :执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态
        if (isStatrt && isFail == false){
            //初始化默认右移
            for (int i = lenth - 1; i > 0 ; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过控制方向让头部移动
            if (fx.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if (snakeX[0] >850){isFail = true;}
            }else if (fx.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if (snakeX[0] <25){isFail = true;}
            }else if (fx.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75){isFail = true;}
            }else if (fx.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] >600){isFail = true;}
            }

            //如果小蛇的头和食物坐标重合了
            if (snakeX[0] == foodX && snakeY[0] == foodY){
                //长度+1
                lenth++;
                score += 10;
            if (lenth % 2 == 0){
                delay = delay - 10;
            }

                //重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodX = 75 + 25 * random.nextInt(24);
            }

            for (int i = 1; i <lenth ; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }





            repaint(); //刷新界面
        }
        timer.start(); //让时间动起来
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //键盘释放
    }


}

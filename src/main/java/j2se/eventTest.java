package j2se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 很好的事件监听示例
 */

public class eventTest extends JFrame {
    //设定组件
    MyPanel mp = null;

    public static void main(String[] args) {
        eventTest win = new eventTest();
    }

    public eventTest() {
        //创建组件
        mp = new MyPanel();

        //注册监听
        this.addMouseListener(mp);
        this.addMouseMotionListener(mp);
        this.addKeyListener(mp);
        this.addWindowListener(mp);

        //加入组件
        this.add(mp);
        //设置窗体
        this.setTitle("事件多监听多处理");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
//1、让MyPanel知道鼠标按下的消息，并且知道点击的位置(x,y)
//2、让MyPanel知道哪个键按下了

class MyPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, WindowListener {
    public void paint(Graphics g) {
        super.paint(g);
    }

    //鼠标点击(mouseClicked)
    public void mouseClicked(MouseEvent e) {
        System.out.println("鼠标点击了x=" + e.getX() + "y=" + e.getY());
    }

    //鼠标移动到MyPanel(mouseEntered)
    public void mouseEntered(MouseEvent e) {
        System.out.println("鼠标移动到MyPanel上了");
    }

    //鼠标离开MyPanel(mouseExited)
    public void mouseExited(MouseEvent e) {
        System.out.println("鼠标离开MyPanel上了");
    }

    //鼠标按下去(mousePressed)
    public void mousePressed(MouseEvent e) {
        System.out.println("鼠标被按下了");
    }

    //鼠标松开(mouseReleased)
    public void mouseReleased(MouseEvent e) {
        System.out.println("鼠标被松开了");
    }

    //鼠标拖拽(mouseDragged)
    public void mouseDragged(MouseEvent e) {
        System.out.println("鼠标拖拽x=" + e.getX() + "y=" + e.getY());
    }

    //鼠标移动(mouseMoved)
    public void mouseMoved(MouseEvent e) {
        System.out.println("鼠标移动时X=" + e.getX() + "Y=" + e.getY());
    }

    //键输入值(keyTyped)，F(1-12)无响应
    public void keyTyped(KeyEvent e) {
        System.out.println("按下了" + e.getKeyChar() + "键");
    }

    //键按下(keyPressed)
    public void keyPressed(KeyEvent e) {
        System.out.println("按下了" + e.getKeyChar() + "键");
    }

    //键松开(keyReleased)
    public void keyReleased(KeyEvent e) {
        System.out.println("按下了" + e.getKeyChar() + "键");
    }

    //打开窗口(windowOpened)
    public void windowOpened(WindowEvent e) {
        System.out.println("打开窗口(windowOpened)");
    }

    //窗口关闭(windowClosing)
    public void windowClosing(WindowEvent e) {
        System.out.println("窗口关闭(windowClosing)");
    }

    //窗口关闭(windowClosed)
    public void windowClosed(WindowEvent e) {
        System.out.println("窗口关闭(windowClosed)");
    }

    //窗口最小化(windowIconified)
    public void windowIconified(WindowEvent e) {
        System.out.println("窗口最小化(windowIconified)");
    }

    //恢复窗口(windowDeiconified)
    public void windowDeiconified(WindowEvent e) {
        System.out.println("恢复窗口(windowDeiconified)");
    }

    //激活窗口，使用窗口(windowActivated)
    public void windowActivated(WindowEvent e) {
        System.out.println("激活窗口(windowActivated)");
    }

    //窗口停用,切换窗口(windowDeactivated)
    public void windowDeactivated(WindowEvent e) {
        System.out.println("窗口停用(windowDeactivated)");
    }
}
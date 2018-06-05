package j2se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * �ܺõ��¼�����ʾ��
 */

public class eventTest extends JFrame {
    //�趨���
    MyPanel mp = null;

    public static void main(String[] args) {
        eventTest win = new eventTest();
    }

    public eventTest() {
        //�������
        mp = new MyPanel();

        //ע�����
        this.addMouseListener(mp);
        this.addMouseMotionListener(mp);
        this.addKeyListener(mp);
        this.addWindowListener(mp);

        //�������
        this.add(mp);
        //���ô���
        this.setTitle("�¼�������ദ��");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
//1����MyPanel֪����갴�µ���Ϣ������֪�������λ��(x,y)
//2����MyPanel֪���ĸ���������

class MyPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, WindowListener {
    public void paint(Graphics g) {
        super.paint(g);
    }

    //�����(mouseClicked)
    public void mouseClicked(MouseEvent e) {
        System.out.println("�������x=" + e.getX() + "y=" + e.getY());
    }

    //����ƶ���MyPanel(mouseEntered)
    public void mouseEntered(MouseEvent e) {
        System.out.println("����ƶ���MyPanel����");
    }

    //����뿪MyPanel(mouseExited)
    public void mouseExited(MouseEvent e) {
        System.out.println("����뿪MyPanel����");
    }

    //��갴��ȥ(mousePressed)
    public void mousePressed(MouseEvent e) {
        System.out.println("��걻������");
    }

    //����ɿ�(mouseReleased)
    public void mouseReleased(MouseEvent e) {
        System.out.println("��걻�ɿ���");
    }

    //�����ק(mouseDragged)
    public void mouseDragged(MouseEvent e) {
        System.out.println("�����קx=" + e.getX() + "y=" + e.getY());
    }

    //����ƶ�(mouseMoved)
    public void mouseMoved(MouseEvent e) {
        System.out.println("����ƶ�ʱX=" + e.getX() + "Y=" + e.getY());
    }

    //������ֵ(keyTyped)��F(1-12)����Ӧ
    public void keyTyped(KeyEvent e) {
        System.out.println("������" + e.getKeyChar() + "��");
    }

    //������(keyPressed)
    public void keyPressed(KeyEvent e) {
        System.out.println("������" + e.getKeyChar() + "��");
    }

    //���ɿ�(keyReleased)
    public void keyReleased(KeyEvent e) {
        System.out.println("������" + e.getKeyChar() + "��");
    }

    //�򿪴���(windowOpened)
    public void windowOpened(WindowEvent e) {
        System.out.println("�򿪴���(windowOpened)");
    }

    //���ڹر�(windowClosing)
    public void windowClosing(WindowEvent e) {
        System.out.println("���ڹر�(windowClosing)");
    }

    //���ڹر�(windowClosed)
    public void windowClosed(WindowEvent e) {
        System.out.println("���ڹر�(windowClosed)");
    }

    //������С��(windowIconified)
    public void windowIconified(WindowEvent e) {
        System.out.println("������С��(windowIconified)");
    }

    //�ָ�����(windowDeiconified)
    public void windowDeiconified(WindowEvent e) {
        System.out.println("�ָ�����(windowDeiconified)");
    }

    //����ڣ�ʹ�ô���(windowActivated)
    public void windowActivated(WindowEvent e) {
        System.out.println("�����(windowActivated)");
    }

    //����ͣ��,�л�����(windowDeactivated)
    public void windowDeactivated(WindowEvent e) {
        System.out.println("����ͣ��(windowDeactivated)");
    }
}
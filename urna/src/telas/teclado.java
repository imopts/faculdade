package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teclado extends JFrame implements ActionListener {

    protected JLabel L1, L2, LIMG;
    protected JPanel P4,P5, TECLADO;
    protected JButton B1,B2,B3,B4,B5,B6,B7,B8,B9,B0,bCorrige,bBranco,bConfirma;
    protected Dimension DM1,DM2;

    teclado(){

        TECLADO = new JPanel();
        TECLADO.setSize(400,600);
        TECLADO.setLocation(820,20);
        TECLADO.setLayout(null);
        TECLADO.setBackground(new Color(210, 210, 210));
        TECLADO.setBorder(new MatteBorder(2, 0, 2, 2, Color.BLACK));
        TECLADO.setVisible(true);

        DM1=new Dimension();
        DM1.setSize(60,40);
        DM2=new Dimension();
        DM2.setSize(64,34);

        LIMG = new JLabel("");
        Image IMG = new  ImageIcon(this.getClass().getResource("../imagens/brasao.gif")).getImage();
        LIMG.setIcon(new ImageIcon(IMG));
        LIMG.setSize(170,96);
        LIMG.setLocation(0,42);

        P4=new JPanel(); // teclado
        P4.setSize(280,360);
        P4.setLocation(0,130);
        P4.setLayout(null);
        P4.setBackground(new Color(40, 40, 40));

        P5=new JPanel (); // Panel onde fica escrito Justiça eleitoral tarja //
        P5.setSize(210,70);
        P5.setLocation(70,55);
        P5.setLayout(null);
        P5.setVisible(true);
        P5.setBackground(Color.WHITE);

        B1 = new JButton("");
        B1.setBackground(Color.BLACK);
        B1.setSize(DM1);
        B1.setLocation(40,40);
        Image IB1=new ImageIcon(this.getClass().getResource("../imagens/1.gif")).getImage();
        B1.setIcon(new ImageIcon(IB1));
        B1.setVisible(true);

        B2 = new JButton("");
        B2.setBackground(Color.BLACK);
        B2.setSize(DM1);
        B2.setLocation(110,40);
        Image IB2=new ImageIcon(this.getClass().getResource("../imagens/2.gif")).getImage();
        B2.setIcon(new ImageIcon(IB2));
        B2.setVisible(true);

        B3 = new JButton("");
        B3.setBackground(Color.BLACK);
        B3.setSize(DM1);
        B3.setLocation(180,40);
        Image IB3=new ImageIcon(this.getClass().getResource("../imagens/3.gif")).getImage();
        B3.setIcon(new ImageIcon(IB3));
        B3.setVisible(true);

        B4 = new JButton("");
        B4.setBackground(Color.lightGray);
        B4.setSize(DM1);
        B4.setLocation(40,100);
        Image IB4=new ImageIcon(this.getClass().getResource("../imagens/4.gif")).getImage();
        B4.setIcon(new ImageIcon(IB4));
        B4.setVisible(true);

        B5 = new JButton("");
        B5.setBackground(Color.lightGray);
        B5.setSize(DM1);
        B5.setLocation(110,100);
        Image IB5=new ImageIcon(this.getClass().getResource("../imagens/5.gif")).getImage();
        B5.setIcon(new ImageIcon(IB5));
        B5.setVisible(true);

        B6 = new JButton("");
        B6.setBackground(Color.lightGray);
        B6.setSize(DM1);
        B6.setLocation(180,100);
        Image IB6=new ImageIcon(this.getClass().getResource("../imagens/6.gif")).getImage();
        B6.setIcon(new ImageIcon(IB6));
        B6.setVisible(true);

        B7 = new JButton("");
        B7.setBackground(Color.lightGray);
        B7.setSize(DM1);
        B7.setLocation(40,160);
        Image IB7=new ImageIcon(this.getClass().getResource("../imagens/7.gif")).getImage();
        B7.setIcon(new ImageIcon(IB7));
        B7.setVisible(true);

        B8 = new JButton("");
        B8.setBackground(Color.lightGray);
        B8.setSize(DM1);
        B8.setLocation(110,160);
        Image IB8=new ImageIcon(this.getClass().getResource("../imagens/8.gif")).getImage();
        B8.setIcon(new ImageIcon(IB8));
        B8.setVisible(true);

        B9 = new JButton("");
        B9.setBackground(Color.lightGray);
        B9.setSize(DM1);
        B9.setLocation(180,160);
        Image IB9=new ImageIcon(this.getClass().getResource("../imagens/9.gif")).getImage();
        B9.setIcon(new ImageIcon(IB9));
        B9.setVisible(true);

        B0 = new JButton("");
        B0.setBackground(Color.lightGray);
        B0.setSize(DM1);
        B0.setLocation(110,220);
        Image IB0=new ImageIcon(this.getClass().getResource("../imagens/0.gif")).getImage();
        B0.setIcon(new ImageIcon(IB0));
        B0.setVisible(true);

        bCorrige=new JButton("");
        Image IMG3=new ImageIcon(this.getClass().getResource("../imagens/Corrige.gif")).getImage();
        bCorrige.setIcon(new ImageIcon(IMG3));
        bCorrige.setSize(DM2);
        bCorrige.setLocation(100,310);

        bBranco = new JButton("");
        Image IMG2 = new  ImageIcon(this.getClass().getResource("../imagens/branco.gif")).getImage();
        bBranco.setIcon(new ImageIcon(IMG2));
        bBranco.setSize(DM2);
        bBranco.setLocation(20,310);

        bConfirma= new JButton("");
        Image IMG1= new ImageIcon(this.getClass().getResource("../imagens/conf.gif")).getImage();
        bConfirma.setIcon(new ImageIcon(IMG1));
        bConfirma.setSize(64,40);
        bConfirma.setLocation(180,304);

        L1=new JLabel("JUSTIÇA");
        L1.setSize(90,40);
        L1.setLocation(70,5);
        L1.setFont(new Font ("Arial", Font.BOLD, 20));

        L2=new JLabel("ELEITORAL");
        L2.setSize(150,40);
        L2.setLocation(60,30);
        L2.setFont((new Font ("Arial", Font.BOLD, 20)));

        P4.add(B0);
        P4.add(B1);
        P4.add(B2);
        P4.add(B3);
        P4.add(B4);
        P4.add(B5);
        P4.add(B6);
        P4.add(B7);
        P4.add(B8);
        P4.add(B9);
        P4.add(bCorrige);
        P4.add(bConfirma);
        P4.add(bBranco);

        P5.add(L1);
        P5.add(L2);
        TECLADO.add(P4);
        TECLADO.add(P5);
        TECLADO.add(LIMG);

        B1.addActionListener(this);
        B2.addActionListener(this);
        B3.addActionListener(this);
        B4.addActionListener(this);
        B5.addActionListener(this);
        B6.addActionListener(this);
        B7.addActionListener(this);
        B8.addActionListener(this);
        B9.addActionListener(this);
        B0.addActionListener(this);

        bBranco.addActionListener(this);
        bCorrige.addActionListener(this);
        bConfirma.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}


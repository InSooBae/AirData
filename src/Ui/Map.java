package Ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import airdata.Cai;
import db.Air;


public class Map extends JFrame {
   String imageurlGreen = "resource/greencircle.png";
   String imageurlBlue = "resource/bluecircle.png";
   String imageurlYellow = "resource/yellowcircle.png";
   String imageurlRed = "resource/redcircle.png";
   String imageurlMap = "resource/map.png";
   JButton[] buttons = new JButton[25];
   int[] color = new int[25];
   int i = 0;
	
   
   Map(List<Air> airList, String month, String date){
      
      double no2p, o3p, cop, so2p; 
      int pm10, pm25, x, y;
      
      setTitle("지도");
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
      JPanel top = new JPanel();
      JPanel west = new JPanel();
      JPanel south = new JPanel();
      JPanel check = new JPanel();
      JPanel smallnorth = new JPanel();
      SCircle circlePanel = new SCircle();
      imagePanel imagepanel = new imagePanel();
      
      top.setLayout(new BorderLayout());
      west.setLayout(new BorderLayout());
      south.setLayout(new BorderLayout());
      check.setLayout(new GridLayout(4,2));
      smallnorth.setLayout(new BorderLayout());
      imagepanel.setLayout(null);
      JLabel jl = new JLabel("2018년 " + month + "월  " + date + "일");
      jl.setFont(jl.getFont().deriveFont(30.0f));

      //JButton checkbtn = new JButton("CAI별 대기 확인");
      JCheckBox jck1 = new JCheckBox("좋음(0~50)");
      JCheckBox jck2 = new JCheckBox("보통(51~100)");
      JCheckBox jck3 = new JCheckBox("나쁨(101~250)");
      JCheckBox jck4 = new JCheckBox("매우나쁨(250~)");
      
      for (Air a : airList) {
         
         Cai cai = new Cai(a);
         
         x = a.getX();
         y = a.getY();
         buttons[i] = setJButton(cai, a, month, date, x, y, cai.getCAI(), i);
         imagepanel.add(buttons[i]);
		 i++;  
      }
      
      setSize(800, 700);
      setVisible(true);
      check.add(jck1);
      check.add(jck2);
      check.add(jck3);
      check.add(jck4);
      top.add(jl, BorderLayout.EAST);
      //smallnorth.add(checkbtn, BorderLayout.NORTH);
      smallnorth.add(check, BorderLayout.WEST);
      smallnorth.add(circlePanel, BorderLayout.EAST);
      west.add(smallnorth, BorderLayout.SOUTH);
      add(imagepanel, BorderLayout.CENTER);
      add(top, BorderLayout.NORTH);
      add(west, BorderLayout.WEST);
      add(south, BorderLayout.SOUTH);
      
      
      jck1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					for(int a = 0; a<25; a++) {
						if(color[a]==1)
							buttons[a].setVisible(true);
						}
					}
				else
					for(int b = 0; b<25; b++) {
						if(color[b]==1)
							buttons[b].setVisible(false);
						}
					}
			});
		jck2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					for(int a = 0; a<25; a++) {
						if(color[a]==2)
							buttons[a].setVisible(true);
						}
					}
				else
					for(int b = 0; b<25; b++) {
						if(color[b]==2)
							buttons[b].setVisible(false);
						}
					}
			});	
		jck3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					for(int a = 0; a<25; a++) {
						if(color[a]==3)
							buttons[a].setVisible(true);
						}
					}
				else
					for(int b = 0; b<25; b++) {
						if(color[b]==3)
							buttons[b].setVisible(false);
						}
					}
			});	
		jck4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==4){
					for(int a = 0; a<25; a++) {
						if(color[a]==1)
							buttons[a].setVisible(true);
						}
					}
				else
					for(int b = 0; b<25; b++) {
						if(color[b]==4)
							buttons[b].setVisible(false);
						}
					}
			});	
	}

   public ImageIcon setImageIcon(int CAI, int i){
      String imageurl;
      Image originimg = null;
      Image changeimg = null;
      ImageIcon changeicon;
      
      if( 0 < CAI && CAI <= 50) {
         imageurl = imageurlGreen;
         color[i]=1;
      }
      else if(50 < CAI && CAI <= 100) {
         imageurl = imageurlBlue;
         color[i]=2;
      }
      else if(100 < CAI && CAI <= 250) {
         imageurl = imageurlYellow;
         color[i]=3;  
      }
      else {
         imageurl = imageurlRed;
         color[i]=4;
      }
      ImageIcon origin = new ImageIcon(imageurl);
      originimg = origin.getImage();
      changeimg = originimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      changeicon = new ImageIcon(changeimg);
      
      return changeicon;
   }
   
   
   public JButton setJButton(Cai cai, Air a, String month, String date, int x, int y, int CAI, int i) {
      
      JButton setbtn = new JButton(setImageIcon(CAI, i));
      setbtn.setBounds(x, y, 40, 40);
      setbtn.setBorderPainted(false);
      setbtn.setContentAreaFilled(false);
      
      setbtn.addActionListener(new ActionListener(){

         public void actionPerformed(ActionEvent arg0) {
            Graph graph = new Graph(cai, a, month, date);
         }
         
         
      });
      
      return setbtn; 
   }
   
   
   class imagePanel extends JPanel{
      Image image = null;
      public void paintComponent(Graphics g){
         ImageIcon map = new ImageIcon(imageurlMap);
         image = map.getImage();
         super.paintComponent(g);
         g.drawImage(image, 0, 0, 600, 600, this);
      }
   }
}


class SCircle extends JPanel{
   public void paint (Graphics g) {
      g.setColor(Color.blue);
      g.fillOval(0, 10, 10, 10);
      g.setColor(Color.green);
      g.fillOval(0, 35, 10, 10);
      g.setColor(Color.yellow);
      g.fillOval(0, 60, 10, 10);
      g.setColor(Color.red);
      g.fillOval(0, 85, 10, 10);      
      }
   }





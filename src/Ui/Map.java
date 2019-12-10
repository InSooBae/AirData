package Ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import airdata.Cai;
import db.Air;
import db.AirData;



public class Map extends JFrame {
   String imageurlGreen = "resource/greencircle.png";
   String imageurlBlue = "resource/bluecircle.png";
   String imageurlYellow = "resource/yellowcircle.png";
   String imageurlRed = "resource/redcircle.png";
   String imageurlMap = "resource/map.png";
<<<<<<< HEAD
   String imageurlNoData = "resource/noData.png";
   JButton[] buttons = new JButton[25];
   int[] color = new int[25];
   int i = 0;
=======
   JButton[] buttons = new JButton[25];
   int[] color = new int[25];
   int i = 0;
	
>>>>>>> refs/remotes/origin/master
   
   Map(List<Air> airList, String month, String date){
      
      double no2p, o3p, cop, so2p; 
      int pm10, pm25, x, y;
      
      setTitle("지도");
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
      c.setBackground(new Color(0xFFE4E1));
      
      JPanel top = new JPanel();
      JPanel west = new JPanel();
      JPanel south = new JPanel();
      JPanel southEast = new JPanel();
      JPanel check = new JPanel();
      JPanel smallSouth = new JPanel();
      top.setBackground(new Color(0xFFE4E1));
      west.setBackground(new Color(0xFFE4E1));
      south.setBackground(new Color(0xFFE4E1));
      southEast.setBackground(new Color(0xFFE4E1));
      check.setBackground(new Color(0xFFE4E1));
      smallSouth.setBackground(new Color(0xFFE4E1));
      SCircle circlePanel = new SCircle();
      imagePanel imagepanel = new imagePanel();
      
      top.setLayout(new BorderLayout());
      west.setLayout(new BorderLayout());
      south.setLayout(new BorderLayout());
      southEast.setLayout(new BorderLayout());
      check.setLayout(new GridLayout(5,2));
      smallSouth.setLayout(new BorderLayout());
      imagepanel.setLayout(null);
      JLabel jl = new JLabel("2018년 " + month + "월  " + date + "일");
      jl.setFont(jl.getFont().deriveFont(30.0f));

      //JButton checkbtn = new JButton("CAI�� ��� Ȯ��");
      JCheckBox jck1 = new JCheckBox("좋음(0~50)", true);
       JCheckBox jck2 = new JCheckBox("보통(51~100)", true);
       JCheckBox jck3 = new JCheckBox("나쁨(101~250)", true);
       JCheckBox jck4 = new JCheckBox("매우나쁨(250~)", true);
       JCheckBox jck5 = new JCheckBox("정보없음", true);
       JButton getWebData = new JButton("정보 받아오기");
       
      
<<<<<<< HEAD
      if(!airList.isEmpty()) {
	      for (Air a : airList) {
	         Cai cai = new Cai(a);
	         x = a.getX();
	         y = a.getY();
	         buttons[i] = setJButton(airList, cai, a, month, date, x, y, cai.getCAI(), i);
	         imagepanel.add(buttons[i]);
	         i++;
	      }
=======
      for (Air a : airList) {
         
         Cai cai = new Cai(a);
         
         x = a.getX();
         y = a.getY();
         buttons[i] = setJButton(cai, a, month, date, x, y, cai.getCAI(), i);
         imagepanel.add(buttons[i]);
		 i++;  
>>>>>>> refs/remotes/origin/master
      }
      
      setSize(800, 700);
      setVisible(true);
      check.add(jck1);
      check.add(jck2);
      check.add(jck3);
      check.add(jck4);
      check.add(jck5);
      top.add(jl, BorderLayout.EAST);
      southEast.add(getWebData);
      south.add(southEast, BorderLayout.EAST);
      //smallnorth.add(checkbtn, BorderLayout.NORTH);
      smallSouth.add(check, BorderLayout.WEST);
      smallSouth.add(circlePanel, BorderLayout.EAST);
      west.add(smallSouth, BorderLayout.SOUTH);
      add(imagepanel, BorderLayout.CENTER);
      add(top, BorderLayout.NORTH);
      add(west, BorderLayout.WEST);
      add(south, BorderLayout.SOUTH);
<<<<<<< HEAD
      if(airList.isEmpty()) {
    	  JOptionPane.showMessageDialog(this, "입력할 정보가 없습니다. 우측 하단의 정보를 받아보세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
      }
       
      getWebData.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent arg0) {
        	 AirData web = new AirData();
        	 web.insertWebData(month, date);
        	 List<Air> temp=web.getSelectYMData("2018"+month+date);
        	 int x,y;
        	 int i=0;
            for (Air a : temp) {
                Cai cai1 = new Cai(a);
                x = a.getX();
                y = a.getY();
                buttons[i] = setJButton(temp, cai1, a, month, date, x, y, cai1.getCAI(), i);
                imagepanel.add(buttons[i]);
                i++;
               
             }
           
           repaint();
         }
         
      });
     
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
            if(e.getStateChange()==1){
               for(int a = 0; a<25; a++) {
                  if(color[a]==4)
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
      jck5.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange()==1){
               for(int a = 0; a<25; a++) {
                  if(color[a]==5)
                     buttons[a].setVisible(true);
                  }
               }
            else
               for(int b = 0; b<25; b++) {
                  if(color[b]==5)
                     buttons[b].setVisible(false);
                  }
               }
         });   
   }
   
=======
      
      
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

>>>>>>> refs/remotes/origin/master
   public ImageIcon setImageIcon(int CAI, int i){
      String imageurl;
      Image originimg = null;
      Image changeimg = null;
      ImageIcon changeicon;
      
      if( 0 < CAI && CAI <= 50) {
<<<<<<< HEAD
=======
         imageurl = imageurlGreen;
         color[i]=1;
      }
      else if(50 < CAI && CAI <= 100) {
>>>>>>> refs/remotes/origin/master
         imageurl = imageurlBlue;
<<<<<<< HEAD
         color[i]=1;
      }
      else if(50 < CAI && CAI <= 100) {
         imageurl = imageurlGreen;
=======
>>>>>>> refs/remotes/origin/master
         color[i]=2;
      }
      else if(100 < CAI && CAI <= 250) {
         imageurl = imageurlYellow;
<<<<<<< HEAD
         color[i]=3;
      }
      else if(250<CAI) {
=======
         color[i]=3;  
      }
      else {
>>>>>>> refs/remotes/origin/master
         imageurl = imageurlRed;
         color[i]=4;
      }
<<<<<<< HEAD
      else {
         imageurl = imageurlNoData;
         color[i]=5;
      }
      
=======
>>>>>>> refs/remotes/origin/master
      ImageIcon origin = new ImageIcon(imageurl);
      originimg = origin.getImage();
      changeimg = originimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      changeicon = new ImageIcon(changeimg);
      
      return changeicon;
   }
   
   
<<<<<<< HEAD
   public JButton setJButton(List<Air> airList, Cai cai, Air a, String month, String date, int x, int y, int CAI, int i) {
=======
   public JButton setJButton(Cai cai, Air a, String month, String date, int x, int y, int CAI, int i) {
>>>>>>> refs/remotes/origin/master
      
      JButton setbtn = new JButton(setImageIcon(CAI, i));
      setbtn.setBounds(x, y, 40, 40);
      setbtn.setBorderPainted(false);
      setbtn.setContentAreaFilled(false);
      setbtn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent arg0) {
            Graph graph = new Graph(airList, cai, a, month, date);
            repaint();
         }
         
      });
      
      return setbtn; 
   }
   
   class SCircle extends JPanel{
      public void paint (Graphics g) {
         Image X =null;
         ImageIcon XIcon = new ImageIcon(imageurlNoData);
         X = XIcon.getImage();
         g.setColor(Color.BLUE);
         g.fillOval(0, 7, 10, 10);
         g.setColor(Color.green);
         g.fillOval(0, 33, 10, 10);
         g.setColor(Color.yellow);
         g.fillOval(0, 57, 10, 10);
         g.setColor(Color.red);
         g.fillOval(0, 81, 10, 10);
         g.drawImage(X, 0, 103, 10, 10, this);
         }
      }
   
   class imagePanel extends JPanel{
      Image image = null;
      @Override
  	public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;
          ImageIcon map = new ImageIcon(imageurlMap);
          image = map.getImage();
 
          
          g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
          int w = getWidth();
          int h = getHeight();
          Color color1 = new Color(0xFFE4E1);
          Color color2 = Color.white;
          GradientPaint gp = new GradientPaint(10, 10, color1, 0, h, color1);
          
          g2d.setPaint(gp);
          g2d.fillRect(0, 0, w, h);
          g.drawImage(image, 0, 0, 600, 600, this);
      }


   }
}


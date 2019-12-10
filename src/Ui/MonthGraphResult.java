package Ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import db.Air;
import db.AirData;



public class MonthGraphResult extends JFrame {
   MonthGraphResult(int num, Air a){
         JFrame frame = new JFrame("월별 평균 그래프");
           frame.setLocation(900,350);
           frame.setPreferredSize(new Dimension(900,400));
           Container contentPane = frame.getContentPane();
           
           ADrawingPanel drawingPanel = new ADrawingPanel();
           contentPane.add(drawingPanel, BorderLayout.CENTER);
           //그래프를 그릴 패널
           
           JPanel controlPanel = new JPanel();
           JPanel north = new JPanel();
           controlPanel.setBackground(new Color(0xFFE4E1));
           north.setBackground(new Color(0xFFE4E1));
           north.setLayout(new BorderLayout());
           JLabel jl = new JLabel(num + "월의 대기 평균 그래프");
          
           north.add(jl, BorderLayout.EAST);
         
           contentPane.add(north, BorderLayout.NORTH);
           contentPane.add(controlPanel, BorderLayout.SOUTH);
           //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          
           frame.pack();
           frame.setVisible(true);
       }

   }

   //그래피를 그리는 패널 클래스
class ADrawingPanel extends JPanel
{
   int no2p, o3p, cop, so2p, pm10, pm25;
   int avrNo2p, avrO3p, avrCop, avrSo2p, avrPm10, avrPm25;
    
    public void paint(Graphics g){
       AirData airList = new AirData();
        Air air = airList.getYearAirAvg();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(0xFF6347);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color1);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        
        avrNo2p = (int)Math.round(air.getNo2p()*1000);
        avrO3p = (int)Math.round(air.getO3p()*1000);
        avrCop = (int)Math.round(air.getCop()*100);
        avrSo2p = (int)Math.round(air.getSo2p()*10000);
        avrPm10 = (int)Math.round(air.getPm10());
        avrPm25 = (int)Math.round(air.getPm25());
        
       g.clearRect(0,0,getWidth(),getHeight());
       g.drawLine(50,250,500,250);
       g.drawLine(550,250,850,250);
       for(int cnt = 1 ;cnt<11;cnt++)
       {
           g.drawLine(50, 250-20*cnt, 500,250-20*cnt);
           g.drawLine(550, 250-20*cnt, 850,250-20*cnt);
       }
       g.drawString("pm", 27, 30);
       g.drawString("㎍/㎥", 523, 30);
        
       g.drawLine(50,20,50,250);
       g.drawLine(550, 20, 550, 250);
       g.drawString("no2p",100,270);
       g.drawString("o3p",200,270);
       g.drawString("co2p",300,270);
       g.drawString("so2p",400,270);
       g.drawString("pm10",620,270);
       g.drawString("pm25",750,270);
       
       g.setColor(Color.RED);//입력받은 막대
       g.fillRect(110,250-avrNo2p*2,10,avrNo2p*2);
       g.fillRect(210,250-avrO3p*2,10,avrO3p*2);
       g.fillRect(310,250-avrCop*2,10,avrCop*2);
       g.fillRect(410,250-avrSo2p*2,10,avrSo2p*2);
       g.fillRect(630,250-avrPm10*2,10,avrPm10*2);
       g.fillRect(760,250-avrPm25*2,10,avrPm25*2);
    }


  
       void setScores(double no2p, double o3p, double cop, double so2p, int pm10, int pm25)
       {
          this.no2p = (int)Math.round(no2p*1000);
          this.o3p = (int)Math.round(o3p*1000);
          this.cop = (int)Math.round(cop*100);
          this.so2p = (int)Math.round(so2p*10000);
          this.pm10 = pm10;
          this.pm25 = pm25;
       }
   }

   //버튼 눌렀을때 동작하는 리스너
   class ADrawActionListener implements ActionListener
   {
      double no2p, o3p, cop, so2p;
      int pm10, pm25;//사라질 데이터   
       ADrawingPanel drawingPanel;
       ADrawActionListener(double no2p, double o3p, double cop, double so2p, int pm10, int pm25, ADrawingPanel drawingPanel)
       {
           this.no2p = no2p;
           this.o3p = o3p;
           this.cop = cop;
           this.so2p = so2p;
           this.pm10 = pm10;
           this.pm25 = pm25;
           this.drawingPanel = drawingPanel;
       }
       public void actionPerformed(ActionEvent e)
       {
            drawingPanel.setScores(no2p, o3p, cop, so2p, pm10, pm25);
            drawingPanel.repaint();
      }
   }
      
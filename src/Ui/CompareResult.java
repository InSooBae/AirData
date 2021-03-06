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



public class CompareResult
{
   CompareResult(int year, int month, int date, String local, double no2p, double o3p, double cop, double so2p, int pm10, int pm25){// 생성자 매개변수 또한 삭제될 임의의 변수들(month, date를 체외하고 다 삭제예정)
      
        JFrame frame = new JFrame("비교 그래프");
        frame.setLocation(690,20);
        frame.setPreferredSize(new Dimension(800,400));
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(new Color(0xFFE4E1));
        
        AnotherDrawingPanel drawingPanel = new AnotherDrawingPanel();
        
        contentPane.add(drawingPanel, BorderLayout.CENTER);
        //그래프를 그릴 패널
        
        JPanel controlPanel = new JPanel();
        JPanel north = new JPanel();
        north.setLayout(new BorderLayout());
        controlPanel.setBackground(new Color(0xFFE4E1));
        north.setBackground(new Color(0xFFE4E1));
        JLabel jl = new JLabel(local + "의" + year + "년 " + month + "월 " + date + "일 대기지수 비교");
        JButton button = new JButton("비교하기");
        north.add(jl, BorderLayout.EAST);
        controlPanel.add(new JLabel("파란색 : 평균, 빨간색 : 입력 값"));
        controlPanel.add(button);
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new AnotherDrawActionListener(no2p, o3p, cop, so2p, pm10, pm25, drawingPanel));
       
        frame.pack();
        frame.setVisible(true);
    }

}

//그래피를 그리는 패널 클래스

class AnotherDrawingPanel extends JPanel
{
   int no2p, o3p, cop, so2p, pm10, pm25;
   int avrNo2p, avrO3p, avrCop, avrSo2p, avrPm10, avrPm25;
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
       int w = getWidth();
       int h = getHeight();
       Color color1 = new Color(0xFFE4E1);
       Color color2 = Color.white;
       GradientPaint gp = new GradientPaint(0, 0, color2, 0, h, color1);
       g2d.setPaint(gp);
       g2d.fillRect(0, 0, w, h);
   }
   
    public void paint(Graphics g){
       AirData airList = new AirData();
        Air air = airList.getYearAirAvg();      
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(0xFF6347);
        Color color2 = Color.white;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color1);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        
        avrNo2p = (int)Math.round(air.getNo2p()*1000);
        avrO3p = (int)Math.round(air.getO3p()*1000);
        avrCop = (int)Math.round(air.getCop()*100);
        avrSo2p = (int)Math.round(air.getSo2p()*10000);
        avrPm10 = (int)Math.round(air.getPm10());
        avrPm25 = (int)Math.round(air.getPm25());
       
        g2d.clearRect(0,0,getWidth(),getHeight());
        g2d.drawLine(50,250,700,250);
       for(int cnt = 1 ;cnt<11;cnt++)
       {
    	   g2d.drawLine(50, 250-20*cnt, 700,250-20*cnt);
       }
       g2d.drawLine(50,20,50,250);
       g2d.drawString("no2p",100,270);
       g2d.drawString("o3p",200,270);
       g2d.drawString("co2p",300,270);
       g2d.drawString("so2p",400,270);
       g2d.drawString("pm10",500,270);
       g2d.drawString("pm25",600,270);
       
       g2d.setColor(Color.blue);//평균 막대       
       g2d.fillRect(100,250-avrNo2p*2,10,avrNo2p*2);
       g2d.fillRect(200,250-avrO3p*2,10,avrO3p*2);
       g2d.fillRect(300,250-avrCop*2,10,avrCop*2);
       g2d.fillRect(400,250-avrSo2p*2,10,avrSo2p*2);
       g2d.fillRect(500,250-avrPm10*2,10,avrPm10*2);  
       g2d.fillRect(600,250-avrPm25*2,10,avrPm25*2);
       
       g2d.setColor(Color.RED);//입력받은 막대
       if (no2p>0)
    	   g2d.fillRect(120,250-no2p*2,10,no2p*2);
       if(o3p>0)
    	   g2d.fillRect(220,250-o3p*2,10,o3p*2);
       if(cop>0)
    	   g2d.fillRect(320,250-cop*2,10,cop*2);
       if (so2p>0)
    	   g2d.fillRect(420,250-so2p*2,10,so2p*2);
       if(pm10>0)
    	   g2d.fillRect(520,250-pm10*2,10,pm10*2);
       if(pm25>0)
    	   g2d.fillRect(620,250-pm25*2,10,pm25*2);

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
class AnotherDrawActionListener implements ActionListener
{
   double no2p, o3p, cop, so2p;
   int pm10, pm25;//사라질 데이터   
    AnotherDrawingPanel drawingPanel;
    AnotherDrawActionListener(double no2p, double o3p, double cop, double so2p, int pm10, int pm25, AnotherDrawingPanel drawingPanel)
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
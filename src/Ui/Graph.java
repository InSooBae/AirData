package Ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import airdata.Cai;
import db.Air;

public class Graph
{
   Graph(Cai cai, Air air, String month, String date){// 생성자 매개변수 또한 삭제될 임의의 변수들(month, date를 체외하고 다 삭제예정)
      
        JFrame frame = new JFrame("대기 그래프");
        frame.setLocation(500,200);
        frame.setPreferredSize(new Dimension(800,400));
        Container contentPane = frame.getContentPane();
        
        DrawingPanel drawingPanel = new DrawingPanel();
        contentPane.add(drawingPanel, BorderLayout.CENTER);
        //그래프를 그릴 패널
        
        JPanel controlPanel = new JPanel();
        JPanel north = new JPanel();
        north.setLayout(new BorderLayout());
        JLabel jl = new JLabel("2018년 " + month + "월 " + date + "일 대기지수");
        JButton button = new JButton("그래프 그리기");
        JButton tab = new JButton("표 보이기");
        north.add(jl, BorderLayout.EAST);
        controlPanel.add(new JLabel("대기지수를 그래프로 표현"));
        controlPanel.add(button);
        controlPanel.add(tab);
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new DrawActionListener(cai.getNO2Cai(), cai.getO3Cai(), cai.getCOCai(), cai.getSO2Cai(), cai.getPM10Cai(), cai.getPM25Cai(), drawingPanel));
        tab.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Table table = new Table(air.getNo2p(), air.getO3p(), air.getCop(), air.getSo2p(), air.getPm10(), air.getPm25());
         }
        });
        //"그래프 그리기" 버튼을 눌렀을때 작동 할 리스터등록
        frame.pack();
        frame.setVisible(true);
    }
}

//그래피를 그리는 패널 클래스

class DrawingPanel extends JPanel
{
    int no2p, o3p, cop, so2p, pm10, pm25;//사라질 데이터
    public void paint(Graphics g){
       g.clearRect(0,0,getWidth(),getHeight());
       g.drawLine(50,250,700,250);
       for(int cnt = 1 ;cnt<11;cnt++)
       {
           g.drawString(cnt *10 +"",25,255-20*cnt);
           g.drawLine(50, 250-20*cnt, 700,250-20*cnt);
       }
       g.drawLine(50,20,50,250);
       g.drawString("no2p",100,270);
       g.drawString("o3p",200,270);
       g.drawString("co2p",300,270);
       g.drawString("so2p",400,270);
       g.drawString("pm10",500,270);
       g.drawString("pm25",600,270);
       g.setColor(Color.RED);
       if (no2p>0)
          g.fillRect(110,250-no2p*2,10,no2p*2);
       if(o3p>0)
          g.fillRect(210,250-o3p*2,10,o3p*2);
       if(cop>0)
          g.fillRect(310,250-cop*2,10,cop*2);
       if (so2p>0)
          g.fillRect(410,250-so2p*2,10,so2p*2);
       if(pm10>0)
          g.fillRect(510,250-pm10*2,10,pm10*2);
       if(pm25>0)
          g.fillRect(610,250-pm25*2,10,pm25*2);
    }
    void setScores(int no2p, int o3p, int cop, int so2p, int pm10, int pm25)
    {
       this.no2p = no2p;
       this.o3p = o3p;
       this.cop = cop;
       this.so2p = so2p;
       this.pm10 = pm10;
       this.pm25 = pm25;
    }
}

//버튼 눌렀을때 동작하는 리스너
class DrawActionListener implements ActionListener
{
   int no2p, o3p, cop, so2p, pm10, pm25;//사라질 데이터   
    DrawingPanel drawingPanel;
    DrawActionListener(int no2p, int o3p, int cop, int so2p, int pm10, int pm25, DrawingPanel drawingPanel)
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
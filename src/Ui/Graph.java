package Ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import airdata.Cai;
import db.AirData;
import db.Air;

public class Graph
{
	Graph(List<Air> airList, Cai cai, Air air, String month, String date){
		  
		  String airNo2p = "no2p";
		  String airO3p = "o3p";
		  String airCop = "cop";
		  String airSo2p = "so2p";
		  String airPm10 = "pm10";
		  String airPm25 = "pm25";
		  System.out.println(air.getNo2p()+" "+air.getO3p()+" "+air.getCop()+" "+air.getSo2p()+" "+ air.getPm10()+" "+air.getPm25());
		  JFrame frame = new JFrame("대기 그래프");
		  frame.setLocation(300,200);
		  frame.setPreferredSize(new Dimension(1500,400));
		  Container contentPane = frame.getContentPane();
		  
		  DrawingPanel drawingPanel = new DrawingPanel(airList, air.getLoc_name());
		  contentPane.add(drawingPanel, BorderLayout.CENTER);
		  
		  JPanel controlPanel = new JPanel();
		  JPanel north = new JPanel();
		  north.setLayout(new BorderLayout());
		  JLabel jl = new JLabel("2018년 " + month + "월 " + date + "일 대기지수");
		  
		  JButton no2pButton = new JButton("NO2p 그래프 그리기");
		  JButton o3pButton = new JButton("O3p 그래프 그리기");
		  JButton copButton = new JButton("COp 그래프 그리기");
		  JButton so2pButton = new JButton("SO2p 그래프 그리기");
		  JButton pm10Button = new JButton("PM10 그래프 그리기");
		  JButton pm25Button = new JButton("PM25 그래프 그리기");
		  JButton tab = new JButton("표 보이기");
		  north.add(jl, BorderLayout.EAST);
		  controlPanel.add(no2pButton);
		  controlPanel.add(o3pButton);
		  controlPanel.add(copButton);
		  controlPanel.add(so2pButton);
		  controlPanel.add(pm10Button);
		  controlPanel.add(pm25Button);
		  controlPanel.add(tab);
		  contentPane.add(north, BorderLayout.NORTH);
		  contentPane.add(controlPanel, BorderLayout.SOUTH);
		  //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  //정수화 시킨 각 정보들을 넘겨준다
		  no2pButton.addActionListener(new DrawActionListener(drawingPanel, airNo2p));
		  o3pButton.addActionListener(new DrawActionListener(drawingPanel, airO3p));
		  copButton.addActionListener(new DrawActionListener(drawingPanel, airCop));
		  so2pButton.addActionListener(new DrawActionListener(drawingPanel, airSo2p));
		  pm10Button.addActionListener(new DrawActionListener(drawingPanel, airPm10));
		  pm25Button.addActionListener(new DrawActionListener(drawingPanel, airPm25));
		  tab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table table = new Table(air.getNo2p(), air.getO3p(), air.getCop(), air.getSo2p(), air.getPm10(), air.getPm25(), air);
			}
		  });
		 
		  frame.pack();
		  frame.setVisible(true);
	 }
}

class DrawingPanel extends JPanel
{
	List<Air> airList;
	String local;
	DrawingPanel(List<Air> airList, String local){
		this.airList = airList;
		this.local = local;
	}
	 String[] loc_name = new String[25]; 
	 double[] airDoubleValue = new double[25];
	 int[] airIntValue = new int[25];
	 int j = 0, x = 100;
	 public void paint(Graphics g){
		 g.clearRect(0,0,getWidth(),getHeight());
		 g.drawLine(50,250,1400,250);
		 g.drawString("pm", 27, 30);
		 for(int cnt = 1 ;cnt<11;cnt++)//pm단위 그래프 그리기
		 {
			  g.drawLine(50, 250-20*cnt, 1400,250-20*cnt);
		 }
		 
		 g.drawLine(50,20,50,250);
		 for(Air a : airList) {
			 loc_name[j] = a.getLoc_name();
			 if(j>0){
				 if(loc_name[j-1].length()==4) {
					 x+=60;
					 g.setColor(Color.blue);
					 g.drawString(loc_name[j], x, 270);
					 if(a.getLoc_name()==local)
						 g.setColor(Color.red);
					 g.fillRect(x+10,250-airIntValue[j]*2,10,airIntValue[j]*2);
				 }
				 else { 
					 x+=50;
					 g.setColor(Color.blue);
					 g.drawString(loc_name[j], x, 270);
					 if(a.getLoc_name()==local)
						 g.setColor(Color.red);
					 g.fillRect(x+10,250-airIntValue[j]*2,10,airIntValue[j]*2);
				 }
			 }
			 else{//x=0이면
				 g.setColor(Color.blue);
				 g.drawString(loc_name[j], x, 270);
				 if(a.getLoc_name()==local)
					 g.setColor(Color.red);
				 g.fillRect(x+10,250-airIntValue[j]*2,10,airIntValue[j]*2);
			 }
			 j++;
			 if(j>24){//다시 repaint() 할 때를 위해 초기화
				 x=100;
				 j=0;
			 }
		 }
	 }
	
	 void setScores(String airKinds)
	 {
		 int i = 0;
		if(airKinds=="no2p") {
			for (Air a : airList) {
				airDoubleValue[i] = a.getNo2p();
				if(0.1<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*50);
				}
				else if(0.1>airDoubleValue[i] && 0.01<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*500);
				}
				else if(0.01>airDoubleValue[i] && 0.001<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*5000);
				}
				i++;
			}
		}
		else if(airKinds=="o3p") {
			for (Air a : airList) {
				airDoubleValue[i] = a.getO3p();
				if(0.1<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*50);
				}
				else if(0.1>airDoubleValue[i] && 0.01<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*500);
				}
				else if(0.01>airDoubleValue[i] && 0.001<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*5000);
				}
				i++;
			}
		}
		else if(airKinds=="cop") {
			for (Air a : airList) {
				airDoubleValue[i] = a.getCop();
				if(0.1<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*50);
				}
				else if(0.1>airDoubleValue[i] && 0.01<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*500);
				}
				else if(0.01>airDoubleValue[i] && 0.001<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*5000);
				}
				i++;
			}
		}
		else if(airKinds=="so2p") {
			for (Air a : airList) {
				airDoubleValue[i] = a.getSo2p();
				if(0.1<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*50);
				}
				else if(0.1>airDoubleValue[i] && 0.01<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*500);
				}
				else if(0.01>airDoubleValue[i] && 0.001<airDoubleValue[i]) {
					airIntValue[i] = (int)Math.round(airDoubleValue[i]*5000);
				}
				i++;
			}
		}
		else if(airKinds=="pm10") {
			for (Air a : airList) {
				airIntValue[i] = Math.round(a.getPm10()/2);
				i++;
			}
		}
		else {
			for (Air a : airList) {
				airIntValue[i] = Math.round(a.getPm25()/2);
				i++;
			}
		}
	 }
}

class DrawActionListener implements ActionListener
{
	 String airKinds;
	 DrawingPanel drawingPanel;

	 DrawActionListener(DrawingPanel drawingPanel, String airKinds)
	 {
		  this.airKinds = airKinds;
		  this.drawingPanel = drawingPanel;
	 }
	 public void actionPerformed(ActionEvent e)
	 {
		   drawingPanel.setScores(airKinds);
		   drawingPanel.repaint();
	}
}

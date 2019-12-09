package Ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Air;

public class Table extends JFrame {
	double no2p, o3p, cop, so2p;
	int pm10, pm25;
	String Sno2p, So3p, Scop, Sso2p, Spm10, Spm25;
	Table(double no2p, double o3p, double cop, double so2p, int pm10, int pm25, Air air){
		 this.no2p = no2p;
		 this.o3p = o3p;
		 this.cop = cop;
		 this.so2p = so2p;
		 this.pm10 = pm10;
		 this.pm25 = pm25; 
		 Sno2p = Double.toString(no2p);
		 So3p = Double.toString(o3p);
		 Scop = Double.toString(cop);
		 Sso2p = Double.toString(so2p);
		 Spm10 = Integer.toString(pm10);
		 Spm25 = Integer.toString(pm25);
		 setTitle(air.getLoc_name() + "의 대기지수 수치 표");
		 Container c = getContentPane();
		 c.setLayout(new BorderLayout());
		 setLocation(700,400);
			
		 String header[] = {"측정대기종류", "이산화질소", "오존", "일산화탄소", "이황산가스", "미세먼지", "초미세먼지"};
		 String contents[][] = {
				{"ppm, ㎍/㎥\"", Sno2p, So3p, Scop, Sso2p, Spm10, Spm25}
		 };
		
		 JTable tab = new JTable(contents, header);
		 JScrollPane scroll = new JScrollPane(tab);
		 add(scroll);
		 setSize(530, 90);
		 setVisible(true);
		 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

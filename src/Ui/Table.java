package Ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table extends JFrame {
   double no2p, o3p, co2p, so2p;
   int pm10, pm25;//삭제될 임의의 변수들
   String Sno2p, So3p, Sco2p, Sso2p, Spm10, Spm25;//삭제될 임의의 변수들
   Table(double no2p, double o3p, double co2p, double so2p, int pm10, int pm25){// 생성자 매개변수 또한 삭제될 임의의 변수들
       this.no2p = no2p;
       this.o3p = o3p;
       this.co2p = co2p;
       this.so2p = so2p;
       this.pm10 = pm10;
       this.pm25 = pm25; 
       Sno2p = Double.toString(no2p);
       So3p = Double.toString(o3p);
       Sco2p = Double.toString(co2p);
       Sso2p = Double.toString(so2p);
       Spm10 = Integer.toString(pm10);
       Spm25 = Integer.toString(pm25);
       setTitle("대기지수 수치 표");
       Container c = getContentPane();
       c.setLayout(new BorderLayout());
         
       String header[] = {"측정대기종류", "이산화탄소", "오존", "이산화탄소", "이황산가스", "미세먼지", "초미세먼지"};
       String contents[][] = {
            {"ppm, ㎍/㎥", Sno2p, So3p, Sco2p, Sso2p, Spm10, Spm25}
       };
      
       JTable tab = new JTable(contents, header);
       JScrollPane scroll = new JScrollPane(tab);
       add(scroll);
       setSize(500, 90);
       setVisible(true);
       //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }

}
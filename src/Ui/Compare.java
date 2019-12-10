package Ui;
<<<<<<< HEAD
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Compare extends JFrame{
   int date, month, year;
   String local;
   Compare(){
      setTitle("대기지수 비교");
      Container c = getContentPane();
      c.setLayout(new FlowLayout());
      JPanel jp1 = new JPanel();
      JPanel jp2 = new JPanel();
      c.setBackground(new Color(0xFFE4E1));
      jp1.setBackground(new Color(0xFFE4E1));
      jp2.setBackground(new Color(0xFFE4E1));
      jp1.setLayout(new GridLayout(10,2));
      jp2.setLayout(new GridLayout(1, 2));
      
      JLabel inyeat = new JLabel("년도를 입력하세요.");
      JLabel inmonth = new JLabel("월을 입력하세요.");
      JLabel indate = new JLabel("날짜를 입력하세요.");
      JLabel inlocal = new JLabel("지역을 입력하세요.");
      JLabel inno2p = new JLabel("no2p의 값을 입력하세요.");
      JLabel ino3p = new JLabel("o3p의 값을 입력하세요.");
      JLabel incop = new JLabel("cop의 값을 입력하세요.");
      JLabel inso2p = new JLabel("so2p의 값을 입력하세요.");
      JLabel inpm10 = new JLabel("pm10의 값을 입력하세요.");
      JLabel inpm25 = new JLabel("pm25의 값을 입력하세요.");
      JTextField inputYear = new JTextField();
      JTextField inputMonth = new JTextField();
      JTextField inputDate = new JTextField();
      JTextField inputLocal = new JTextField();
      JTextField inputNo2p = new JTextField();
      JTextField inputO3p = new JTextField();
      JTextField inputCop = new JTextField();
      JTextField inputSo2p = new JTextField();
      JTextField inputPm10 = new JTextField();
      JTextField inputPm25 = new JTextField();
      JButton comp = new JButton("입력한 값과  2018년 대기지수 비교하기!");
      
      jp1.add(inyeat);
      jp1.add(inputYear);
      jp1.add(inmonth);
      jp1.add(inputMonth);
      jp1.add(indate);
      jp1.add(inputDate);
      jp1.add(inlocal);
      jp1.add(inputLocal);
      jp1.add(inno2p);
      jp1.add(inputNo2p);
      jp1.add(ino3p);
      jp1.add(inputO3p);
      jp1.add(incop);
      jp1.add(inputCop);
      jp1.add(inso2p);
      jp1.add(inputSo2p);
      jp1.add(inpm10);
      jp1.add(inputPm10);
      jp1.add(inpm25);
      jp1.add(inputPm25);
      jp2.add(comp);
      add(jp1);
      add(jp2);
      
      comp.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            double no2p, o3p, cop, so2p;
            int pm10, pm25;
            try {
            no2p = Double.valueOf(inputNo2p.getText());
            o3p = Double.valueOf(inputO3p.getText());
            cop = Double.valueOf(inputCop.getText());
            so2p = Double.valueOf(inputSo2p.getText());
            pm10 = Integer.valueOf(inputPm10.getText());
            pm25 = Integer.valueOf(inputPm25.getText());
            month = Integer.valueOf(inputMonth.getText());
            date = Integer.valueOf(inputDate.getText());
            year = Integer.valueOf(inputYear.getText());
            local = inputLocal.getText();
            CompareResult result = new CompareResult(year, month, date, local, no2p, o3p, cop, so2p, pm10, pm25);
            }catch(NumberFormatException e2){
            	e2.printStackTrace();
            	JOptionPane.showMessageDialog(comp,"입력하신 정보가 부족합니다. 다시 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
            } 
           
         }
      });
   
      setLocation(300,100);
      setSize(400, 320);
      setVisible(true);
   
   }
}
=======
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import airdata.Cai;
import db.Air;

public class Compare extends JFrame{
	
	Compare(){
		setTitle("대기지수 비교");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		jp1.setLayout(new GridLayout(6,2));
		jp2.setLayout(new GridLayout(1, 2));
		
		JLabel inno2p = new JLabel("no2p의 값을 입력하세요.");
		JLabel ino3p = new JLabel("o3p의 값을 입력하세요.");
		JLabel incop = new JLabel("cop의 값을 입력하세요.");
		JLabel inso2p = new JLabel("so2p의 값을 입력하세요.");
		JLabel inpm10 = new JLabel("pm10의 값을 입력하세요.");
		JLabel inpm25 = new JLabel("pm25의 값을 입력하세요.");
		JTextField inputNo2p = new JTextField();
		JTextField inputO3p = new JTextField();
		JTextField inputCop = new JTextField();
		JTextField inputSo2p = new JTextField();
		JTextField inputPm10 = new JTextField();
		JTextField inputPm25 = new JTextField();
		JButton comp = new JButton("입력한 값과  2018년 대기지수 비교하기!");
		
		jp1.add(inno2p);
		jp1.add(inputNo2p);
		jp1.add(ino3p);
		jp1.add(inputO3p);
		jp1.add(incop);
		jp1.add(inputCop);
		jp1.add(inso2p);
		jp1.add(inputSo2p);
		jp1.add(inpm10);
		jp1.add(inputPm10);
		jp1.add(inpm25);
		jp1.add(inputPm25);
		jp2.add(comp);
		add(jp1);
		add(jp2);
		
		comp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				double no2p, o3p, cop, so2p, pm10, pm25;
				no2p = Double.valueOf(inputNo2p.getText());
				o3p = Double.valueOf(inputO3p.getText());
				cop = Double.valueOf(inputCop.getText());
				so2p = Double.valueOf(inputSo2p.getText());
				pm10 = Double.valueOf(inputPm10.getText());
				pm25 = Double.valueOf(inputPm25.getText());
				
				CompareResult result = new CompareResult(no2p, o3p, cop, so2p, pm10, pm25);
			}
			
		});
		
		
		setLocation(300,100);
		setSize(300, 220);
		setVisible(true);
	
	}
}
>>>>>>> refs/remotes/origin/master

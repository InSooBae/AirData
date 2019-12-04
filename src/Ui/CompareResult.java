package Ui;
import java.awt.BorderLayout;
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

import airdata.Cai;
import db.Air;

public class CompareResult extends JFrame {
	
	double no2pavr = 10;
	double o3pavr = 10;
	double copavr = 10;
	double so2pavr = 10;
	double pm10avr = 10;
	double pm25avr = 10;
	CompareResult(double no2p, double o3p, double cop, double so2p, double pm10, double pm25){
		setTitle("비교 결과");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));
		jp3.setLayout(new GridLayout(6, 1));
		jp4.setLayout(new BorderLayout());
		jp5.setLayout(new BorderLayout());
		
		JLabel no2pC = new JLabel("no2p 비교 결과 :");
		JLabel o3pC = new JLabel("o3p 비교 결과 :");
		JLabel copC = new JLabel("cop 비교 결과  :");
		JLabel so2pC = new JLabel("so2p 비교 결과  :");
		JLabel pm10C = new JLabel("pm10 비교 결과  :");
		JLabel pm25C = new JLabel("pm25 비교 결과 :"); // 
		System.out.println(no2pavr);
		
		jp1.add(no2pC);
		jp2.add(new JLabel("입력한 값 "+ no2p+"와 2018년의 평균 "+ no2pavr));
		if(no2p>no2pavr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(no2p<no2pavr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp1.add(o3pC);
		jp2.add(new JLabel("입력한 값 "+ o3p+"와 2018년의 평균  "+ o3pavr));
		if(o3p>o3pavr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(o3p<o3pavr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp1.add(copC);
		jp2.add(new JLabel("입력한 값"+ cop+"와 2018년의 평균 "+ copavr));
		if(cop>copavr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(cop<copavr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp1.add(so2pC);
		jp2.add(new JLabel("입력한 값 "+ so2p+"와 2018년의 평균 "+ so2pavr));
		if(so2p>so2pavr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(so2p<so2pavr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp1.add(pm10C);
		jp2.add(new JLabel("입력한 값 "+ pm10+"와 2018년의 평균 "+ pm10avr));
		if(pm10>pm10avr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(pm10<pm10avr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp1.add(pm25C);
		jp2.add(new JLabel("입력한 값 "+ pm25+"와 2018년의 평균 "+ pm25avr));
		if(pm25>pm25avr)
			jp3.add(new JLabel("2018년이 더 깨끗합니다."));
		else if(pm25<pm25avr)
			jp3.add(new JLabel("2018년보다 깨끗합니다."));
		else
			jp3.add(new JLabel("2018년과 똑같습니다."));
		
		jp4.add(jp2, BorderLayout.WEST);
		jp5.add(jp4, BorderLayout.WEST);
		add(jp1, BorderLayout.WEST);
		add(jp5, BorderLayout.CENTER);
		add(jp3, BorderLayout.EAST);
		
		setLocation(500,250);
		setSize(700, 170);
		setVisible(true);
	}
}

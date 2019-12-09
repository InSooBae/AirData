package Ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Air;
import db.AirData;

public class MonthGraph extends JFrame {
	
		MonthGraph(){
			AirData airList = new AirData();
			List<Air> air = airList.getMonthAirAvg();
			int i = 0, j = 0;
			Container c = getContentPane();
			c.setLayout(new BorderLayout());
			
			JPanel jp1 = new JPanel();
			JPanel jp2 = new JPanel();
			JPanel jp3 = new JPanel();
			jp1.setLayout(new BorderLayout());
			jp2.setLayout(new GridLayout(12,1));
			jp3.setLayout(new GridLayout(12,1));
			
			for(Air a : air) {
				jp3.add(setButton(i+1, a));
				i++;
			}
			
			for(Air a : air) {
				jp2.add(setTable(j+1, a));
				j++;
			}
			
			jp1.add(jp2, BorderLayout.CENTER);
			jp1.add(jp3, BorderLayout.EAST);
			add(jp1);
			setTitle("월별 그래프 선택");
			setLocation(200, 200);
			setSize(700, 550);
			setVisible(true);
		}


	public JButton setButton(int num, Air a) {
		JButton setbutton = new JButton(num + "월 그래프 보기");
		setbutton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				MonthGraphResult result = new MonthGraphResult(num, a);
			}
			
		});
		return setbutton;
	}
	
	public JScrollPane setTable(int num, Air a) {
		String header[] = {"측정대기종류", "이산화질소", "오존", "일산화탄소", "이황산가스", "미세먼지", "초미세먼지"};
		String contents[][] = {
				{"ppm, ㎍/㎥\"", Double.toString(a.getNo2p()), Double.toString(a.getO3p()), Double.toString(a.getCop()), Double.toString(a.getSo2p()), Double.toString(a.getPm10()), Double.toString(a.getPm25())}
		 };
		JTable settable = new JTable(contents, header);
		JScrollPane scroll = new JScrollPane(settable);

		return scroll;
	}
}

package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
	public Main() {
		setTitle("대기오염도 관리 프로그램");
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel daylabel = new JLabel("지정한 날짜 대기상태 보기");
		daylabel.setBounds(50,20,500,100);
		JButton daybutton = new JButton("날짜선택");
		daybutton.setBounds(300,50,100,40);
		ActionListener daybuttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DateChoice dateselection = new DateChoice();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		daybutton.addActionListener(daybuttonListener);
		
		JLabel comparelabel = new JLabel("새로운 데이터 입력 후 비교하기");
		comparelabel.setBounds(50,70,500,100);
		JButton comparebutton = new JButton("비교하기");
		comparebutton.setBounds(300,100,100,40);
		
		JLabel monthlabel = new JLabel("월별 대기상태 통계 보기");
		monthlabel.setBounds(50,120,500,100);
		JButton monthbutton = new JButton("월별통계");
		monthbutton.setBounds(300,150,100,40);
		
		JLabel emaillabel = new JLabel("이메일에 데이터 보내기");
		emaillabel.setBounds(50,170,500,100);
		JButton emailbutton = new JButton("이메일");
		emailbutton.setBounds(300,200,100,40);
		ActionListener emailbuttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new SendMail();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		};
		emailbutton.addActionListener(emailbuttonListener);

		comparebutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Compare compare = new Compare();
			}
			
		});
		
		p.add(daylabel);
		p.add(daybutton);
		p.add(comparelabel);
		p.add(comparebutton);
		p.add(monthlabel);
		p.add(monthbutton);
		p.add(emaillabel);
		p.add(emailbutton);
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
	}

}

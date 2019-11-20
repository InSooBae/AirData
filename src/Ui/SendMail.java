package Ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mail.Mail;


public class SendMail extends JFrame{
	SendMail(){
		setTitle("메일 전송");
		JPanel panel = new JPanel(new FlowLayout(2,0, 0));
		JPanel p1 = new JPanel();
		
		JPanel p2 = new JPanel();
		JLabel l2 = new JLabel("메일 입력");
		JTextField t1 = new JTextField(10);
		
		JButton sendmail_btn = new JButton("전송");
		
		String testStr = "오늘의 미세먼지 상태 좋음";
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Mail m1 = new Mail(t1.getText(), testStr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		sendmail_btn.addActionListener(l);
		p1.add(sendmail_btn);
		p2.add(l2);
		p2.add(t1);
		panel.add(p2);
		panel.add(p1);
		add(panel, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300,400);
		setVisible(true);
	}
	
	/*public static void main(String[] args) {
		new SendMail();
	}*/

}

package Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mail.Mail;


public class SendMail extends JFrame{
	String[] date1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",  "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	String[] loc_name = {"강남구","강남대로","강동구","강변북로","강북구","강서구","공항대로","관악구","광진구","구로구","금천구","노원구","도봉구","도산대로","동대문구","동작구","동작대로","마포구","서대문구","서초구","성동구","성북구","송파구","신촌로","양천구","영등포구","영등포로","용산구","은평구","정릉로","종로","종로구","중구","중랑구","천호대로","청계천로","한강대로","홍릉로","화랑로"};
	JComboBox combo2;
	public int index_month, index_day, index_loc;
	
	SendMail(){
		setTitle("메일 전송");
		//전체 패널
		setBackground(new Color(0xFFE4E1));
		JPanel panel = new JPanel(new FlowLayout(0,0, 0));
		JComboBox cb_month = new JComboBox(month);
		JComboBox cb_day = new JComboBox(date1);
		JComboBox cb_loc = new JComboBox(loc_name);
		//메일 입력 UI
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setBackground(new Color(0xFFE4E1));
		p2.setBackground(new Color(0xFFE4E1));
		panel.setBackground(new Color(0xFFE4E1));
		JLabel l2 = new JLabel("메일 입력");
		JTextField t1 = new JTextField(10);
		JTextField loc_name = new JTextField(5);

		JButton sendmail_btn = new JButton("전송");
			
		
		String testStr = "오늘의 미세먼지 상태 좋음";
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Mail m1 = new Mail(index_month, index_day, index_loc, t1.getText(), testStr);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
        cb_month.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb =(JComboBox)e.getSource();
                index_month=jcb.getSelectedIndex();
            }
            });
        cb_day.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb =(JComboBox)e.getSource();
                index_day=jcb.getSelectedIndex();
            }
            });
        cb_loc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb =(JComboBox)e.getSource();
                index_loc=jcb.getSelectedIndex();
            }
            });


		//메일 버튼 리스너 달기
		sendmail_btn.addActionListener(l);
		//메일 전송 버튼, 텍스트 붙이기
		p1.add(sendmail_btn);
		p2.add(l2);
		p2.add(t1);
		//전체 패널에 붙이기
		panel.add(cb_month);
		panel.add(cb_day);
		panel.add(cb_loc);
		panel.add(p2);
		panel.add(p1);
		add(panel, BorderLayout.NORTH);
		
		
		setSize(600,60);
		setVisible(true);
	}
	
	/*public static void main(String[] args) {
		new SendMail();
	}*/

}

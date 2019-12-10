package Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame {
   public Main() {
      setTitle("대기오염도 관리 프로그램");
      
      Background p = new Background();
      p.setLayout(null);
      
      JLabel Title = new JLabel("대기오염도 관리 프로그램");
      Title.setFont(new Font("Arial",Font.BOLD,26));
      Title.setHorizontalAlignment(SwingConstants.CENTER);
      Title.setBounds(107,29,280,36);
      JLabel daylabel = new JLabel("지정한 날짜 대기상태 보기");
      daylabel.setBounds(100,60,500,100);
      
      JButton daybutton = new JButton("날짜선택");
      daybutton.setBounds(300,90,100,40);
      daybutton.setOpaque(false);
    
   
      
      JLabel comparelabel = new JLabel("새로운 데이터 입력 후 비교하기");
      comparelabel.setBounds(100,130,500,100);
      JButton comparebutton = new JButton("비교하기");
      comparebutton.setBounds(300,160,100,40);
      JLabel monthlabel = new JLabel("월별 대기상태 통계 보기");
      monthlabel.setBounds(100,200,500,100);
      JButton monthbutton = new JButton("월별통계");
      monthbutton.setBounds(300,230,100,40);
      
      JLabel emaillabel = new JLabel("이메일에 데이터 보내기");
      emaillabel.setBounds(100,270,500,100);
      JButton emailbutton = new JButton("이메일");
      emailbutton.setBounds(300,300,100,40);
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

<<<<<<< HEAD
     
      daybutton.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent e) {
             DateChoice dateChoice = new DateChoice();
          }
          
       });
       
       comparebutton.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent e) {
             Compare compare = new Compare();
          }
          
       });
       
       monthbutton.addActionListener(new ActionListener() {
=======
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
>>>>>>> refs/remotes/origin/master

          public void actionPerformed(ActionEvent e) {
             MonthGraph monthgraph = new MonthGraph();
          }
          
       });
      
      p.add(Title);
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
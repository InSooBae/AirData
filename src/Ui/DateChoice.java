package Ui;
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

import db.Air;
import db.AirData;



public class DateChoice extends JFrame {
   
   String[] date1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",  "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
   String[] date2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",  "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
   String[] date3 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",  "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
   String[] month1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
   JComboBox combo2;
   public int month, date;
   
   DateChoice(){
      setTitle("날짜선택");
      Container c = getContentPane();
      c.setLayout(new GridLayout(3, 1));
      JPanel jp = new JPanel();
      JPanel jp2 = new JPanel();
      JPanel jp3 = new JPanel();
      JLabel mon = new JLabel(" 월");
      JLabel day = new JLabel(" 일");
      jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
      jp2.setLayout(new GridLayout(2, 1));
      jp3.setLayout(new FlowLayout());
      JButton jb = new JButton("OK");
      jb.setPreferredSize(new Dimension(80, 27));
      JComboBox combo = new JComboBox(month1);
      combo2 = new JComboBox(date1);
      JLabel lab = new JLabel("날짜를 입력하세요");
      lab.setHorizontalAlignment (JLabel.CENTER);
      jp.add(combo);
      jp.add(mon);
      jp.add(combo2);
      jp.add(day);
      jp2.add(lab);
      jp3.add(jb);
      jp2.add(jp3);
      add(jp);
      add(jp2);
      
      combo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JComboBox cb1 = (JComboBox)e.getSource();
            month = cb1.getSelectedIndex()+1;
            if(month<8) {
               if(month%2==0)                  
                  if(month==2) {
                     DefaultComboBoxModel model = new DefaultComboBoxModel(date3);
                     combo2.setModel(model);
                  }
                  else {
                     DefaultComboBoxModel model = new DefaultComboBoxModel(date2);
                     combo2.setModel(model);
                  }
            }
            else {
               if(month%2!=0) {
                  DefaultComboBoxModel model = new DefaultComboBoxModel(date2);
                  combo2.setModel(model);
               }         
            }
         }
      });
      
      combo2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JComboBox cd2 = (JComboBox)e.getSource();
            date = cd2.getSelectedIndex()+1;
         }
      });
      
      jb.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AirData airdata = new AirData();
            String SDate = date < 10 ? '0'+Integer.toString(date):Integer.toString(date);
            String SMonth = month < 10 ? '0'+Integer.toString(month):Integer.toString(month);
            String yDate = "2018"+SMonth+SDate;
            System.out.println(yDate);
            
            List<Air> list = airdata.getSelectYMData(yDate);
            
            Map map = new Map(list, SMonth, SDate);
         }
      });
      setSize(250, 250);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


}
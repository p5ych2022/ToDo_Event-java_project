package org.example.View;

import com.alee.laf.WebLookAndFeel;
import org.example.ConstDef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Calendar;

public class Mainview extends JFrame implements ActionListener {
    private JPanel panel1 = new JPanel();
    private JComboBox cbo1 = new JComboBox();
    private JComboBox cbo2 = new JComboBox();
    private JLabel lab1 = new JLabel("年份");
    private JLabel lab2 = new JLabel("月份");

    private Calendar defaultCalendar = Calendar.getInstance();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();

    public void Init(){
        this.setTitle("日历");
        this.setSize(800,650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);


        panel1.setBackground(Color.GRAY);
        panel1.setBounds(0,0,800,100);
        panel1.setLayout(null);
        this.add(panel1);

        lab1.setFont(new Font("SimHei",Font.BOLD,24));
        lab2.setFont(new Font("SimHei",Font.BOLD,24));
        cbo1.setFont(new Font("SimHei",Font.BOLD,24));
        cbo2.setFont(new Font("SimHei",Font.BOLD,24));
        lab1.setBounds(200, 40, 50,30);
        lab2.setBounds(450, 40, 50,30);
        cbo1.setBounds(270,40,100,30);
        cbo2.setBounds(520,40,100,30);


        for(int i = 1970; i <= 2080; i++){
            this.cbo1.addItem(i);
        }
        this.cbo1.setSelectedItem(defaultCalendar.get(Calendar.YEAR));

        for(int i = 1; i <= 12;i++){
            this.cbo2.addItem(i);
        }
        this.cbo2.setSelectedItem(defaultCalendar.get(Calendar.MONTH) + 1);

        panel1.add(lab1);
        panel1.add(cbo1);
        panel1.add(lab2);
        panel1.add(cbo2);
        panel1.repaint();


        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBounds(0,100,800,50);
        panel2.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel2.setLayout(new GridLayout(1,7));
        panel2.setVisible(true);
        this.add(panel2);
        String[] WeekDays = {"日","一", "二", "三", "四", "五", "六"};
        for(String label:WeekDays){
            JLabel jLabel = new JLabel(label);
            jLabel.setHorizontalAlignment(JLabel.CENTER);
            jLabel.setFont(new Font("SimHei",Font.PLAIN,30));
            panel2.add(jLabel);
            panel2.revalidate();
        }


        panel3.setBounds(0, 150,800,500);
/*
        panel3.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
*/
        panel3.setLayout(null);
        this.add(panel3);
        defaultCalendar.set(Calendar.DATE, 1);
        this.Panel3Repaint(defaultCalendar.get(Calendar.YEAR),defaultCalendar.get(Calendar.MONTH) + 1);

    }
    public void Panel3Repaint(int year, int month){
        Calendar c = Calendar.getInstance();
        Calendar cLast = Calendar.getInstance();
        c.set(year,month - 1,1);
        cLast.set(year,month - 2,1);
        int cnt = 1;
        for(int x = 1; x<= 6;x++){
            for(int y = 0; y<=6;y++){
                JButton jButton = new JButton();
                if(x == 1 && y < c.get(Calendar.DAY_OF_WEEK) - 1){
                    int firstDate = y + cLast.getActualMaximum(Calendar.DAY_OF_MONTH) - c.get(Calendar.DAY_OF_WEEK)+2;
                    jButton.setText(String.valueOf(firstDate));
                    jButton.setBackground(Color.gray);
                    jButton.setEnabled(false);
                }
                else if(cnt > c.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    jButton.setText(String.valueOf(cnt - c.getActualMaximum(Calendar.DAY_OF_MONTH)));
                    jButton.setBackground(Color.gray);
                    jButton.setEnabled(false);
                    cnt++;
                }
                else{
                    jButton.setText(cnt + "");
                    jButton.setBackground(Color.orange);
                    cnt++;
                }
                jButton.setFont(new Font("SimHei",Font.BOLD,30));
                jButton.setBounds(y* ConstDef.WIDTH_CONST, (x-1)*ConstDef.HEIGHT_CONST,ConstDef.WIDTH_CONST,ConstDef.HEIGHT_CONST);
                jButton.addActionListener(this);
                panel3.add(jButton);
            }
        }
        panel3.repaint();
    }
    public void changeYearHandler(){
        cbo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    panel3.removeAll();
                    panel3.repaint();
                    Panel3Repaint((int)cbo1.getSelectedItem(),(int)cbo2.getSelectedItem());
                }
            }
        });
    }
    public void changeMonthHandler(){
        cbo2.addItemListener((new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                    panel3.removeAll();
                    panel3.repaint();
                    Panel3Repaint((int)cbo1.getSelectedItem(),(int)cbo2.getSelectedItem());
            }
        }));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String date;
            if(e.getActionCommand().toString().length() < 2){
                if(cbo2.getSelectedItem().toString().length() < 2){
                    date = cbo1.getSelectedItem().toString() + "0" + cbo2.getSelectedItem().toString() + "0"+e.getActionCommand().toString();

                }

                else date = cbo1.getSelectedItem().toString() + cbo2.getSelectedItem().toString() + "0"+e.getActionCommand().toString();

            }
            else {
                if(cbo2.getSelectedItem().toString().length() < 2){
                    date = cbo1.getSelectedItem().toString() + "0" + cbo2.getSelectedItem().toString() + "0"+e.getActionCommand().toString();
                }
                else date = cbo1.getSelectedItem().toString() + cbo2.getSelectedItem().toString() + e.getActionCommand().toString();
            }
            new Dateview(date);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
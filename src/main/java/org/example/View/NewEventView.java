package org.example.View;

import org.example.EventData;
import org.example.LinkMysql;

import javax.swing.*;
import java.awt.*;

public class NewEventView extends JFrame {
    private Dateview dateView;
    public NewEventView(String date){
        this.setTitle("新建日程");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);

        JLabel label1 = new JLabel("请输入日程标题:");
        label1.setFont(new Font("111", Font.BOLD, 20));
        label1.setBounds(20,20,200,40);
        this.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("111",Font.BOLD,20));
        textField1.setBounds(200,20,250,40);

        this.add(textField1);

        JLabel label2 = new JLabel("请输入起始时间（格式如hh:mm:ss）:");
        label2.setFont(new Font("111", Font.BOLD, 20));
        label2.setBounds(20,70,400,40);
        this.add(label2);

        JTextField textField2 = new JTextField();
        textField2.setFont(new Font("111",Font.BOLD,20));
        textField2.setBounds(70,120,350,40);
        this.add(textField2);

        JLabel label3 = new JLabel("请输入结束时间:");
        label3.setFont(new Font("111", Font.BOLD, 20));
        label3.setBounds(20,170,200,40);
        this.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setFont(new Font("111",Font.BOLD,20));
        textField3.setBounds(70,220,350,40);
        this.add(textField3);

        JLabel label4 = new JLabel("请输入事件详情:");
        label4.setFont(new Font("111", Font.BOLD, 20));
        label4.setBounds(20,270,200,40);
        this.add(label4);

        JTextArea textArea4 = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea4);
        textArea4.setFont(new Font("111",Font.BOLD,20));
        scrollPane.setBounds(70,320,350,90);
        textArea4.setLineWrap(true);

        this.add(scrollPane);

        JButton button = new JButton();
        button.setText("完成");
        button.setFont(new Font("111",Font.BOLD,16));
        button.setForeground(Color.RED);
        button.setBackground(Color.gray);
        button.setBounds(200,420,80,35);
        button.addActionListener(e -> {
            EventData newEvent = new EventData(date,textArea4.getText(),textField1.getText(),textField2.getText(),textField3.getText());
            LinkMysql.newEventData(newEvent);
            this.dispose();

        });
        this.add(button);
    }


}

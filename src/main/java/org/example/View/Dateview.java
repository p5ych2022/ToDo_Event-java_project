package org.example.View;

import com.alee.laf.WebLookAndFeel;
import org.example.CalendarApi;
import org.example.Model.SingleDate;
import org.example.EventData;
import org.example.LinkMysql;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class Dateview extends JDialog {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    private JTable table;
    private SingleDate today;

    public Dateview(String date) throws IOException {
        this.setTitle(date);
        this.setSize(500, 750);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);


        SingleDate today = CalendarApi.transferSinfleDate(date);
        panel1.setLayout(null);
        panel1.setBounds(0, 0, 500, 350);
        panel1.setBackground(Color.lightGray);
        panel2.setLayout(new BorderLayout());
        panel2.setBounds(0, 350, 500, 450);
        panel2.setBackground(Color.darkGray);

        JLabel label1 = new JLabel(today.getData().getDate());
        label1.setBounds(50, 0, 350, 50);
        label1.setFont(new Font("111", Font.BOLD, 30));
        label1.setForeground(Color.ORANGE);
        panel1.add(label1);

        JLabel label1_1 = new JLabel();
        String lable1_1_content = " ";
        switch (today.getData().getWeekDay()) {
            case 1:
                lable1_1_content = "周一";
                break;
            case 2:
                lable1_1_content = "周二";
                break;
            case 3:
                lable1_1_content = "周三";
                break;
            case 4:
                lable1_1_content = "周四";
                break;
            case 5:
                lable1_1_content = "周五";
                break;
            case 6:
                lable1_1_content = "周六";
                break;
            case 7:
                lable1_1_content = "周天";
                break;

        }
        label1_1.setText(lable1_1_content);
        label1_1.setFont(new Font("111", Font.BOLD, 20));
        label1_1.setBounds(350, 0, 50, 50);
        label1_1.setForeground(Color.blue);
        panel1.add(label1_1);
        JLabel label2 = new JLabel("节假日： " + today.getData().getTypeDes());
        label2.setFont(new Font("111", Font.BOLD, 20));
        label2.setBounds(50, 50, 250, 50);
        panel1.add(label2);

        JLabel label3 = new JLabel("节气： " + today.getData().getSolarTerms());
        label3.setFont(new Font("111", Font.BOLD, 20));
        label3.setBounds(300, 50, 200, 50);
        panel1.add(label3);

        JLabel label4 = new JLabel("农历： " + today.getData().getLunarCalendar());
        label4.setFont(new Font("111", Font.BOLD, 20));
        label4.setBounds(50, 100, 250, 50);
        panel1.add(label4);

        JLabel label5 = new JLabel("星座： " + today.getData().getConstellation());
        label5.setFont(new Font("111", Font.BOLD, 20));
        label5.setBounds(300, 100, 200, 50);
        panel1.add(label5);

        JLabel label6 = new JLabel("宜：" + today.getData().getSuit());
        label6.setFont(new Font("111", Font.BOLD, 20));
        label6.setBounds(50, 150, 450, 50);
        panel1.add(label6);

        JLabel label7 = new JLabel("忌： " + today.getData().getAvoid());
        label7.setFont(new Font("111", Font.BOLD, 20));
        label7.setBounds(50, 200, 450, 50);
        panel1.add(label7);


        JButton addButton = new JButton("添加日程");
        addButton.setFont(new Font("111", Font.BOLD, 20));
        addButton.setBounds(50, 250, 150, 50);
        addButton.addActionListener(e -> {

            NewEventView newView = new NewEventView(today.getData().getDate());
            //按钮点击创建新窗口并且将Date属性传入，API返回的格式是"2018-11-21"
        });  // 设置按钮点击事件
        panel1.add(addButton);

        this.add(panel1);


        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel2.add(scrollPane, BorderLayout.CENTER);

        // Populate JTable with data from LinkMysql
        DefaultTableModel model = new DefaultTableModel();
        //model.addColumn("ID");
        model.addColumn("Start Time");
        model.addColumn("Finish Time");
        model.addColumn("Name");
        model.addColumn("Event");

        try {
            // 获取 LinkMysql 的查询结果
            java.util.List<EventData> eventDataList = LinkMysql.getEventData();

            // 将数据添加到表格模型中
            for (EventData eventData : eventDataList) {
                if (eventData.getDate().equals(today.getData().getDate())) {
                    model.addRow(new Object[]{
                            //eventData.getId(),
                            eventData.getStartTime(),
                            eventData.getFinishTime(),
                            eventData.getName(),
                            eventData.getEvent()
                    });
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        table.setModel(model);

        //添加并刷新
        this.add(panel2);
        panel2.revalidate();
        panel2.repaint();

    }


}
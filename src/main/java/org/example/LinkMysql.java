package org.example;

import org.example.EventData;
import org.example.util.jdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkMysql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 执行查询并获取结果
        List<EventData> eventDataList = getEventData();

        // 处理查询结果
        for (EventData eventData : eventDataList) {
            System.out.println("Name: " + eventData.getName() + ", event: " + eventData.getEvent());

        }
    }

    public static List<EventData> getEventData() throws ClassNotFoundException, SQLException {
        // 获取数据库连接
        Connection connection = jdbcUtil.getConnection();

        // 需要执行的 SQL 语句
        String sql = "SELECT * FROM user;";

        // 获取预处理对象
        PreparedStatement statement = connection.prepareStatement(sql);

        // 执行 SQL 查询
        ResultSet resultSet = statement.executeQuery();

        // 处理查询结果
        List<EventData> eventDataList = new ArrayList<>();
        while (resultSet.next()) {
            //int id = resultSet.getInt("id");
            String date = resultSet.getString("date");
            String name = resultSet.getString("name");
            String start_time =  resultSet.getString("start_time");
            String finish_time =  resultSet.getString("finish_time");
            String event =  resultSet.getString("event");

            EventData eventData = new EventData(date,event,name,start_time,finish_time);
            eventDataList.add(eventData);
        }

        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();

        // 返回查询结果列表
        return eventDataList;
    }

    public static void newEventData(EventData eventData) {
        try {
            // 获取数据库连接
            Connection connection = jdbcUtil.getConnection();

            // 需要执行的 SQL 语句
            String sql = "INSERT INTO user (date, event, name, start_time, finish_time) VALUES (?, ?, ?, ?, ?);";

            // 获取预处理对象
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // 设置参数
                statement.setString(1, eventData.getDate());
                statement.setString(2, eventData.getEvent());
                statement.setString(3, eventData.getName());
                statement.setString(4, eventData.getStartTime());
                statement.setString(5, eventData.getFinishTime());

                // 执行 SQL 插入
                statement.executeUpdate();
            }

            // 关闭资源
            connection.close();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }
}
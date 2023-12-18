package org.example;

public class EventData {
    private int id;
    private String date;
    private String event;
    private String name;
    private String start_time;
    private String finish_time;

    // 其他字段...

    public EventData(int id, String date,String event ,String name,String start_time,String finish_time) {
        this.id = id;
        this.date = date;
        this.event = event;
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
    }

    // 其他字段的 getter 和 setter 方法...

    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getEvent() {
        return event;
    }
    public String getName() {
        return name;
    }

    public String getStartTime() {
        return start_time;
    }

    public String getFinishTime() {
        return finish_time;
    }
}


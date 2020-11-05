package cn.edu.cn.tomatoclock.domain;

public class Clock {
    private int id;
    private String time;
    private String name;

    public Clock() {
    }

    public Clock(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

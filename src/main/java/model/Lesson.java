package homework.students.model;

import java.util.Date;

import static homework.students.util.DateUtil.dateToString;

public class Lesson {

    private String name;
    private String teacherName;
    private int duration;
    private double price;
    private Date startDate;

    public Lesson(String name, String teacherName, int duration, double price,Date startDate) {
        this.name = name;
        this.teacherName = teacherName;
        this.duration = duration;
        this.price = price;
        this.startDate=startDate;
    }

    public Lesson() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", startDate=" + dateToString(startDate) +
                '}';
    }
}

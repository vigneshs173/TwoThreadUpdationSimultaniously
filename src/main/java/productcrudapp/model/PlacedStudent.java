package productcrudapp.model;// PlacedStudent.java

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlacedStudent {

    @Id
    private Long id;
    private String name;
    private int rollno;
    private String gmail;
    private Integer mark;
    private int isExists = 1;

    public PlacedStudent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public int getIsExists() {
        return isExists;
    }

    public void setIsExists(int isExists) {
        this.isExists = isExists;
    }
}

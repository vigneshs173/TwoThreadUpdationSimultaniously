package productcrudapp.model;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name should only have alphabets and spaces")
	@NotBlank(message = "Name is required")
	private String name;
	private int rollno;
	@NotEmpty(message = "gmail is required")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Gmail")
	private String gmail;
	@NotNull(message = "No null accepted")
	@Min(value = 0, message = "Mark should not be less than 0")
	@Max(value = 100, message = "Mark should not be greater than 100")
	private Integer mark;
	public int isExists =1;


	public Student()
	{}
	public Long getId() {
		return id;
	}

	public int getIsExists() {
		return isExists;
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

		public void setIsExists(int isExists) {
		this.isExists = isExists;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", rollno=" + rollno +
				", gmail='" + gmail + '\'' +
				", mark=" + mark +
				", isExists=" + isExists +
				'}';
	}

}


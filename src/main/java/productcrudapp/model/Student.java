package productcrudapp.model;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotNull(message = "name mustn't be null")
	private String name;
	private int rollno;

}



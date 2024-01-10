package productcrudapp.controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;
import productcrudapp.model.Student;

public class ApiHitJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Student student = new Student();
        student.setRollno(77);
        student.setName("Karupusamy");


        String apiUrl = "https://updateNow?rollno=" + student.getRollno() +
                "&name=" + student.getName();

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        System.out.println("API Response: " + response);

        System.out.println("Executing API hit job at: " + System.currentTimeMillis());
    }
}
package lt.javadarbai.springexample;

import lombok.AllArgsConstructor;
import lt.javadarbai.springexample.services.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
@SpringBootApplication
public class SpringExampleApplication {


    private CarService carService;


    public static void main(String[] args) {
        SpringApplication.run(SpringExampleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup(){
        this.carService.addTestCars();
        this.carService.printAllCars();

    }

}

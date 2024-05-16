package lt.javadarbai.springexample;

import lombok.AllArgsConstructor;
import lt.javadarbai.springexample.services.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
//        this.carService.printAllCars();

        this.carService.printAllCarsPageable(PageRequest.of(0, 2));
//        this.carService.printAllCarsPageable(PageRequest.of(1, 2));
        this.carService.printAllCarsPageable(PageRequest.of(2, 2));
        this.carService.addTestCars();
        this.carService.printAllCarsPageable(PageRequest.of(3, 2));
        this.carService.printAllCarsPageable(PageRequest.of(4, 2));
//        System.out.println();
//        this.carService.printAllCarsByColor("GREEN");
//        this.carService.printAllCarsByColor("RED");
//        this.carService.printAllCarsByColor("BLUE");
//        this.carService.test();

    }

}

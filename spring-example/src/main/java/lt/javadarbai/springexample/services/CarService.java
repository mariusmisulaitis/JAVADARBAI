package lt.javadarbai.springexample.services;

import lombok.AllArgsConstructor;
import lt.javadarbai.springexample.entities.Car;
import lt.javadarbai.springexample.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;


    public void addTestCars(){
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            car.setMake("MAKE-"+i);
            car.setModel("MODEL-"+i);
            car.setColor("COLOR-"+i);
            car.setEngineSizeInLiters((double) i);
            car.setFuelType("FUEL-TYPE-"+i);
            cars.add(car);
        }
        this.carRepository.saveAllAndFlush(cars);
    }

    public void printAllCars(){
        System.out.println("In total we have " + this.carRepository.count() + " cars");
        System.out.println("Car with ID=5 exists: " + this.carRepository.existsById(5L));
        System.out.println(this.carRepository.findById(5L));
        this.carRepository.deleteById(5L);
        System.out.println("Car with ID=5 exists: " + this.carRepository.existsById(5L));
        for (Car c : this.carRepository.findAll()) {
            System.out.println(c);
        }
    }
}

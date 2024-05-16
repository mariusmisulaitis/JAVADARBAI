package lt.javadarbai.springexample.services;

import lombok.AllArgsConstructor;
import lt.javadarbai.springexample.entities.Car;
import lt.javadarbai.springexample.repositories.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;


    public void addTestCars() {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            car.setMake("MAKE-" + i);
            car.setModel("MODEL-" + i);
//          car.setColor("COLOR-"+i);

            if (i % 2 == 0) {
                car.setColor("BLUE");
            } else {
                car.setColor("RED");
            }

            car.setEngineSizeInLiters((double) i);
            car.setFuelType("FUEL-TYPE-" + i);
            cars.add(car);
        }
        this.carRepository.saveAllAndFlush(cars);
    }

    public void printAllCars() {
        System.out.println("In total we have " + this.carRepository.count() + " cars");
        System.out.println("Car with ID=5 exists: " + this.carRepository.existsById(5L));
        System.out.println(this.carRepository.findById(5L));
        this.carRepository.deleteById(5L);
        System.out.println("Car with ID=5 exists: " + this.carRepository.existsById(5L));
        for (Car c : this.carRepository.findAll()) {
            System.out.println(c);
        }
    }

//    public void printAllCarsPageable(Pageable pageable) {
//        for (Car c : this.carRepository.findAll(pageable)) {
//            System.out.println(c);
//        }
//    }


    public void printAllCarsPageable(Pageable pageable) {
        Page<Car> cars = this.carRepository.findAll(pageable);
        System.out.printf("Page %d out of %d pages%n", pageable.getPageNumber() + 1, cars.getTotalPages());
        for (Car c : this.carRepository.findAll(pageable)) {
            System.out.println(c);
        }
    }

//    public void printAllCarsByColor(String color) {
//        System.out.println("Cars by color " + color);
//        for (Car c : this.carRepository.getCarsByColor(color)) {
//            System.out.println(c);
//        }
//    }

//    public void printAllCarsByColor(String color) {
//        System.out.println("Cars by color " + color);
//        for (Car c : this.carRepository.getCarsByColorNative(color)) {
//            System.out.println(c);
//        }
//    }

//    public void printAllCarsByColor(String color) {
//        System.out.println("Cars by color " + color);
//        for (Car c : this.carRepository.getAllByColor(color)) {
//            System.out.println(c);
//        }
//    }


    public void test(){
        System.out.println();
        for (Car c : this.carRepository.getAllByColorOrModelOrMakeOrderByFuelType("BLUE","MODEL-3", "MAKE-2")) {
            System.out.println(c);
        }
        System.out.println();
        System.out.println(this.carRepository.countAllByEngineSizeInLiters(3.0));
    }

    public void printAllCarsByColor(String color) {
        System.out.println("Cars by color " + color);
        if (this.carRepository.existsByColor(color)) {
            for (Car c : this.carRepository.getAllByColor(color)) {
                System.out.println(c);
            }
        } else {
            System.out.println("No Car with color " + color + " exists");
        }
    }

}

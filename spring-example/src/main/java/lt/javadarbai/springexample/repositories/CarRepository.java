package lt.javadarbai.springexample.repositories;

import lt.javadarbai.springexample.entities.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> getAllByColor(String color);

    Boolean existsByColor(String color);

    List<Car> getAllByColorOrModelOrMakeOrderByFuelType(String color, String model, String make);

    Integer countAllByEngineSizeInLiters(Double engineSizeInLiters);

    @Query("SELECT c FROM Car c WHERE c.color=?1")
    List<Car> getCarsByColor(String color);

    @Query("SELECT c FROM Car c WHERE c.color= :color OR c.model= :model OR c.make= :make")
    List<Car> getCarsByColorOrModelOrMake(@Param("color") String color,
                                          @Param("model") String model,
                                          @Param("make") String make);

    @Query(value = "SELECT * from car WHERE color= :color", nativeQuery = true)
    List<Car> getCarsByColorNative(@Param("color") String color);
}

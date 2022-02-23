package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.web.bind.annotation.*;
// utility-metoder: get, post osv
import java.util.List;

@RestController
    @RequestMapping("api/cars") // rod-endpoint til hele klassen. URL til alle ressourcer
    public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}") // pathvariable. hænger sammen med nedenstående
    public CarResponse getCar(@PathVariable int id) throws Exception {
        return carService.getCar(id, false);
    }


    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body) { // reference til http-protocollen. Parametrer bliver konverteret fra JSON til Java
        return carService.addCar(body);// serveren laver det om til JSON
    }

    @PutMapping("/{id}")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id) throws Exception {
        return carService.editCar(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) throws Exception {
        carService.deleteCar(id);

    }

    @PatchMapping("/{id}/{newprice}")
    public void editPrice(@PathVariable int id, @PathVariable double newprice) throws Exception{
        carService.updatePrice(id, newprice);
    }

}





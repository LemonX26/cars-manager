package com.car_manager.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.car_manager.model.Car;
import com.car_manager.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getCars()); //returns list of all cars
        model.addAttribute("newCar", new Car());

        // Adding attributes for filtering
        model.addAttribute("names", carService.getALlNames());
        model.addAttribute("models", carService.getAllModels());
        model.addAttribute("categories", carService.getAllCategories());
        model.addAttribute("years", carService.getAllProductionYears());
        model.addAttribute("status", carService.getAllCarStatus());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        // check if user has role Admin, and if yes, show add & delete button

        return "carList";
    }
    @GetMapping("/filters")
    public String filterCars(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "model", required = false) String model,
                             @RequestParam(value = "categories", required = false) String categories,
                             @RequestParam(value = "productionYears", required = false) Integer productionYear,
                             @RequestParam(value = "minMileage", required = false) Integer minMileage,
                             @RequestParam(value = "maxMileage", required = false) Integer maxMileage,
                             @RequestParam(value = "status", required = false) String selectedStatus,
                             Model modelAttribute) {

        List<Car> filteredCars = carService.filterCars(name, model, categories, productionYear, minMileage, maxMileage, selectedStatus);
        modelAttribute.addAttribute("cars", filteredCars);
        modelAttribute.addAttribute("newCar", new Car());

        modelAttribute.addAttribute("names", carService.getALlNames());
        modelAttribute.addAttribute("categories", carService.getAllCategories());
        modelAttribute.addAttribute("years", carService.getAllProductionYears());
        modelAttribute.addAttribute("status", carService.getAllCarStatus());
        if(name != null && !name.isBlank()){
            List<String> selectedModels = carService.findModelsForCarName(name);
            modelAttribute.addAttribute("models", selectedModels);
        }
        else {
            modelAttribute.addAttribute("models", carService.getAllModels());
        }
        // Zapamiętanie wybranych wartości
        modelAttribute.addAttribute("selectedName", name);
        modelAttribute.addAttribute("selectedModel", model);
        modelAttribute.addAttribute("categoriesValue", categories);
        modelAttribute.addAttribute("productionYearsValue", productionYear);
        modelAttribute.addAttribute("minMileage", minMileage);
        modelAttribute.addAttribute("maxMileage", maxMileage);
        modelAttribute.addAttribute("selectedStatus", selectedStatus); // wartość statusu z request param

        return "carList";
      }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addCar(@ModelAttribute("newCar") Car newCar) {
        carService.addCar(newCar);
        return "redirect:/cars";
    }

    @PostMapping("/delete/{carId}")
    public String deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
        return "redirect:/cars";
    }

    @GetMapping("/sort")
    public String sortCarsBy (@RequestParam(value = "sortBy", required = false) String sortDir,
                              Model model){
        List<Car> sortedCars = carService.sortBy(sortDir);

        model.addAttribute("cars", sortedCars);
        model.addAttribute("newCar", new Car());
        // Adding attributes for filtering

        model.addAttribute("names", carService.getALlNames());
        model.addAttribute("models", carService.getAllModels());
        model.addAttribute("categories", carService.getAllCategories());
        model.addAttribute("years", carService.getAllProductionYears());
        model.addAttribute("mileages", carService.getAllMileages());

        model.addAttribute("sortBy", sortDir);

        return "carList";
    }

    @GetMapping("/details/{carId}")
    public String getCarData(@PathVariable int carId, Model model){
        Car car =  carService.getAllCarData(carId);
        model.addAttribute("car", car);

        model.addAttribute("names", car.getName());
        model.addAttribute("models", car.getModel());
        model.addAttribute("categories", car.getCategory());
        model.addAttribute("years", car.getProductionYear());
        model.addAttribute("mileages", car.getMileage());
        model.addAttribute("status", car.getStatus());

        return "carData";
    }

    @GetMapping("/data/{carId}")
    @ResponseBody
    public Car getCarData(@PathVariable int carId){
        return carService.getAllCarData(carId);
    }

}


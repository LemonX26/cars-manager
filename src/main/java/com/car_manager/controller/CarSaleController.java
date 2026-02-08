package com.car_manager.controller;

import com.car_manager.repository.CarSaleMongoRepository;
import com.car_manager.service.EmailService;
import com.car_manager.model.Car;
import com.car_manager.model.CarSaleAnnouncement;
import com.car_manager.model.CarSaleAnnouncementDocument;
import com.car_manager.model.CarStatus;
import com.car_manager.service.CarSaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarSaleController {
    private final CarSaleService carSaleService;
    
    private final EmailService emailService;

    private final CarSaleMongoRepository carSaleMongoRepository;  // Mongo repo
    public CarSaleController(CarSaleService carSaleService, EmailService emailService, CarSaleMongoRepository carSaleMongoRepository) {
        this.carSaleService = carSaleService;
        this.emailService = emailService;
        this.carSaleMongoRepository = carSaleMongoRepository;
    }

    @GetMapping("/sales/create")
    public String showCreateForm(Model model) {
        CarSaleAnnouncementDocument carSaleAnnouncementDocument = new CarSaleAnnouncementDocument();
        carSaleAnnouncementDocument.setCar(new Car());

        model.addAttribute("carSaleAnnouncementDocument", carSaleAnnouncementDocument);
        model.addAttribute("statuses", CarStatus.values());

        // Dodajemy atrybuty do modelu, aby były dostępne w szablonie

        CarSaleAnnouncement announcement = new CarSaleAnnouncement();
        announcement.setCar(new Car());

        model.addAttribute("names", carSaleService.getALlNames());
        model.addAttribute("models", carSaleService.getAllModels());
        model.addAttribute("categories", carSaleService.getAllCategories());
        model.addAttribute("statuses", List.of(CarStatus.values()));

        model.addAttribute("carSaleAnnouncement", announcement);
        return "carSale"; // Twój szablon formularza
    }

    @PostMapping("/sales/create")
    public String addAnnouncement(@ModelAttribute CarSaleAnnouncement carSaleAnnouncement, @ModelAttribute CarSaleAnnouncementDocument carSaleAnnouncementDocument, Model model) {
        try {
            carSaleService.addCarSaleAnnouncement(carSaleAnnouncement);
            carSaleMongoRepository.save(carSaleAnnouncementDocument);
            /* optional
            emailService.sendJsonAnnouncement(carSaleAnnouncement);
            emailService.sendAnnouncementEmail(carSaleAnnouncement);
            */

        } catch (IllegalArgumentException e) {
            System.out.println("Nie można dodać ogłoszenia: " + e.getMessage());

            return "redirect:/cars/sales/create";
        }

        return "redirect:/cars/sales/all";
    }

    @GetMapping("/sales/allSalesData")
    @ResponseBody
    public List<CarSaleAnnouncement> getAllSales() {
        return carSaleService.findAll();
    }

    @GetMapping("/sales/all")
    public String getAllSalesPage(Model model) {
        List<CarSaleAnnouncement> carSaleAnnouncements = carSaleService.findAll();
        model.addAttribute("carSaleAnnouncements", carSaleAnnouncements);
        return "carSalesList";
    }


}

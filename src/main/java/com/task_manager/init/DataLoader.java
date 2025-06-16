package com.task_manager.init;

import com.task_manager.model.*;
import com.task_manager.repository.CarRepository;
import com.task_manager.repository.CarSaleRepository;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CarRepository carRepository;

    private final CarSaleRepository carSaleRepository;

    public DataLoader(PasswordEncoder passwordEncoder,
                      UserRepository userRepository,
                      TaskRepository taskRepository,
                      CarRepository carRepository, CarSaleRepository carSaleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.carRepository = carRepository;
        this.carSaleRepository = carSaleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Only load data if database is empty
        if (userRepository.count() == 0 && carRepository.count() == 0) {
            // Create and save cars first
            Car car1 = new Car("Lamborghini", "Aventador", "Sport", 2023, 500000, "Italy", CarStatus.FACTORY);
            Car car2 = new Car("Ferrari", "488 GTB", "Sport", 2022, 350000, "Italy", CarStatus.USED);
            Car car3 = new Car("Porsche", "911 Carrera", "Sport", 2021, 100000, "Germany", CarStatus.USED);
            Car car4 = new Car("BMW", "M3", "Sedan", 2021, 70000, "Germany", CarStatus.SCRAP);
            Car car5 = new Car("Mercedes-Benz", "S-Class", "Luxury", 2020, 120000, "Germany", CarStatus.USED);
            Car car6 = new Car("Audi", "A8", "Luxury", 2022, 95000, "Germany", CarStatus.FACTORY);
            Car car7 = new Car("Tesla", "Model S", "Electric", 2023, 90000, "USA", CarStatus.FACTORY);
            Car car8 = new Car("Toyota", "Camry", "Sedan", 2020, 25000, "Japan", CarStatus.USED);
            Car car9 = new Car("Honda", "Civic", "Compact", 2021, 20000, "Japan", CarStatus.FACTORY);
            Car car10 = new Car("Ford", "Mustang", "Sport", 2021, 40000, "USA", CarStatus.USED);
            Car car11 = new Car("Chevrolet", "Corvette", "Sport", 2023, 60000, "USA", CarStatus.FACTORY);
            Car car12 = new Car("Nissan", "GT-R", "Sport", 2021, 120000, "Japan", CarStatus.SCRAP);
            Car car13 = new Car("Aston Martin", "DB11", "Luxury", 2022, 150000, "UK", CarStatus.FACTORY);
            Car car14 = new Car("Jaguar", "F-Type", "Sport", 2021, 70000, "UK", CarStatus.USED);
            Car car15 = new Car("Maserati", "GranTurismo", "Luxury", 2020, 90000, "Italy", CarStatus.USED);
            Car car16 = new Car("Land Rover", "Range Rover", "SUV", 2022, 95000, "UK", CarStatus.FACTORY);
            Car car17 = new Car("Bentley", "Continental GT", "Luxury", 2021, 200000, "UK", CarStatus.USED);
            Car car18 = new Car("Rolls-Royce", "Phantom", "Luxury", 2023, 450000, "UK", CarStatus.FACTORY);
            Car car19 = new Car("Volkswagen", "Golf GTI", "Compact", 2022, 30000, "Germany", CarStatus.FACTORY);
            Car car20 = new Car("Subaru", "WRX", "Sport", 2022, 35000, "Japan", CarStatus.FACTORY);
            Car car21 = new Car("Mazda", "MX-5", "Sport", 2021, 30000, "Japan", CarStatus.USED);
            Car car22 = new Car("Hyundai", "Elantra", "Compact", 2022, 25000, "South Korea", CarStatus.FACTORY);
            Car car23 = new Car("Kia", "Stinger", "Sport", 2021, 45000, "South Korea", CarStatus.FACTORY);
            Car car24 = new Car("Ford", "F-150", "Truck", 2022, 55000, "USA", CarStatus.USED);
            Car car25 = new Car("Chevrolet", "Silverado", "Truck", 2021, 60000, "USA", CarStatus.FACTORY);
            Car car26 = new Car("Toyota", "Hilux", "Truck", 2023, 35000, "Japan", CarStatus.USED);
            Car car27 = new Car("Ram", "1500", "Truck", 2022, 70000, "USA", CarStatus.SCRAP);
            Car car28 = new Car("Jeep", "Wrangler", "SUV", 2022, 40000, "USA", CarStatus.FACTORY);
            Car car29 = new Car("BMW", "X5", "SUV", 2021, 90000, "Germany", CarStatus.FACTORY);
            Car car30 = new Car("Mercedes-Benz", "GLC", "SUV", 2022, 95000, "Germany", CarStatus.USED);
            Car car31 = new Car("Honda", "CR-V", "SUV", 2020, 25000, "Japan", CarStatus.FACTORY);
            Car car32 = new Car("Audi", "Q5", "SUV", 2021, 75000, "Germany", CarStatus.SCRAP);
            Car car33 = new Car("Porsche", "Cayenne", "SUV", 2022, 100000, "Germany", CarStatus.USED);
            Car car34 = new Car("Volvo", "XC90", "SUV", 2021, 80000, "Sweden", CarStatus.USED);
            Car car35 = new Car("Tesla", "Model X", "Electric", 2022, 110000, "USA", CarStatus.FACTORY);
            Car car36 = new Car("Ford", "Explorer", "SUV", 2021, 50000, "USA", CarStatus.SCRAP);
            Car car37 = new Car("Chevrolet", "Tahoe", "SUV", 2022, 65000, "USA", CarStatus.USED);
            Car car38 = new Car("Mazda", "CX-5", "SUV", 2021, 35000, "Japan", CarStatus.FACTORY);
            Car car39 = new Car("Nissan", "Rogue", "SUV", 2021, 27000, "Japan", CarStatus.SCRAP);
            Car car40 = new Car("Kia", "Sportage", "SUV", 2021, 25000, "South Korea", CarStatus.FACTORY);
            Car car41 = new Car("Toyota", "4Runner", "SUV", 2020, 45000, "Japan", CarStatus.FACTORY);
            Car car42 = new Car("Hyundai", "Tucson", "SUV", 2021, 35000, "South Korea", CarStatus.USED);
            Car car43 = new Car("Honda", "Pilot", "SUV", 2022, 65000, "Japan", CarStatus.SCRAP);
            Car car44 = new Car("Ford", "Escape", "SUV", 2022, 30000, "USA", CarStatus.FACTORY);
            Car car45 = new Car("Chevrolet", "Equinox", "SUV", 2021, 35000, "USA", CarStatus.USED);
            Car car46 = new Car("BMW", "X6", "SUV", 2022, 80000, "Germany", CarStatus.USED);
            Car car47 = new Car("Audi", "Q7", "SUV", 2021, 90000, "Germany", CarStatus.FACTORY);
            Car car48 = new Car("Nissan", "Pathfinder", "SUV", 2022, 40000, "Japan", CarStatus.SCRAP);
            Car car49 = new Car("Subaru", "Outback", "SUV", 2021, 32000, "Japan", CarStatus.FACTORY);
            Car car50 = new Car("Jeep", "Grand Cherokee", "SUV", 2021, 60000, "USA", CarStatus.USED);
            Car car51 = new Car("Peugeot", "308", "Compact", 2021, 20000, "France", CarStatus.USED);
            Car car52 = new Car("Renault", "Clio", "Compact", 2022, 18000, "France", CarStatus.FACTORY);
            Car car53 = new Car("Citroën", "C5 Aircross", "SUV", 2021, 27000, "France", CarStatus.FACTORY);
            Car car54 = new Car("Opel", "Insignia", "Sedan", 2020, 22000, "Germany", CarStatus.SCRAP);
            Car car55 = new Car("Skoda", "Octavia", "Sedan", 2022, 24000, "Czech Republic", CarStatus.FACTORY);
            Car car56 = new Car("Seat", "Leon", "Compact", 2021, 23000, "Spain", CarStatus.USED);
            Car car57 = new Car("Fiat", "500", "Compact", 2023, 17000, "Italy", CarStatus.FACTORY);
            Car car58 = new Car("Lancia", "Ypsilon", "Compact", 2020, 15000, "Italy", CarStatus.SCRAP);
            Car car59 = new Car("Dacia", "Duster", "SUV", 2021, 16000, "Romania", CarStatus.USED);
            Car car60 = new Car("Alfa Romeo", "Giulia", "Sedan", 2022, 45000, "Italy", CarStatus.FACTORY);
            Car car61 = new Car("Saab", "9-3", "Sedan", 2009, 8000, "Sweden", CarStatus.SCRAP);
            Car car62 = new Car("Suzuki", "Vitara", "SUV", 2021, 19000, "Japan", CarStatus.USED);
            Car car63 = new Car("Isuzu", "D-Max", "Truck", 2022, 30000, "Japan", CarStatus.FACTORY);
            Car car64 = new Car("Mitsubishi", "Outlander", "SUV", 2020, 21000, "Japan", CarStatus.SCRAP);
            Car car65 = new Car("Mini", "Cooper", "Compact", 2021, 25000, "UK", CarStatus.USED);
            Car car66 = new Car("Chrysler", "300", "Sedan", 2022, 34000, "USA", CarStatus.FACTORY);
            Car car67 = new Car("Dodge", "Charger", "Sedan", 2021, 38000, "USA", CarStatus.USED);
            Car car68 = new Car("Buick", "Enclave", "SUV", 2020, 36000, "USA", CarStatus.SCRAP);
            Car car69 = new Car("Lincoln", "Navigator", "SUV", 2022, 75000, "USA", CarStatus.FACTORY);
            Car car70 = new Car("Genesis", "G80", "Luxury", 2023, 60000, "South Korea", CarStatus.FACTORY);
            Car car71 = new Car("Infiniti", "Q50", "Sedan", 2021, 33000, "Japan", CarStatus.USED);
            Car car72 = new Car("Acura", "TLX", "Sedan", 2022, 37000, "Japan", CarStatus.FACTORY);
            Car car73 = new Car("Lexus", "RX", "SUV", 2021, 50000, "Japan", CarStatus.FACTORY);
            Car car74 = new Car("Cadillac", "Escalade", "SUV", 2023, 85000, "USA", CarStatus.FACTORY);
            Car car75 = new Car("Hummer", "EV", "Truck", 2023, 95000, "USA", CarStatus.FACTORY);
            Car car76 = new Car("Lucid", "Air", "Electric", 2023, 120000, "USA", CarStatus.FACTORY);
            Car car77 = new Car("Rivian", "R1T", "Truck", 2023, 85000, "USA", CarStatus.FACTORY);
            Car car78 = new Car("Fisker", "Ocean", "Electric", 2023, 37000, "USA", CarStatus.FACTORY);
            Car car79 = new Car("Bugatti", "Chiron", "Sport", 2021, 3000000, "France", CarStatus.USED);
            Car car80 = new Car("Koenigsegg", "Jesko", "Sport", 2023, 2500000, "Sweden", CarStatus.FACTORY);
            Car car81 = new Car("Pagani", "Huayra", "Sport", 2022, 2800000, "Italy", CarStatus.FACTORY);
            Car car82 = new Car("McLaren", "720S", "Sport", 2022, 300000, "UK", CarStatus.USED);
            Car car83 = new Car("Lotus", "Evora", "Sport", 2021, 90000, "UK", CarStatus.SCRAP);
            Car car84 = new Car("Tata", "Nexon", "SUV", 2021, 13000, "India", CarStatus.USED);
            Car car85 = new Car("Mahindra", "XUV700", "SUV", 2022, 20000, "India", CarStatus.FACTORY);
            Car car86 = new Car("BYD", "Han", "Electric", 2022, 40000, "China", CarStatus.FACTORY);
            Car car87 = new Car("NIO", "ES8", "Electric", 2022, 50000, "China", CarStatus.FACTORY);
            Car car88 = new Car("Xpeng", "P7", "Electric", 2022, 42000, "China", CarStatus.FACTORY);
            Car car89 = new Car("Geely", "Coolray", "SUV", 2021, 19000, "China", CarStatus.USED);
            Car car90 = new Car("Great Wall", "Haval H6", "SUV", 2021, 21000, "China", CarStatus.SCRAP);
            Car car91 = new Car("Proton", "X70", "SUV", 2022, 22000, "Malaysia", CarStatus.FACTORY);
            Car car92 = new Car("Perodua", "Myvi", "Compact", 2021, 12000, "Malaysia", CarStatus.FACTORY);
            Car car93 = new Car("Zotye", "T600", "SUV", 2020, 10000, "China", CarStatus.SCRAP);
            Car car94 = new Car("Changan", "CS75", "SUV", 2022, 18000, "China", CarStatus.FACTORY);
            Car car95 = new Car("Lifan", "X60", "SUV", 2020, 11000, "China", CarStatus.SCRAP);
            Car car96 = new Car("FAW", "Bestune T77", "SUV", 2022, 20000, "China", CarStatus.FACTORY);
            Car car97 = new Car("BAIC", "BJ40", "SUV", 2021, 23000, "China", CarStatus.USED);
            Car car98 = new Car("Dongfeng", "Fengon 580", "SUV", 2022, 19000, "China", CarStatus.FACTORY);
            Car car99 = new Car("VinFast", "VF8", "Electric", 2023, 40000, "Vietnam", CarStatus.FACTORY);
            Car car100 = new Car("Wuling", "Hongguang Mini EV", "Electric", 2023, 5000, "China", CarStatus.FACTORY);

            carRepository.saveAll(List.of(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10, car11, car12, car13, car14, car15, car16, car17, car18, car19, car20, car21, car22, car23, car24, car25, car26, car27, car28, car29, car30, car31, car32, car33, car34, car35, car36, car37, car38, car39, car40, car41, car42, car43, car44, car45, car46, car47, car48, car49, car50, car51, car52, car53, car54, car55, car56, car57, car58, car59, car60, car61, car62, car63, car64, car65, car66, car67, car68, car69, car70, car71, car72, car73, car74, car75, car76, car77, car78, car79, car80, car81, car82, car83, car84, car85, car86, car87, car88, car89, car90, car91, car92, car93, car94, car95, car96, car97, car98, car99, car100));

            // Create users with references to cars
            User admin = new User("admin", passwordEncoder.encode("admin123"), "admin@gmail.com", Role.ADMIN);
            User user = new User("user", passwordEncoder.encode("user123"), "user@gmail.com", Role.USER);

            // Save users first
            userRepository.saveAll(List.of(admin, user));

            // Assign cars to users after they're saved
            admin.addCar(car6);
            user.addCars(List.of(car1, car21, car3, car44, car23, car34, car18, car19, car10));
            userRepository.saveAll(List.of(admin, user));

            // Create tasks
            Task adminTask = new Task("Pizza", "This is a pizza for admin", admin);
            Task adminTask2 = new Task("Kebab", "This is a kebab for admin", admin);
            Task adminTask3 = new Task("Sushi", "This is a sushi for admin", admin);
            Task adminTask4 = new Task("Burger", "This is a burger for admin", admin);
            Task adminTask5 = new Task("Pasta", "This is a pasta for admin", admin);
            // ... other admin tasks

            Task userTask = new Task("Car", "This is a car for user", user);
            Task userTask2 = new Task("Bike", "This is a bike for user", user);
            // ... other user tasks

            taskRepository.saveAll(List.of(adminTask, adminTask2,adminTask3,adminTask4,adminTask5,userTask,userTask2));




            CarSaleAnnouncement sale2 = new CarSaleAnnouncement(car4, 20000, "Sprzedam zadbany samochód", "manius.kowalski@example.com", "http://example.com/image2.jpg", "Kraków", "Maniuś Kowalski");

            carSaleRepository.saveAll(List.of( sale2));
            System.out.println("Initial data loaded successfully");
        }
    }
}
//package com.proyect.CompilAir.AutomaticSQL;
//
//import com.proyect.CompilAir.models.Route;
//import com.proyect.CompilAir.repositories.IRouteRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//
//public class RoutesDataInitializer {
//    @Bean
//    CommandLineRunner initDatabase(IRouteRepository iRouteRepository) {
//        return args -> {
//            Route route1 = new Route();
//            route1.setNameRoute("SVQ-IBZ");
//
//            Route route2 = new Route();
//            route2.setNameRoute("MAD-BCN");
//
//            Route route3 = new Route();
//            route3.setNameRoute("AGP-LPA");
//
//            Route route4 = new Route();
//            route4.setNameRoute("BIO-PMI");
//
//            Route route5 = new Route();
//            route5.setNameRoute("VLC-SCQ");
//
//            Route route6 = new Route();
//            route6.setNameRoute("MAD-SVQ");
//
//            Route route7 = new Route();
//            route7.setNameRoute("BCN-IBZ");
//
//            Route route8 = new Route();
//            route8.setNameRoute("AGP-MAD");
//
//            iRouteRepository.save(route1);
//            iRouteRepository.save(route2);
//            iRouteRepository.save(route3);
//            iRouteRepository.save(route4);
//            iRouteRepository.save(route5);
//            iRouteRepository.save(route6);
//            iRouteRepository.save(route7);
//            iRouteRepository.save(route8);
//
//            System.out.println("Routes automatically generated in database");
//        };
//    }
//}

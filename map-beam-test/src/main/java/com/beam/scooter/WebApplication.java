package com.beam.scooter;

import com.beam.scooter.Service.LocationService;
import com.beam.scooter.model.LocationEntry;
import com.beam.scooter.repo.LocationMongoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    @Autowired
    private LocationMongoRepository locationMongoRepository;

    @Autowired
    private LocationService locationService;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

    @PostConstruct
    private  void initializeDB() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<LocationEntry>> typeReference = new TypeReference<List<LocationEntry>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/location.json");
        try {
            locationMongoRepository.deleteAll();
            List<LocationEntry> locationResponseList = mapper.readValue(inputStream,typeReference);
            locationService.addLocations(locationResponseList);
            System.out.println("Locations Saved!");
        } catch (IOException e){
            System.out.println("Unable to save Locations: " + e.getMessage());
        }
    }
}


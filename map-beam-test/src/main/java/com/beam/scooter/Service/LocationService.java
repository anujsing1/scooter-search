package com.beam.scooter.Service;

import com.beam.scooter.model.LocationEntity;
import com.beam.scooter.model.LocationEntry;
import com.beam.scooter.model.LocationQuantity;
import com.beam.scooter.model.LocationResponse;
import com.beam.scooter.repo.LocationMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationMongoRepository repository;

    public List<LocationResponse> getLocations(String name, Double longitude, Double latitude, Double distance, Integer limit) {
        List<LocationEntity> result = null;
        int page = 0;
        int size = limit;
        Pageable pageable = new PageRequest( page,  size);
        if(name == null || name.trim().equalsIgnoreCase("")) {
            result=  repository.findByLocationNear(new Point(longitude, latitude),
                    new Distance(distance, Metrics.KILOMETERS),pageable).getContent();
        } else {
            result=  this.repository.findByNameAndLocationNear(name,
                    new Point(longitude, latitude),
                    new Distance(distance, Metrics.KILOMETERS),pageable).getContent();
        }
        return result.stream().map(locationEntity -> new LocationResponse(locationEntity)).collect(Collectors.toList());
    }

    public void addLocations( List<LocationEntry> entries) {
        List<LocationEntity> entities = entries.stream().map(locationEntry -> new LocationEntity(locationEntry.getTitle(), new GeoJsonPoint(
                locationEntry.getLongitude(),
                locationEntry.getLatitude()))).collect(Collectors.toList());
        entities = this.repository.save(entities);
        System.out.println(entities);
    }


    public List<LocationQuantity>  getLocationQuantity(List<LocationQuantity> idealDropOffLocationQuantity, Integer distance) {
        List<LocationQuantity> locationQuantities = null;
        List<LocationEntity> locationEntities=  this.repository.findAll();
        locationQuantities = idealDropOffLocationQuantity.parallelStream().map(locationQuantity -> {
           List<LocationEntity> locationEntities1 = this.repository.findByLocationNear(new Point(locationQuantity.getPoint().getLongitude(), locationQuantity.getPoint().getLongitude()),
                    new Distance(distance, Metrics.KILOMETERS));
           Integer quantity = null;
           if(locationEntities1.size() >=  locationQuantity.getQuantity() ) {
               quantity = 0;
           } else if((locationQuantity.getQuantity() - locationEntities1.size() )<= (locationEntities.size()- locationEntities1.size())){
               quantity = locationQuantity.getQuantity() - locationEntities1.size();
           } else {
               quantity = locationEntities.size()- locationEntities1.size();
            }

           return  new LocationQuantity(locationQuantity.getPoint(),quantity);
        }).collect(Collectors.toList());
        return locationQuantities;
    }
}

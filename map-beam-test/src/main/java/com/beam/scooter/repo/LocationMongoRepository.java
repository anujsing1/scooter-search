package com.beam.scooter.repo;

import com.beam.scooter.model.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LocationMongoRepository extends MongoRepository<LocationEntity, String> {

    Page<LocationEntity> findByNameAndLocationNear(String name, Point p, Distance d, Pageable pageable);

    Page<LocationEntity> findByLocationNear(Point p, Distance d,Pageable pageable);
}

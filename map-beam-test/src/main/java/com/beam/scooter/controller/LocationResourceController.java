package com.beam.scooter.controller;

import com.beam.scooter.model.DropOffLocationQuantityResponse;
import com.beam.scooter.model.LocationEntry;
import com.beam.scooter.model.LocationQuantity;
import com.beam.scooter.model.LocationResponse;
import com.beam.scooter.Service.LocationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationResourceController {

	@Autowired
	private LocationService service;

	@ApiOperation(
			value = "API to get the Scooter locations based on the lat long and radius",
			notes = "API to get the Scooter locations based on the lat long and radius")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully sent notification")})
	@RequestMapping(value = "/{lat}/{long}/{distance}", method = RequestMethod.GET)
	public  List<LocationResponse> getLocations(
			@PathVariable("lat") @ApiParam(value = "location latitude and must be double value") Double latitude,
			@PathVariable("long") @ApiParam(value = "location longitude and must be double value")Double longitude,
			@PathVariable("distance") @ApiParam(value = "radius in which scooter needs to be search") double distance,
			@RequestParam(name = "limit",defaultValue = "10") @ApiParam(value = "max how many number of scooter needs to be search" ) Integer limit,
			@RequestParam(value = "name", required = false) @ApiParam(value = "title of the scooter you are searching and its a optional param" ) String name) {
		return service.getLocations(name, longitude, latitude,distance,limit);
	}

	@ApiOperation(
			value = "API to add Scooter lat long in database",
			notes = "API to add Scooter lat long in database")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully add the locations")})
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public  void addLocations(
			@RequestBody List<LocationEntry> entries) {
		service.addLocations(entries);


	}

	@ApiOperation(
			value = "API to get locatino and quantity to dropoff",
			notes = "API to get locatino and quantity to dropoff")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully get the location to drop off")})
	@RequestMapping(value = "/quantity",method = RequestMethod.POST)
	public  List<LocationQuantity> getDropOffLocationQuantity(
			@RequestBody DropOffLocationQuantityResponse dropOffLocationQuantityResponse) {
		return service.getLocationQuantity(dropOffLocationQuantityResponse.getLocationQuantities(),dropOffLocationQuantityResponse.getProximateDistance());
	}
}

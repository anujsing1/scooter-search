package com.beam.scooter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
@CompoundIndexes({
		@CompoundIndex(def = "{'location':'2dsphere'}", name = "2dsphere_index")
})
public class LocationEntity {
	@Id
	private String id;
	private String name;
	private GeoJsonPoint location;

	public LocationEntity(final String name, final GeoJsonPoint location) {
		this.name = name;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "LocationEntity{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", location=" + location +
				'}';
	}
}
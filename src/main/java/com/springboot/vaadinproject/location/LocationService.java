package com.springboot.vaadinproject.location;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations(String filterText){
        if(filterText == null || filterText == ""){
            System.out.println(locationRepository.findAll());
            return (List<Location>) locationRepository.findAll();
        }else{
            System.out.println(locationRepository.search(filterText));
            return locationRepository.search(filterText);
        }
    }


    public long countLocations(){
        return locationRepository.count(); 
    }

    public void deleteLocation(Location location){
        locationRepository.delete(location);
    }

    public void saveLocation(Location location){
        if(location == null){
            System.err.println("Location is Null");
            return;
        }
        try{
            System.out.println("Location to be counted: " + location);
            locationRepository.save(location);
        }catch(Exception e){
            System.out.println("Could not insert new row due to error: " + e);
        }
        
    }    
}

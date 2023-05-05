package com.joranbergfeld.airportsystem.plane;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlaneService {
    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Plane getPlaneById(Long id) {
        Optional<Plane> optionalPlane = planeRepository.findById(id);
        return optionalPlane.orElseThrow(() -> new ResourceNotFoundException("Plane not found with id " + id));
    }

    public List<Plane> getAllActivePlanes() {
        return planeRepository.findAllByActiveTrue();
    }

    public Plane createPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public Plane updatePlane(Long id, Plane updatedPlane) {
        return planeRepository.findById(id)
                .map(plane -> {
                    plane.setName(updatedPlane.getName());
                    plane.setSize(updatedPlane.getSize());
                    plane.setCrewRequired(updatedPlane.getCrewRequired());
                    plane.setPassengerCapacity(updatedPlane.getPassengerCapacity());
                    return planeRepository.save(plane);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id " + id));
    }

    public Plane deletePlane(Long id) {
        return planeRepository.findById(id)
                .map(plane -> {
                    plane.setActive(false);
                    return planeRepository.save(plane);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id " + id));
    }
}

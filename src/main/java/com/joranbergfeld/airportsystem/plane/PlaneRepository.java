package com.joranbergfeld.airportsystem.plane;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    List<Plane> findAllByActiveTrue();
}

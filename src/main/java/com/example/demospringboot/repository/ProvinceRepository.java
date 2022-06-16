package com.example.demospringboot.repository;

import com.example.demospringboot.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
    Iterable<Province> findAllByNameContaining(String name);
}

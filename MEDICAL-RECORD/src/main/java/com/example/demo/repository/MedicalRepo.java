package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Medical;

public interface MedicalRepo extends JpaRepository<Medical,Integer>{

}

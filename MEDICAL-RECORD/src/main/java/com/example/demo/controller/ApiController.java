package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Medical;
import com.example.demo.repository.MedicalRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")

public class ApiController {

	@Autowired
	private MedicalRepo medicalRepository;
	
	// get all records
	@GetMapping("/employees")
	public List<Medical> getAllRecords(){
		return medicalRepository.findAll();
	}		
	
	// create record
	@PostMapping("/employees")
	public Medical createRecord(@RequestBody Medical record) {
		return medicalRepository.save(record);
	}
	
	// get record by id 
	@GetMapping("/employees/{id}")
	public ResponseEntity<Medical> getRecordById(@PathVariable int id) {
		Medical record = medicalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		return ResponseEntity.ok(record);
	}
	
	// update record
	@PutMapping("/employees/{id}")
	public ResponseEntity<Medical> updateRecord(@PathVariable int id, @RequestBody Medical medicalDetails){
		Medical record = medicalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		
		record.setName(medicalDetails.getName());
		record.setEmailId(medicalDetails.getEmailId());
		record.setImg(medicalDetails.getImg());
		record.setGender(medicalDetails.getGender());
		record.setAge(medicalDetails.getAge());
		
		Medical updatedRecord = medicalRepository.save(record);
		return ResponseEntity.ok(updatedRecord);
	}
	
	// delete record
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRecord(@PathVariable int id){
		Medical record = medicalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
		
		medicalRepository.delete(record);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
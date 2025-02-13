package com.hostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.entity.Hostel;
import com.hostel.service.HostelAdminService;

@RestController
@RequestMapping("/restTemplate")
public class HostelAdminController {

	@Autowired
	private HostelAdminService hostelAdminService;
	
	@PostMapping("/saveHostel")
	public ResponseEntity<Hostel> addHostel(@RequestBody Hostel hostel){
		return hostelAdminService.addHostel(hostel);
	}
	
	@PostMapping("/saveMultipleHostels")
	public ResponseEntity<Hostel> addMultipleHostel(@RequestBody List<Hostel> hostels) {
		return hostelAdminService.addMultipleHostel(hostels);
	}
	
	@GetMapping("/getHostelById/{id}")
	public Hostel getHostelById(Integer id) {
		return hostelAdminService.getHostelById(id);
	}
	
	@GetMapping("/getAllHostels")
	public ResponseEntity<Hostel> getAllHostels() {
		return hostelAdminService.getAllHostels();
	}
	
	@GetMapping("/getAllHostelsByExchangeMethod")
	public ResponseEntity<Hostel> getAllHostelsByExchangeMethod() {
		return hostelAdminService.getAllHostelsByExchangeMethod();
	}
	
	@PutMapping("/editHostel/{id}")
	public void updateHostel(@PathVariable Integer id, @RequestBody Hostel hostel) {
		 hostelAdminService.updateHostel(id, hostel);
	}
	
	@PatchMapping("/editHostelFields/{id}")
	public Hostel updateHostelFields(@PathVariable Integer id, @RequestBody Hostel hostel) {
		return hostelAdminService.updateHostelFields(id, hostel);
	}
	
	@DeleteMapping("/deleteHostel/{id}")
	public void deleteHostel(@PathVariable Integer id) {
		hostelAdminService.deleteHostel(id);
	}
	
}

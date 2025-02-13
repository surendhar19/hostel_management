package com.hostel.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.entity.Hostel;
import com.hostel.exception.HostelEmptyException;
import com.hostel.exception.UserNotFoundException;
import com.hostel.service.HostelService;

@RestController
public class HostelController {
	
	Logger log = LoggerFactory.getLogger(HostelController.class);
	
	@Autowired
	private HostelService hostelService;
	
	@PostMapping("/addHostel")
	public Hostel addHostel(@RequestBody @Valid Hostel hostel) {
			log.info("inside add Hostel method");
			log.debug("inside add Hostel method");
			return hostelService.addHostel(hostel);
	}
	
	@PostMapping("/addMultipleHostels")
	public List<Hostel> addMultipleHostels(@RequestBody @Valid List<Hostel> hostels) {
		log.info("Inside add multiple hosels method");
		return hostelService.addHostels(hostels);
	}
	
	@GetMapping("/getHostelById/{hostelId}")
	public Hostel getHostelById(@PathVariable Integer hostelId) throws UserNotFoundException {
		log.debug("Inside get Hostel By Id method");
		return hostelService.getHostelById(hostelId);
	}

	@GetMapping("/getHostel")
	public List<Hostel> getHostel(@RequestParam (required=false) Integer hostelId,@RequestParam (required=false) String hostelName) throws HostelEmptyException {
		log.debug("Inside get Hostel method");
		return hostelService.getHostel(hostelId, hostelName);
	}
	
	@GetMapping("/getAllHostels")
	public List<Hostel> getAllHostels() throws HostelEmptyException {
		log.info("Inside get all hostels method");
		return hostelService.getAllHostels();
	}
	
	@PutMapping("/updateHostel/{id}")
	public Hostel updateHostel(@PathVariable Integer id, @RequestBody @Valid Hostel hostel) {
		log.info("Inside update Hostel by id method");
		return hostelService.updateHostel(id, hostel);
	}
	
	@PatchMapping("/editHostelDetails/{id}")
	public Hostel updateHostelFields(@PathVariable Integer id,@RequestBody @Valid Map<String, Object> hostelFields) {
		log.info("Inside editHostel Details method");
		return hostelService.updateHostelFields(id, hostelFields);
	}
	
	@DeleteMapping("/deleteHostel/{id}")
	public String deleteHostel(@PathVariable Integer id) {
		log.info("inside deletehostel by id");
		return hostelService.removeHostel(id);
	}
	
}

package com.hostel.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.hostel.entity.Hostel;
import com.hostel.exception.HostelEmptyException;
import com.hostel.exception.UserNotFoundException;
import com.hostel.repository.HostelRepository;

@Service
public class HostelService {
	
	Logger log = LoggerFactory.getLogger(HostelService.class);
	
	@Autowired
	private HostelRepository hostelRepository;
	
	public Hostel addHostel(Hostel hostel) {
		return hostelRepository.save(hostel);
	}
	
	public List<Hostel> addHostels(List<Hostel> hostels) {
		return hostelRepository.saveAll(hostels);
	}
	
	public Hostel getHostelById(Integer hostelId) throws UserNotFoundException {
		log.info("Inside get Hostel By Id");
		Hostel hostel= hostelRepository.findByHostelId(hostelId);
		if(hostel!=null) {
			log.info("Inside getHostelById if statement");
			return hostel;
		}
		else {
			log.error("Hostel doesn't exist method", UserNotFoundException.class);
			throw new UserNotFoundException("Hostel with ID:"+ hostelId + " doesn't exists");
		}
	}
	
	public List<Hostel> getHostel(Integer hostelId, String hostelName) throws HostelEmptyException {
		if(hostelId!=null && hostelName!=null) {
			Hostel hostel = hostelRepository.findByHostelIdAndName(hostelId, hostelName);
			if(hostel!=null) {
				List<Hostel> hostels = new ArrayList<>();
				hostels.add(hostel);
				return hostels;
			}
			else 
				throw new HostelEmptyException("Hostel with ID:" + hostelId + " and Name: " + hostelName + " doesn't exist in the application");		
		}
		else if(hostelId!=null && hostelName==null) {
			Hostel hostel = hostelRepository.findByHostelId(hostelId);
			List<Hostel> hostels = new ArrayList<>();
			hostels.add(hostel);
			return hostels;
		}
		else if(hostelId==null && hostelName!=null)
			return hostelRepository.findByHostelName(hostelName);
		else
			throw new HostelEmptyException("Hostel with ID:" + hostelId + " and " + hostelName + " doesn't exist in the application");
	}
	
	public List<Hostel> getAllHostels() throws HostelEmptyException {
		List<Hostel> hostels = hostelRepository.findAll();
		if(hostels.isEmpty())
			throw new HostelEmptyException("No hostels data present in the application");
		else return hostels;
			
	} 
	
	public Hostel updateHostel(Integer id, Hostel hostel){
		Hostel existingHostel = hostelRepository.findByHostelId(id);
		if(existingHostel!=null) {
			existingHostel.setHostelId(hostel.getHostelId());
			existingHostel.setHostelName(hostel.getHostelName());
			existingHostel.setWardenName(hostel.getWardenName());
			existingHostel.setNoOfRooms(hostel.getNoOfRooms());
			existingHostel.setFeeDetails(hostel.getFeeDetails());
			existingHostel.setMobileNumber(hostel.getMobileNumber());
			existingHostel.setOccupied(hostel.getOccupied());
			existingHostel.setVacancy(hostel.getVacancy());
		}
		return existingHostel;
	}
	public Hostel updateHostelFields(Integer id, Map<String, Object> fields){
		Optional<Hostel> existingHostel = hostelRepository.findById(id);
		if(existingHostel.isPresent()) {
		fields.forEach((key,value) -> {
			Field field = ReflectionUtils.findField(Hostel.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field,existingHostel.get(),value);
		});
		return hostelRepository.save(existingHostel.get());
		}
		return null;
	}
	
	public String removeHostel(Integer id) {
		Hostel existingHostel = hostelRepository.findByHostelId(id);
		if(existingHostel!=null) {
			hostelRepository.delete(existingHostel);
			return "Hostel with Id:" + id + " deleted successfully";
		}
		else
			return "Hostel doesn't exists";
	}
	
}

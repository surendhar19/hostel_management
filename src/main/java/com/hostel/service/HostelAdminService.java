package com.hostel.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hostel.entity.Hostel;

@Service
public class HostelAdminService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	private static final String add_hostel_url = "http://localhost:9090/addHostel";
	private static final String add_multiple_hostel_url = "http://localhost:9090/addMultipleHostels";
	private static final String get_hostelById_url = "http://localhost:9090/getHostelById/{hostelId}";
	private static final String get_all_hostels_url = "http://localhost:9090/getAllHostels";
	private static final String update_hostel_url = "http://localhost:9090/updateHostel/{id}";
	private static final String update_hostel_details_url = "http://localhost:9090/editHostelDetails/{id}";
	private static final String delete_hostel_url = "http://localhost:9090/deleteHostel/{id}";
	
	public ResponseEntity<Hostel> addHostel(Hostel hostel) {
		return restTemplate.postForEntity(add_hostel_url, hostel, Hostel.class);
	}
	
	public ResponseEntity<Hostel> addMultipleHostel(List<Hostel> hostel) {
		return restTemplate.postForEntity(add_multiple_hostel_url, hostel, Hostel.class);
		//return.restTemplate.
	}
	
	public Hostel getHostelById(Integer id) {
		Map<String, Integer> map= new HashMap<String, Integer>();
		map.put("hostelId", id);
		return restTemplate.getForObject(get_hostelById_url, Hostel.class, id);
	}
	
	public ResponseEntity<Hostel> getAllHostels() {
		return restTemplate.getForEntity(get_all_hostels_url, Hostel.class);
	}
	
	public ResponseEntity<Hostel> getAllHostelsByExchangeMethod() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return restTemplate.exchange(get_all_hostels_url, HttpMethod.GET, entity, Hostel.class);
	}
	
	public void updateHostel(Integer id, Hostel hostel) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		restTemplate.put(update_hostel_url, hostel, id);
	}
	

	public Hostel updateHostelFields(Integer id, Hostel hostel) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		return restTemplate.patchForObject(update_hostel_details_url, hostel, Hostel.class, id);
	}
	
	public void deleteHostel(Integer id) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		restTemplate.delete(delete_hostel_url, id);
	}
	
}

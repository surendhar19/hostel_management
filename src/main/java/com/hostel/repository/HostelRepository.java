package com.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hostel.entity.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer>{

	@Query(value= "SELECT * FROM HOSTEL_TBL ht WHERE ht.HOSTEL_ID=:hostelId", nativeQuery=true)
	Hostel findByHostelId(Integer hostelId);

	@Query(value= "SELECT * FROM HOSTEL_TBL ht WHERE ht.Hostel_Id=:hostelId AND ht.Hostel_Name=:hostelName", nativeQuery=true)
	Hostel findByHostelIdAndName(Integer hostelId, String hostelName);

	@Query(value= "SELECT * FROM HOSTEL_TBL ht WHERE ht.HOSTEL_NAME=:hostelName", nativeQuery=true)
	List<Hostel> findByHostelName(String hostelName);

}

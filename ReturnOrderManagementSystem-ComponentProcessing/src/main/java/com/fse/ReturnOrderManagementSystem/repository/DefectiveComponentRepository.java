package com.fse.ReturnOrderManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.ReturnOrderManagementSystem.entity.DefectiveComponentDetail;

import java.util.List;

public interface DefectiveComponentRepository extends JpaRepository<DefectiveComponentDetail, Long> {
	public List<DefectiveComponentDetail> findAllByUserName(String userName);
}

package com.bugtracking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtracking.bean.Bug;
import com.bugtracking.enums.bugstatus;

@Repository("br")
public interface IBugRepository extends JpaRepository<Bug, Long> {

	List<Bug> findByBugStatus(bugstatus bugstatus);

}
package com.store.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entity.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

}

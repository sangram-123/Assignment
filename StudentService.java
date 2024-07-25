package com.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entity.StudentEntity;
import com.store.repo.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;
	public List<StudentEntity> getAllStudents() {
        return repo.findAll();
    }

    public Optional<StudentEntity> getStudentById(Integer id) {
        return repo.findById(id);
    }

    public StudentEntity saveStudent(StudentEntity student) {
        return repo.save(student);
    }
    public void deleteStudent(Integer id) {
        repo.deleteById(id);
    }

}

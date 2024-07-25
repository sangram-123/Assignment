package com.store.controllor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.store.entity.StudentEntity;
import com.store.service.StudentService;

@RestController
public class StudentControllor {
	@Autowired
	private StudentService ss;
	@GetMapping("/api/students")
    public List<StudentEntity> getAllStudents() {
        return ss.getAllStudents();
    }
	@GetMapping("/api/students/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Integer id) {
        Optional<StudentEntity> student = ss.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	@PostMapping("/api/students/add")
    public ResponseEntity<StudentEntity> createStudent(@RequestParam("name") String name,
                                                 @RequestParam("stream") String stream,
                                                 @RequestParam("marks") Double marks,
                                                 @RequestParam("image") MultipartFile image) throws IOException {
        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setStream(stream);
        student.setMarks(marks);
        student.setImage(image.getBytes());

        StudentEntity savedStudent = ss.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}
	
	@PutMapping("/api/students/update/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Integer id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("stream") String stream,
                                                 @RequestParam("marks") Double marks,
                                                 @RequestParam("image") MultipartFile image) throws IOException {
        Optional<StudentEntity> optionalStudent = ss.getStudentById(id);
        if (optionalStudent.isPresent()) {
            StudentEntity student = optionalStudent.get();
            student.setName(name);
            student.setStream(stream);
            student.setMarks(marks);
            student.setImage(image.getBytes());

            StudentEntity updatedStudent = ss.saveStudent(student);
            return ResponseEntity.ok(updatedStudent);} else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/api/students/delete/{id}")
        public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
            ss.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
	
	
	
	
}

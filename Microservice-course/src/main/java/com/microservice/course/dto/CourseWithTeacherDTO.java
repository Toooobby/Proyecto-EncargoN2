package com.microservice.course.dto;

import com.microservice.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseWithTeacherDTO {
    private Course course;
    private TeacherDTO teacher;
}

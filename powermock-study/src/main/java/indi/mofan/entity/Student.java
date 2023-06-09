package indi.mofan.entity;

import lombok.Getter;

/**
 * @author mofan
 * @date 2021/4/12 14:41
 */
@Getter
public class Student {
    private String studentName;
    private String gender;
    private Integer age;
    private University school;

    public Student(String studentName, String gender, Integer age) {
        University university = new University();
        university.setUniversityName("xxx大学");
        this.school = university;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
    }
}

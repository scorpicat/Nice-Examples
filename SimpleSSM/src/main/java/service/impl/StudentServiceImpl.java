package service.impl;

import dao.StudentDao;
import domain.Student;
import org.springframework.stereotype.Service;
import service.StudentService;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao sutdentDao;

    @Override
    public int addStudent(Student student) {
        int nums = sutdentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> findStudent() {
        return sutdentDao.selectStudents();
    }
}

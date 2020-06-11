package com.example.service;

import com.example.dao.EmpDAO;
import com.example.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpDAO empDAO; //调用dao中的方法进而访问到数据库

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)  //没必要每次都开启事务
    public List<Emp> findAll() {
        return empDAO.findAll();
    }

    @Override
    public void save(Emp emp) {
        emp.setId(UUID.randomUUID().toString());
        empDAO.save(emp);
    }

    @Override
    public void delete(String id) {
        empDAO.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Emp find(String id) {
        return empDAO.find(id);
    }

    @Override
    public void update(Emp emp) {
        empDAO.update(emp);
    }
}

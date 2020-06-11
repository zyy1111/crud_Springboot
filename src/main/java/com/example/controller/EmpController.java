package com.example.controller;

import com.example.entity.Emp;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/findAll")  //跳转到/emp/findAll
    public String findAll(Model model) {
        //在控制器中，数据会存放到Model对象中，当需要生成HTML的时候，模板引擎会根据名字来定位数据
        List<Emp> emps = empService.findAll();
        model.addAttribute("emps", emps);
        return "ems/emplist";  //找resources/ems/emplist.html页面
    }

    @PostMapping("/save")
    public String save(Emp emp) {
        empService.save(emp);
        return "redirect:/emp/findAll";  //跳转到控制器，由控制器再去加载模板视图
    }

    @GetMapping("/delete")
    public String delete(String id) {
        empService.delete(id);  //调业务->调DAO->操作数据库
        return "redirect:/emp/findAll";
    }

    //根据id查询员工并返回填好信息的页面等待修改
    @GetMapping("/find")
    public String find(String id, Model model) {
        Emp emp = empService.find(id);
        model.addAttribute("emp", emp);
        return "/ems/updateEmp";
    }

    @PostMapping("/update")  //要和前端写的路径对应上
    public String update(Emp emp) {
        empService.update(emp);
        return "redirect:/emp/findAll";
    }
}

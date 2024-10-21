package com.mypack.Th1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class StuCont {
	@GetMapping("/hello")
	public String test1(Model m)
	{
		Student stu=new Student(1,"abcdef");
		m.addAttribute("student", stu);
		return "dis";
	}
}

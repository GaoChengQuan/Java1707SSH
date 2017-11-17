package com.situ.ssh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Student;
import com.situ.ssh.service.IStudentService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student>{
	@Autowired
	private IStudentService studentService;
	
	public String pageQuery() {
		studentService.pageQuery(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}

}

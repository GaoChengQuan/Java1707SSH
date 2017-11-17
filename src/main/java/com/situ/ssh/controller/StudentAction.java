package com.situ.ssh.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
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
	
	private String ids;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String pageQuery() {
		studentService.pageQuery(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}

	public String deleteBatch() {
		ServerResponse serverResponse = null;
		if (studentService.deleteBatch(ids)) {
			serverResponse = ServerResponse.createSuccess("删除成功");
		} else {
			serverResponse = ServerResponse.createError("删除失败");
		}
		obj2Json(serverResponse);
		return NONE;
	}
}

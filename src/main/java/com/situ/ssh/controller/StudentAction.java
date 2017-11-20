package com.situ.ssh.controller;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Student;
import com.situ.ssh.service.IStudentService;

//Action交给Spring容器管理，Spring默认创建实例是singleton的，但是Struts2每次访问Action都会创建一个新的Action，
//其实就要求这个Action是多例的，所以要加@Scope("prototype")，告诉Spring Action的实例是多利的。
@Controller
@Scope("prototype") 
public class StudentAction extends BaseAction<Student>{
	@Autowired
	private IStudentService studentService;
	
	public String pageQuery() {
		//在DetachedCriteria中封装离线查询条件
		String name = model.getName();
		if (StringUtils.isNotEmpty(name)) {
			////添加过滤条件，根据名字关键字模糊查询
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		Integer age = model.getAge();
		if (age != null) {
			////添加过滤条件，根据年龄查询
			detachedCriteria.add(Restrictions.eq("age", age));
		}
		
		studentService.pageQuery(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;//The action execution was successful but do not show a view. 
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

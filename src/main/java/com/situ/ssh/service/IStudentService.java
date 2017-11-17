package com.situ.ssh.service;

import com.situ.ssh.util.PageBean;

public interface IStudentService {
	public void pageQuery(PageBean pageBean);

	public boolean deleteBatch(String ids);
}

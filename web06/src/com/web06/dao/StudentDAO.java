package com.web06.dao;

import com.web06.entity.Page;

public interface StudentDAO {
public void findByPage(Page page);
public Integer countStudent();
}

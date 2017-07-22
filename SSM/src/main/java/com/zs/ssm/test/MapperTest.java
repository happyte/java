package com.zs.ssm.test;


import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.ssm.bean.Department;
import com.zs.ssm.bean.Employee;
import com.zs.ssm.dao.DepartmentMapper;
import com.zs.ssm.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	//sqlSession是个接口，SqlSessionTemplate是它的实现类
	@Autowired
	SqlSession sqlSession;

	@Test
	public void test() {
		System.out.println("departmentMapper:"+departmentMapper);
		System.out.println("sqlSession:"+sqlSession);
		//插入部门成功
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "测试部"));
		//插入员工成功
		//employeeMapper.insertSelective(new Employee(null, "张树", "F", "zs511129@163.com", 2));
		EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<10;i++){
			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
			employeeMapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 3));
		}
		
	}

}

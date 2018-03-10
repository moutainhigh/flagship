package com.hzcf.flagship.dao;

import java.util.List;

import com.hzcf.flagship.model.Employee;

public interface EmployeeMapper extends BaseMapper<Employee>{
    
    /**
	 * 
	 * Description: 根据用户名查找用户
	 *
	 * @param username 
	 * @return String 
	 * @Author lijie
	 * @Create Date: 2013-11-4 下午5:10:22
	 */
	public Employee findByUsername(String username);
	
	 /**
	  * 
	  * Description: 验证用户
	  *
	  * @param 
	  * @return Employee
	  * @throws 
	  * @Author ydw
	  * Create Date: 2014-12-1 下午05:48:53
	  */
    public Employee checkUsers(Employee employee);
    
    /**
     * 查询所有管理员的手机号
     * @return 格式:mobile1,mobile2....
     */
    public String getMobilesOfAdmin();
    
    /**
     * 查询所有普通的后台用户(account_type为0)
     */
    public List<Employee> findAllNormalEmployee();
}
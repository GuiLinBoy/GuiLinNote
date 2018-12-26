package com.BoHong.customer.Action;

import java.util.List;

import com.BoHong.customer.dao.UsersDAO;
import com.BoHong.customer.vo.Optometryinfo;
import com.BoHong.customer.vo.Users;
import com.BoHong.util.BaseSupportAction;
import com.BoHong.util.HibernateDAO;

public class UserAction extends BaseSupportAction{
	UsersDAO usersDAO=new UsersDAO();
	HibernateDAO hibernateDAO=new HibernateDAO();
	private Integer id;
	Users users;//客户
	List<Users> usersList;//客户列表
	List<Optometryinfo> itemList;//项目
	String startTime;
	String endTime;
	String name;
	public String ShowCustomer() {
		try {
			
			String sqlString="SELECT * FROM optometryinfo where delState = 0 ORDER BY id DESC ";
			itemList=hibernateDAO.findBySqlStart(sqlString, Optometryinfo.class);
			usersList=usersDAO.findUsers(startTime, endTime, name);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
			// TODO: handle exception
		}
	}
	public String DeleteUser() {
		try {
			users=(Users) hibernateDAO.findById(Users.class, id);
			users.setDelState(1);
			hibernateDAO.update(users);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
			// TODO: handle exception
		}
	}
	public List<Users> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public List<Optometryinfo> getItemList() {
		return itemList;
	}
	public void setItemList(List<Optometryinfo> itemList) {
		this.itemList = itemList;
	}
}

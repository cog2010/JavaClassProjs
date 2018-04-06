package com.tblanton.meetingroom.dao;

import com.tblanton.meetingroom.model.User;

public interface UserDao {
	public void addUserToDb(User user);
	public void updateUserInDb(User user);
	public User getUserFromDb(String userId);
	public void deleteUserFromDb(User user);
}

package day02;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	private List<User> users = new ArrayList<>();

	public void addUser(User user) {
		users.add(user);
	}

	public User findUserByName(String name) {
		for (User user: users) {
			if (user.getUserName().equals(name)) {
				return user;
			}
		}
		throw new IllegalArgumentException("Cannot find user with username:" + name);
	}
}

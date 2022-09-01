package day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

	UserRepository userRepository = new UserRepository();

	@Test
	void testFindUserByUsername(){
		userRepository.addUser(new User("jane", "jane@email.hu"));
		userRepository.addUser(new User("john", "john@email.hu"));
		userRepository.addUser(new User("jack", "jack@email.hu"));
		userRepository.addUser(new User("sally", "sally@email.hu"));
		User res = userRepository.findUserByName("jack");
		assertEquals("jack@email.hu", res.getEmail());
	}

	@Test
	void testFindUserByUserNameNotFound() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> userRepository.findUserByName("johnny"),
				"Cannot find user with username:johnny");
	}

}
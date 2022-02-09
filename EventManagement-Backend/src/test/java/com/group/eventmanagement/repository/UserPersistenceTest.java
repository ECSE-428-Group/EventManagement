package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserPersistenceTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadUserPersistence() {

        // User
        User user = new User();
        TestData.setUser(user, 1);
        userRepository.save(user);

        User noVax_user = new User();
        TestData.setUser(noVax_user, 3);
        userRepository.save(noVax_user);

        user = null;
        user = userRepository.findUserByUsername(TestData.user1Username);

        noVax_user = null;
        noVax_user = userRepository.findUserByUsername(TestData.user3Username);

        assertNotNull(user);
        assertNotNull(noVax_user);
        assertTrue(userRepository.existsByUsername(TestData.user3Username));
        assertTrue(userRepository.existsByUsername(TestData.user1Username));
        assertEquals(2, userRepository.findAll().size());
    }

	@Test
	public void testExistsUserByUsername() {
		// Save a user in repository
		User user = new User();
		TestData.setUser(user, 1);
		String username = user.getUsername();
		userRepository.save(user);

		// Check if user exists in database
		assertTrue(userRepository.existsByUsername(username));
	}

	@Test
	public void testNonExistentUserByUsername() {
		User nonExistentUser = new User();
		TestData.setUser(nonExistentUser, 2);
		String nonExistentUsername = nonExistentUser.getUsername();

		assertFalse(userRepository.existsByUsername(nonExistentUsername));
	}

	@Test
	public void testFindAllUsers() {
		// Save multiple users in repository
		User user1 = new User();
		TestData.setUser(user1, 1);
		User user2 = new User();
		TestData.setUser(user2, 2);
		User user3 = new User();
		TestData.setUser(user3, 3);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		int count = userRepository.findAll().size();
		assertEquals(3, count);
	}

}

package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TagPersistence {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void clearDatabase() {
        tagRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadTagPersistence() {



        // User
        String username = "saba";
        String firstname = "Saba";
        String lastname = "Fathi";
        String email = "test@email.com";
        String password = "test1234";
        Timestamp birthday = new Timestamp(1644162880059L);
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);

        userRepository.save(user);

        // Tag
        String name = "TestTag";
        String description = "TestDescription";
        List<User> userList = new ArrayList<User>();
        Tag tag = new Tag();

        userList.add(user);
        tag.setDescription(description);
        tag.setName(name);
        tag.setUsers(userList);

        tagRepository.save(tag);

        tag = null;
        tag = tagRepository.findByName(name);

        user = null;
        user = tagRepository.findByName(name).getUsers().get(0);

        assertNotNull(tag);
        assertEquals(name, tag.getName());
        assertNotNull(user);
        assertEquals(username, tag.getUsers().get(0).getUsername());

    }

}

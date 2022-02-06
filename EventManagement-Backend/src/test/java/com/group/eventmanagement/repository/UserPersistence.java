package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
public class UserPersistence {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @AfterEach
    public void clearDatabase() {
        tagRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @Transactional
    public void testAndLoadUserPersistence() {

        // Tag
        String name = "TestTag";
        String description = "TestDescription";
        List<User> userList = new ArrayList<User>();
        Tag tag = new Tag();

        tag.setDescription(description);
        tag.setName(name);

        tagRepository.save(tag);

        // Vax User
        String username = "saba";
        String firstname = "Saba";
        String lastname = "Fathi";
        String email = "test@email.com";
        String password = "test1234";
        Timestamp birthday = new Timestamp(1644162880059L);
        List<Tag> tagList = new ArrayList<Tag>();
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setVaccinationStatus(true);
        tagList.add(tag);
        user.setTags(tagList);

        // Non Vax User
        String noVax_username = "saba";
        String noVax_firstname = "Saba";
        String noVax_lastname = "Fathi";
        String noVax_email = "test@email.com";
        String noVax_password = "test1234";
        Timestamp noVax_birthday = new Timestamp(1644162880059L);
        List<Tag> noVax_tagList = new ArrayList<Tag>();
        User noVax_user = new User();

        noVax_user.setUsername(noVax_username);
        noVax_user.setPassword(noVax_password);
        noVax_user.setFirstName(noVax_firstname);
        noVax_user.setLastName(noVax_lastname);
        noVax_user.setEmail(noVax_email);
        noVax_user.setBirthday(noVax_birthday);
        noVax_user.setVaccinationStatus(false);
        noVax_tagList.add(tag);
        noVax_user.setTags(noVax_tagList);

        userRepository.save(user);
        userRepository.save(noVax_user);

        userList.add(user);
        userList.add(noVax_user);
        tag.setUsers(userList);

        tagRepository.save(tag);

        user = null;
        user = userRepository.findUserByUsername(username);

        tag = null;
        tag = user.getTags().get(0);

        assertNotNull(user);
        assertNotNull(tag);
        assertTrue(tagRepository.existsByName(name));
        assertEquals(2, tag.getUsers().size());
    }

}

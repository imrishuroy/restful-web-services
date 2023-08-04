package com.rishu.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List users = new ArrayList();

    private static int userCount = 0;

    static {
        users.add(new User(userCount++, "Prince", LocalDate.of(2001, 4, 24)));
        users.add(new User(userCount++, "Rishu", LocalDate.of(2000, 9, 5)));
        users.add(new User(userCount++, "Roy", LocalDate.of(2025, 7, 1)));

    }
    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return (User) users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save (User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }





}

package springsecurity.sample.radha.basic.service;

import springsecurity.sample.radha.basic.model.User;

import java.util.List;

public interface UserService {
    User find(long id);

    User findByName(String name);

    void save(User user);

    void update(User user);

    void delete(long id);

    List<User> findAll();

    boolean isUserExist(User user);
}

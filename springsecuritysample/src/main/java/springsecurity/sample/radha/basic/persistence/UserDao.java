package springsecurity.sample.radha.basic.persistence;

import springsecurity.sample.radha.basic.model.User;

import java.util.List;

public interface UserDao {
    void insert(User user);

    void update(User user);

    void delete(long id);

    User find(long id);

    User findByName(String name);

    List<User> findAll();
}

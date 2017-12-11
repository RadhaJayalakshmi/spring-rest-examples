package springsecurity.sample.radha.basic.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springsecurity.sample.radha.basic.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component("userDao")
public class UserDaoImpl implements UserDao {
    @PersistenceContext //Default PersistenceContext used as there is only one
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insert(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        entityManager.remove(find(id));
    }

    @Override
    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return (User) entityManager.createNativeQuery("select * from test_users where NAME = ?", User.class).setParameter(1, name).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createNativeQuery("select * from test_users", User.class).getResultList();
    }
}

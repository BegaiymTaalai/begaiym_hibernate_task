package peaksoft.dao;

import peaksoft.model.User;

import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User users);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}

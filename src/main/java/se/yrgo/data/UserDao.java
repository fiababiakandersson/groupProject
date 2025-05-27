package se.yrgo.data;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserDao {
    public List<User> allUsers();

    public User findUserById(int id) throws UserNotFoundException;

    public void createUser(User user);
}

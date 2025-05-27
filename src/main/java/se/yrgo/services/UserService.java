package se.yrgo.services;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int id) throws UserNotFoundException;

    public void addUser(User user);
}
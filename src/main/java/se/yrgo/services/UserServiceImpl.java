package se.yrgo.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        try {
            return userDao.findUserById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }

    @Override
    public void addUser(User user) {
        userDao.createUser(user);
    }
}
package se.yrgo.data;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserDao {
    public User findUserById(int id) throws UserNotFoundException;

}

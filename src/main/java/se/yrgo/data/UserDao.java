package se.yrgo.data;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserDao {
    public AppUser findUserById(int id) throws UserNotFoundException;

    public void createUser(AppUser newUser);
}

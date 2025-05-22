package se.yrgo.services;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserService {
    public User getUserById(int id) throws UserNotFoundException;
}
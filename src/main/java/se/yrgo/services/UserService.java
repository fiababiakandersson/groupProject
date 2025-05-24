package se.yrgo.services;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface UserService {
    public AppUser getUserById(int id) throws UserNotFoundException;

    public void addUser(AppUser user);
}
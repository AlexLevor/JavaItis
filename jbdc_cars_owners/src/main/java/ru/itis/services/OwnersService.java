package ru.itis.services;

import ru.itis.models.Owner;
import java.util.List;

/**
 * Created by AlexLevor on 21.10.2016.
 */
public interface OwnersService {
    List<Owner> getAllOwner();
    Owner findOwner(int id);
    void updateOwner(Owner car);
    void addOwner(Owner car);
    void deleteOwner(int id);
}

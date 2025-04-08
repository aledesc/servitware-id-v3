package com.servitware.id;

import com.servitware.base.exception.InvalidNameException;
import com.servitware.base.exception.InvalidNumericIdException;
import com.servitware.base.impl.IntNamed;

public class LookUp extends IntNamed {
    public LookUp(int id, String name) throws InvalidNumericIdException, InvalidNameException {
        super(id, name);
    }

    public LookUp(String id, String name) throws InvalidNumericIdException, InvalidNameException {
        super(id, name);
    }
}

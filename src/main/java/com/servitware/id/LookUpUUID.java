package com.servitware.id;

import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidNameException;
import com.servitware.base.impl.StrNamed;

import java.util.UUID;

public class LookUpUUID extends StrNamed {

    public LookUpUUID(String name) throws InvalidAlphanumericIdException, InvalidNameException {
        this(UUID.randomUUID().toString(), name);
    }

    public LookUpUUID(String id, String name) throws InvalidAlphanumericIdException, InvalidNameException {
        super(id, name);
    }

}

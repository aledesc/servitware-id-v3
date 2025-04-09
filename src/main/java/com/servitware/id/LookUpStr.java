package com.servitware.id;

import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidNameException;
import com.servitware.base.impl.StrNamed;

public class LookUpStr extends StrNamed {

    public LookUpStr(String id, String name) throws InvalidAlphanumericIdException, InvalidNameException {
        super(id, name);
    }
}

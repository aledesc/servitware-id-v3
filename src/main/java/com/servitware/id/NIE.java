package com.servitware.id;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidStructuredAlphanumericIdException;
import com.servitware.base.impl.StructuredStrIded;
import com.servitware.id.exception.Invalid_NIE_IdException;


public class NIE implements StrId {

    private final static String REGEX_NIE = "^[XYZW][0-9]{8}$";
    private final _Nie nie;

    public NIE(String id) throws Invalid_NIE_IdException {
        try {
            nie = new _Nie(id,REGEX_NIE);
        } catch (InvalidAlphanumericIdException | InvalidStructuredAlphanumericIdException e) {
            throw new Invalid_NIE_IdException();
        }
    }

    @Override
    public String getId() {
        return nie.getId();
    }

    private static class _Nie extends StructuredStrIded {
        public _Nie(String id, String structure) throws InvalidAlphanumericIdException, InvalidStructuredAlphanumericIdException {
            super(id,structure);
        }
    }

}


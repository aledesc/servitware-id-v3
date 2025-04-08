package com.servitware.id;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.impl.StructuredStrIded;
import com.servitware.id.exception.Invalid_NIE_IdException;


public class NIE implements StrId {

    private final _NIE nie;

    public NIE(String id) throws Invalid_NIE_IdException {
        try {
            nie = new _NIE(id);
        } catch (InvalidAlphanumericIdException e) {
            throw new Invalid_NIE_IdException();
        }
    }

    @Override
    public String getId() {
        return nie.getId();
    }

    private static class _NIE extends StructuredStrIded {

        private final static String REGEX_NIE = "^[XYZW][0-9]{8}$";

        public _NIE(String id) throws InvalidAlphanumericIdException {
            super(id);
        }

        public boolean hasAValidIdStructure() {
            return super.hasAValidIdStructure(REGEX_NIE);
        }
    }

}


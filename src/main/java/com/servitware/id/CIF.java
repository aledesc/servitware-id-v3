package com.servitware.id;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.impl.StructuredStrIded;
import com.servitware.id.exception.Invalid_CIF_IdException;
import com.servitware.id.exception.Invalid_NIE_IdException;


public class CIF implements StrId {

    private final _CIF cif;

    public CIF(String id) throws Invalid_CIF_IdException {
        try {
            cif = new _CIF(id);
        } catch (InvalidAlphanumericIdException e) {
            throw new Invalid_CIF_IdException();
        }
    }

    @Override
    public String getId() {
        return cif.getId();
    }

    private static class _CIF extends StructuredStrIded {

        private final static String REGEX_NIE = "^[XYZ][0-9]{7}[A-Z|0-9]$";

        public _CIF(String id) throws InvalidAlphanumericIdException {
            super(id);
        }

        public boolean hasAValidIdStructure() {
            return super.hasAValidIdStructure(REGEX_NIE);
        }
    }

}


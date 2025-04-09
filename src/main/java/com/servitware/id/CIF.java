package com.servitware.id;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidStructuredAlphanumericIdException;
import com.servitware.base.impl.StructuredStrIded;
import com.servitware.id.exception.Invalid_CIF_IdException;


public class CIF implements StrId {

    private final static String REGEX_CIF = "^[ABCDEFGHKLMNPQS]\\d{2}\\d{5}[0-9|A-Z]$";
    private final _Cif cif;

    public CIF(String id) throws Invalid_CIF_IdException {

        try {
            cif = new _Cif(id, REGEX_CIF);
        } catch (InvalidAlphanumericIdException | InvalidStructuredAlphanumericIdException e) {
            throw new Invalid_CIF_IdException();
        }
    }

    @Override
    public String getId() {
        return cif.getId();
    }

    private static class _Cif extends StructuredStrIded {
        public _Cif(String id, String structure) throws InvalidAlphanumericIdException, InvalidStructuredAlphanumericIdException {
            super(id, structure);
        }
    }

}


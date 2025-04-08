package com.servitware.base.impl;

import com.servitware.base.exception.InvalidAlphanumericIdException;

public class StructuredStrIded extends StrIded{

    public StructuredStrIded(String id) throws InvalidAlphanumericIdException {
        super(id);
    }

    public boolean hasAValidIdStructure(String structure) {
        return this.getId().matches(structure);
    }
}

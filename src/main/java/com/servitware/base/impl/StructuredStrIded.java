package com.servitware.base.impl;

import com.servitware.base.StructuredStrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidStructuredAlphanumericIdException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StructuredStrIded extends StrIded implements StructuredStrId {

    private static boolean hasAValidIdStructure(String id,String structure) {
        if(id==null || id.isBlank() ) {
            return false;
        }

        if(structure==null || structure.isBlank() ) {
            return false;
        }

        return id.matches(structure);
    }

    public StructuredStrIded(String id, String structure) throws InvalidAlphanumericIdException, InvalidStructuredAlphanumericIdException {

        super(id);

        if( !hasAValidIdStructure(id,structure) ) {
            throw new InvalidStructuredAlphanumericIdException();
        }
    }
}

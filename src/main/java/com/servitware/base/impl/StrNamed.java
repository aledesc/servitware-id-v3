package com.servitware.base.impl;


import com.servitware.base.Named;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.exception.InvalidNameException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class StrNamed extends StrIded implements Named {
	private final String name;

	public StrNamed(String id, String name) throws InvalidAlphanumericIdException, InvalidNameException {
		super( id );

		if( name == null || name.isEmpty() )
			throw new InvalidNameException();
		
		this.name = name;
	}
}

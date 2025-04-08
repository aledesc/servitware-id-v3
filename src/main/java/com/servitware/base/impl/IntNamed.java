package com.servitware.base.impl;

import com.servitware.base.Named;
import com.servitware.base.exception.InvalidNameException;
import com.servitware.base.exception.InvalidNumericIdException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class IntNamed extends IntIded implements Named
{
	private final String name;

	public IntNamed(int id, String name) throws InvalidNumericIdException, InvalidNameException
	{
		super(id);
		
		if( name == null || name.isBlank() )
			throw new InvalidNameException();
		
		this.name = name;
	}

	public IntNamed(String id, String name) throws InvalidNumericIdException, InvalidNameException
	{
		this( Integer.parseInt(id), name);
	}
}

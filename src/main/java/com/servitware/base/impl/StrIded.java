package com.servitware.base.impl;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public abstract class StrIded implements StrId
{
	private final String id;

	public StrIded(String id) throws InvalidAlphanumericIdException
	{
		if( id==null || id.isBlank())
			throw new InvalidAlphanumericIdException();

		this.id = id;
	}
}

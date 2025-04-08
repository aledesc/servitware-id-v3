package com.servitware.base.impl;

import com.servitware.base.exception.InvalidNumericIdException;
import com.servitware.base.IntId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public abstract class IntIded implements IntId
{
	private final int id;

	public IntIded(int id) throws InvalidNumericIdException
	{
		if( id < MIN_ID )
			throw new InvalidNumericIdException();
		
		this.id = id;
	}

	public IntIded(String id) throws InvalidNumericIdException
	{
		if( id==null || id.isBlank())
			throw new InvalidNumericIdException();

		String _id = id.trim();

		if( !_id.matches(IntId.REGEX_POSITIVE_GT_ZERO))
			throw new InvalidNumericIdException();

        this.id = Integer.parseInt(_id);
	}
}
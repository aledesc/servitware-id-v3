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
public class StrNamed extends StrIded implements Named {
	private final String name;

	public StrNamed(String id, String name) throws InvalidAlphanumericIdException, InvalidNameException {
		super( id );

		if( name == null || name.isEmpty() )
			throw new InvalidNameException();
		
		this.name = name;
	}

	public static void main(String[] args) {


        try {

			StrNamed c = new StrNamed("asdsad"," pepe luis");
			System.out.println(c.toString());



        } catch (InvalidAlphanumericIdException | InvalidNameException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.servitware.base;

/**
 * @author ALedesma
 *
 */
public interface StrId
{
	String REGEX_ALPHA_NUMERIC_ID= "[a-zA-Z0-9_\\-\\.\\s]+";
	String INVALID_ALPHANUMERIC_EXCEPTION_MESSAGE = "Invalid alphanumeric Id, can just include: letters (a-z,A-Z), numbers (0-9), dash (-), underscore (_), space ( ), and dot (.)";

	String getId();
}

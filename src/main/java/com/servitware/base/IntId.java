/**
 * 
 */
package com.servitware.base;

/**
 * @author ALedesma
 *
 */
public interface IntId
{
    int MIN_ID = 1;

    String REGEX_POSITIVE_GT_ZERO= "^(0*[1-9]\\d*)$";
    String EXCEPTION_MESSAGE = "Invalid numeric Id, it has to be a positive integers (> ZERO)";

    int getId();
}

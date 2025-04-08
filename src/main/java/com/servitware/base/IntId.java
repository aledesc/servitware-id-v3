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

    String REGEX_POSITIVE_GT_ZERO= "(0|\\d{2,}|[1-9])";
    String EXCEPTION_MESSAGE = "Invalid numeric Id, it should be a positive integer > ZERO";

    int getId();
}

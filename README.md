# servitware-id-v3

This library grew out of work done to validate and curate the identification numbers of a long list of people whose personal information was not complete or accurate.

This library contains different interfaces, and classes to work with Entities whose identifier is an integer or a string of characters with or without structure.

Given the nature of the related entities, a design was decided using hierarchies keeping them as tight as possible, and finally, composition is also used. 

**The interfaces are:**
    
    - StrId
    - Named
    - StructuredId
    - IntId

The classes are:

    - StrIded
    - StrNamed
    - StructuredNamed
    - IntIded
    - IntNamed

Classes are also created to manage numbers of Spanish identification documents for national and foreign individuals and for individuals and legal entities:

    - DNI
    - NIE
    - CIF

**Error handling**

Classes throw exceptions in case of invalid identifiers, depending on the type, these are:

    - InvalidAlphanumericIdException
    - InvalidNameException
    - InvalidNumericIdException
    - InvalidStructuredAlphanumericIdException

Identifiers classes throw the following exceptions:

    - Invalid_CIF_IdException
    - Invalid_DNI_IdException
    - Invalid_NIE_IdException

**LookUps**

In almost all systems there are referential entities that in general have a numeric identifier, but that can also be a string of characters, this library has some classes that represents such entities, those are:

    - LookUp
    - LookUpStr
    - LookUpUUID

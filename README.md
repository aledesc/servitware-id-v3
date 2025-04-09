# servitware-id-v3

This library contains different interfaces, abstract classes and concrete classes to work with Entities whose identifier is an integer or a string of characters with or without structure.

**The interfaces are:**
    
    - StrId
    - Named
    - StructuredId
    - IntId

The abstract classes are:

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

Abstract classes throw exceptions in case of invalid identifiers, depending on the type, these are:

    - InvalidAlphanumericIdException
    - InvalidNameException
    - InvalidNumericIdException
    - InvalidStructuredAlphanumericIdException

The concrete classes throw the following exceptions:

    - Invalid_CIF_IdException
    - Invalid_DNI_IdException
    - Invalid_NIE_IdException

**LookUps**

In almost all systems there are referential entities that in general have a numeric identifier, but that can also be a string of characters, this librebase to these classes two classes are created to manage referential entities, whose identifier can be an integer or a string of characters, these classes are:

    - LookUp
    - LookUpStr
    - LookUpUUID

Translated with DeepL.com (free version)
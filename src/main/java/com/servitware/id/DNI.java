package com.servitware.id;

import com.servitware.base.StrId;
import com.servitware.base.exception.InvalidAlphanumericIdException;
import com.servitware.base.impl.StructuredStrIded;
import com.servitware.id.exception.Invalid_DNI_IdException;

import java.util.HashMap;
import java.util.Map;


public class DNI implements StrId {

    private final _DNI dni;

    public DNI(String id) throws Invalid_DNI_IdException  {

        try {
            dni = new _DNI(id);
        } catch (InvalidAlphanumericIdException e) {
            throw new Invalid_DNI_IdException();
        }

        if ( !dni.hasAValidIdStructure() || dni.hasAValidIdLetter() ) {
            throw new Invalid_DNI_IdException();
        }
    }

    @Override
    public String getId() {
        return dni.getId();
    }

    private static class _DNI extends StructuredStrIded {

        private final static int DIVISOR= 23;
        private final static String REGEX_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        private final static Map<Integer,Character> CONTROL_CHAR= new HashMap<>();

        static {
            CONTROL_CHAR.put( 0,'T');
            CONTROL_CHAR.put( 1,'R');
            CONTROL_CHAR.put( 2,'W');
            CONTROL_CHAR.put( 3,'A');
            CONTROL_CHAR.put( 4,'G');
            CONTROL_CHAR.put( 5,'M');
            CONTROL_CHAR.put( 6,'Y');
            CONTROL_CHAR.put( 7,'F');
            CONTROL_CHAR.put( 8,'P');
            CONTROL_CHAR.put( 9,'D');
            CONTROL_CHAR.put(10,'X');
            CONTROL_CHAR.put(11,'B');
            CONTROL_CHAR.put(12,'N');
            CONTROL_CHAR.put(13,'J');
            CONTROL_CHAR.put(14,'Z');
            CONTROL_CHAR.put(15,'S');
            CONTROL_CHAR.put(16,'Q');
            CONTROL_CHAR.put(17,'V');
            CONTROL_CHAR.put(18,'H');
            CONTROL_CHAR.put(19,'L');
            CONTROL_CHAR.put(20,'C');
            CONTROL_CHAR.put(21,'K');
            CONTROL_CHAR.put(22,'E');
        }

        public _DNI(String id) throws InvalidAlphanumericIdException {
            super(id);
        }

        public boolean hasAValidIdStructure() {
            return super.hasAValidIdStructure(REGEX_DNI);
        }

        public boolean hasAValidIdLetter() {

            int number = Integer.parseInt(this.getId().substring(0,8));
            Character letter= this.getId().charAt(8);

            int modulus = number % DIVISOR;
            Character LETRA = CONTROL_CHAR.get(modulus);

            return letter.equals( LETRA );

        }
    }
}


package sam.logging;

import java.util.logging.*;

public class AgeFilter implements Filter {
    public AgeFilter() {
    }
    /**
     * This is the overridden method from the Filter interface.
     * It checks the Person object associated with the LogRecord
     * checks the age>30, and returns true
     *@param record the LogRecord object
     *@return boolean true/false
     */
    public boolean isLoggable(LogRecord record) {
        boolean result = false;
        //obtaining the Person object from the record
        Object[] objs = record.getParameters();
        Person person = (Person)objs[0];
        
        //check if person is not null
        if(person !=null) {
            //obtain the age
            int age = person.getAge();
            if(age>30)
                result =  true;
            else
                result =  false;
        }
        return result;
    }
    
}


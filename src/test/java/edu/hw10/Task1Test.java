package edu.hw10;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {
    @Test
    void testGeneratePOJO() throws Exception {
        //arrange
        RandomObjectGenerator rog = new RandomObjectGenerator();
        //act
        MyClass myClass = rog.nextObject(MyClass.class);
        //assert
        assertNotNull(myClass);
        assertNotNull(myClass.getStringValue());
        assertTrue(myClass.getIntValue() >= 0);
    }

    @Test
    void testGenerateRecord() throws Exception {
        //arrange
        RandomObjectGenerator rog = new RandomObjectGenerator();
        //act
        MyRecord myRecord = rog.nextObject(MyRecord.class);
        //assert
        assertNotNull(myRecord);
        assertNotNull(myRecord.stringValue());
        assertTrue(myRecord.intValue() >= 0);
    }
}

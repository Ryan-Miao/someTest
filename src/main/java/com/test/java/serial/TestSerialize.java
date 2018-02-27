package com.test.java.serial;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class TestSerialize {

    private static final String filename = "D:/test.txt";

    @Test
    public void testSer() throws IOException, ClassNotFoundException {
        final Foo foo = Foo.builder()
                .fa("fa")
                .fb("fb")
                .ta("ta")
                .tb("tb")
                .build();

        Foo.PUB_STATIC  = "test";

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
        os.writeObject(foo);
        os.flush();
        os.close();

    }

    @Test
    public void testRead() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
        Foo foo2 = (Foo) is.readObject();
        is.close();

        Assert.assertEquals("fa", foo2.getFa());
        Assert.assertEquals("fb", foo2.getFb());
        Assert.assertEquals(null, foo2.getTa());
        Assert.assertEquals(null, foo2.getTb());

       Assert.assertNull(foo2.PUB_STATIC);
    }
}

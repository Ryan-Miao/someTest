package com.test.java.tenum;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/3/31.
 */
public class Mail {
    enum GeneralDelivery{YES,NO1,NO2,NO3,NO4,NO5};
    enum Scannability{UNSCANNABLE,YES1,YES2,YES3,YES4};
    enum Readability{ILLEGIBLE,YES1,YES2,YES3,YES4};
    enum Address{INCORRECT,OK1,OK2,OK3,OK4,OK5};
    enum ReturnAddress{MISSING,OK1,OK2,OK3,OK4,OK5};
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter ++;
    public String toString(){
        return "Mail "+id;
    }
    public String details(){
        return toString()+
                ",General Delivery:"+generalDelivery+
                ",Address Scanability:"+scannability+
                ",Address Readability:"+readability+
                ",Address Address:"+address+
                ",Return addres:"+returnAddress;
    }
    //General test Mail
    public static Mail randomMail(){
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return  m;
    }
    public static Iterable<Mail> generator(final int count){
        return new Iterable<Mail>() {
            int n = count;
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    public boolean hasNext() {
                        return n-->0;
                    }

                    public Mail next() {
                        return randomMail();
                    }
                };
            }
        };
    }
}

class PostOffice{

}

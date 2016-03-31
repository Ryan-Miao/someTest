package com.test.java.tenum;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/31.
 */
public enum ConstantspecificMethod {
    DATE_TIME{
        String getInfo(){
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH{
        String getInfo(){
            return System.getenv("CLASSPATH");
        }
    },
    VERSION{
      String getInfo(){
          return System.getProperty("java.version");
      }
    };
    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantspecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }
}

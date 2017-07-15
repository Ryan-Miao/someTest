package com.test.encoding;

import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by ryan on 6/24/17.
 */
public class TestProperties {

    private InputStream inputStream;

    @Before
    public void setUp(){
        inputStream = TestProperties.class.getClassLoader().getResourceAsStream("test.properties");
    }

    /**
     * 默认以ISO-8859-1读取文件，中文会被乱码
     * @throws IOException
     */
    @Test
    public void testDefaultReadByInputStream_iso8859_1() throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.forEach((k,v) -> System.out.println(k+"="+v));
        //cn=ä¸­æ
        //cn_unicode=中文
        //en=abcd
    }

    /**
     * 定义编码格式的方式去读取文件
     * @throws IOException
     */
    @Test
    public void testEncodingFormatRead_utf8() throws IOException {
        Properties properties = new Properties();
        String charsetName = "utf-8";
        properties.load(new InputStreamReader(inputStream, charsetName));
        properties.forEach((k,v) -> System.out.println(k+"="+v));
        //cn=中文
        //cn_unicode=中文
        //en=abcd
    }

    @Test
    public void testDefault_convert() throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.forEach((k,v) -> {
            try {
                v = new String(v.toString().getBytes("iso-8859-1"), "utf-8");
                System.out.println("k="+v);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        //k=中文
        //ck=??
        //k=abcd
    }

    /**
     * 证明系统默认是以utf-8的编码方式去解码的
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testDefaultGetBytesFormat() throws UnsupportedEncodingException {
        String str = "中文啊";
        byte[] bytes = str.getBytes();
        printBytes(bytes);

        byte[] bytes_utf8 = str.getBytes("utf-8");
        printBytes(bytes_utf8);

        byte[] bytes_iso88591 = str.getBytes("iso-8859-1");
        printBytes(bytes_iso88591);
        /**
         *  -28 -72 -83 -26 -106 -121
            -28 -72 -83 -26 -106 -121
            63 63
         */
    }

    /**
     * \u4E2D\u6587  中文
     * 0100 1110 0100 1101    0110 0101 1000 0111
     * @param bytes
     */
    private void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.printf(b + " ");
        }
        System.out.println();

        System.out.println(Hex.encodeHexString(bytes));
    }

    /**
     * 0011 1111
     *
     */
    @Test
    public void test63(){
        System.out.println((char) 63);
        //?
    }


}

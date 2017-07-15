package com.test.encoding;

import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by ryan on 6/25/17.
 */
public class TestEncodingConvert {

    @Test
    public void test_write_read_encoding() throws IOException {
        String file = this.getClass().getClassLoader().getResource("").getPath()+File.separator+"test.txt";
        String charset = "UTF-8";
        String iso = "ISO-8859-1";

        // 写字符换转成字节流
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(
                outputStream, charset);
        try {
            writer.write("这是要保存的中文字符");
        } finally {
            writer.close();
        }

        // 读取字节转换成字符
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(
                inputStream, iso);

        StringBuilder sb = new StringBuilder();

        int charRead = reader.read();
        while (charRead != -1){
            sb.append((char) charRead);
            charRead = reader.read();
        }

        System.out.println(sb.toString());
    }

    @Test
    public void testConvert() throws UnsupportedEncodingException {
        String s = "这是一段中文字符串";
        byte[] b = s.getBytes("UTF-8");
        String utf8 = new String(b,"UTF-8");
        String iso = new String(b,"iso-8859-1");
        Assert.assertEquals(s, utf8);
        Assert.assertEquals("è¿\u0099æ\u0098¯ä¸\u0080æ®µä¸\u00ADæ\u0096\u0087å\u00AD\u0097ç¬¦ä¸²", iso);
    }

    @Test
    public void testCharToByte() throws UnsupportedEncodingException {
        String utf8 = "UTF-8";
        String aStr = "\u4E2D\u6587";
        String zhongwen = "中文";
        byte[] bytes = aStr.getBytes(utf8);
        byte[] zhongwenBytes = zhongwen.getBytes(utf8);

        StringBuilder zhongwenBuilder = new StringBuilder();
        for(byte b: zhongwenBytes){
            zhongwenBuilder.append(b).append(";");
        }

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(b).append(";");
        }

        System.out.println(builder.toString());
        System.out.println(zhongwenBuilder.toString());

    }

    @Test
    public void unicodeToChar(){
        char aChar = '\u4E2D';
        Assert.assertEquals('中', aChar);

        String aStr = "\u4E2D\u6587";
        Assert.assertEquals("中文", aStr);

    }

    @Test
    public void testEncodingCharSet(){
        String aStr = "中文";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(aStr);
        CharBuffer charBuffer = charset.decode(byteBuffer);

        Assert.assertEquals(aStr, charBuffer.toString());
    }

    @Test
    public void testHex(){
        System.out.println(Integer.toHexString(21531));
    }

    @Test
    public void testEncoder(){
            String name = "I am 君山";

        char[] chars = name.toCharArray();
        for (char c : chars) {
            System.out.printf(c +"（"+(int)c+ "）=" + Integer.toHexString(c) +" | ");
        }
        System.out.println();

        try {
                byte[] iso8859 = name.getBytes("ISO-8859-1");
                System.out.println("iso:");
                toHex(iso8859);

                byte[] utf8 = name.getBytes("UTF-8");
                System.out.println("utf8:");
                toHex(utf8);

                byte[] gb2312 = name.getBytes("GB2312");
                System.out.println("gb2312:");
                toHex(gb2312);

                byte[] gbk = name.getBytes("GBK");
                System.out.println("gbk:");
                toHex(gbk);

                byte[] utf16 = name.getBytes("UTF-16");
                System.out.println("utf16:");
                toHex(utf16);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

    }

    private void toHex(byte[] data) {
        for (byte b: data){
            byte[] bytes = {b};
            System.out.printf(Hex.encodeHexString(bytes) + "           | ");
        }
        System.out.println();
    }


}

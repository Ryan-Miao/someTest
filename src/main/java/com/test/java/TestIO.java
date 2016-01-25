package com.test.java;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/23.
 * 文件复制传输
 */
public class TestIO {

    /**
     * 管道传输文件--通过设置buffer检测传输进度
     *
     * @param source
     * @param target
     * @throws Exception
     */
    private static long nioBufferCopy(File source, File target) throws IOException {
        long a = new Date().getTime();
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null)
                inStream.close();
            if (in != null)
                in.close();
            if (outStream != null)
                outStream.close();
            if (out != null)
                out.close();
            long b = new Date().getTime();
            return b - a;
        }
    }

    /**
     * 不考虑多线程优化，单线程文件复制最快的方法是(文件越大该方法越有优势，一般比常用方法快30+%):
     *
     * @param source
     * @param target
     * @throws IOException
     */
    private static long nioTransferCopy(File source, File target) throws IOException {
        long a = new Date().getTime();
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null)
                inStream.close();
            if (in != null)
                in.close();
            if (outStream != null)
                outStream.close();
            if (out != null)
                out.close();

            long b = new Date().getTime();
            return b - a;
        }
    }

    /**
     * 常用的复制方法
     *
     * @param source
     * @param target
     * @throws IOException
     */
    private static long customBufferBufferedStreamCopy(File source, File target) throws IOException {
        long a = new Date().getTime();
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(source));
            fos = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
            long b = new Date().getTime();
            return b - a;
        }
    }

    public static void main(String[] args) throws Exception {
        File f1 = new File("E:\\apps\\Editplus_CK_XP85.rar");
//        File f1 = new File("E:\\apps\\java\\myeclipse10\\myeclipse-10.7.1-offline-installer-windows.exe");
        File f2 = new File("E:\\apps\\test.rar");
        File f3 = new File("E:\\apps\\test2.rar");
        File f4 = new File("E:\\apps\\test3.rar");
        System.out.println("管道直接：" + nioTransferCopy(f1, f4));
        System.out.println("管道缓存：" + nioBufferCopy(f1, f2));
        System.out.println("byte缓存：" + customBufferBufferedStreamCopy(f1, f3));


    }

}

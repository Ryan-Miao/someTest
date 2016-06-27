package com.test.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtil {  
    
    public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("创建单个文件" + destFileName + "失败，文件已存在！");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("创建单个文件" + destFileName + "失败，文件不能为目录！");  
            return false;  
        }  
        //判断文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
                return false;  
            }  
        }  
        //创建目标文件  
        try {  
            if (file.createNewFile()) {  
                System.out.println("创建单个文件" + destFileName + "成功！");  
                return true;  
            } else {  
                System.out.println("创建单个文件" + destFileName + "失败！");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
            return false;  
        }  
    }  
     
     
    public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }  
     
     
    public static String createTempFile(String prefix, String suffix, String dirName) {  
        File tempFile = null;  
        if (dirName == null) {  
            try{  
                //在默认文件夹下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix);  
                //返回临时文件的路径  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        } else {  
            File dir = new File(dirName);  
            //如果临时文件所在目录不存在，首先创建  
            if (!dir.exists()) {  
                if (!FileUtil.createDir(dirName)) {  
                    System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");  
                    return null;  
                }  
            }  
            try {  
                //在指定目录下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix, dir);  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        }  
    }  
     
    public static void main(String[] args) {  
        //创建目录  
        String dirName = "D:/work/temp/temp0/temp1";  
        FileUtil.createDir(dirName);  
        //创建文件  
        String fileName = dirName + "/temp2/tempFile.txt";  
        FileUtil.createFile(fileName);  
        //创建临时文件  
        String prefix = "temp";  
        String suffix = ".txt";  
        for (int i = 0; i < 10; i++) {  
            System.out.println("创建了临时文件："  
                    + FileUtil.createTempFile(prefix, suffix, dirName));  
        }  
        //在默认目录下创建临时文件  
        for (int i = 0; i < 10; i++) {  
            System.out.println("在默认目录下创建了临时文件："  
                    + FileUtil.createTempFile(prefix, suffix, null));  
        }  
    }

    public static void fileCopy(String source,String targer) throws IOException {
        try(InputStream in = new FileInputStream(source)) {
            try(OutputStream out = new FileOutputStream(targer)) {
                byte[] buffer = new byte[3096];
                int byteToRead;
                while((byteToRead = in.read(buffer))!=-1){
                    out.write(buffer,0,byteToRead);
                }
            }
        }
    }
    public static void fileCopyNIO(String source,String target) throws IOException {
        try(FileInputStream in = new FileInputStream(source)) {
            try(FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel  = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while (inChannel.read(buffer)!=-1){
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

    /**
     *统计给定文件中字符word的个数
     */
    public static int countWordInFile(String filename,String word) throws IOException {
        int count = 0;
        try(FileReader fr = new FileReader(filename)){
            try(BufferedReader br = new BufferedReader(fr)){
                String line = null;
                while ((line = br.readLine())!=null){
                    int index = -1;
                    while (line.length()>=word.length() && (index=line.indexOf(word))>=0){
                        count++;
                        line = line.substring(index+word.length());
                    }
                }
            }
        }
        return count;
    }
    /**
     * 列出当前文件夹下的文件
     */
    public void fileList(String source){
        File file = new File(source);
        for (File temp : file.listFiles()) {
            if (temp.isFile()){
                System.out.println(temp.getName());
            }
        }
    }

    /**
     * 列出文件夹下的所有文件，深入
     */
    private static void _walkDirectory(File f,int level) throws IOException {
        if (f.isDirectory()){
            writeTofile(f, level);
            for (File temp : f.listFiles()) {
                _walkDirectory(temp,level+1);
            }
        }else {
            writeTofile(f, level);
        }
    }

    private static void writeTofile(File f, int level) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("F:\\listFile.txt"),true))){
            for (int i = 0; i < level - 1; i++) {
                System.out.print("----");
                bw.write("----");
            }
            System.out.println("|"+f.getName());
            bw.write("|"+f.getName());
            bw.newLine();
            bw.flush();
        }
    }

    public static void showDirectory(File f) throws IOException {
        _walkDirectory(f,0);
    }
    @Test
    public void testShow(){
        try {
            showDirectory(new File("D:\\MyApp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出文件夹下的所有文件，深入
     */
    @Test
    public void listFiles() throws IOException {
        Path path = Paths.get("D:\\MyApp");
        Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void deleteFile() throws Exception{
        File dir = new File("D:\\perforce\\workspace\\EPC");
        FileUtils.deleteDirectory(dir);
    }

}
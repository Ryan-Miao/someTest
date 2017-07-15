package com.test.capture;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;

import java.io.File;

/**
 * Created by Ryan on 2017/5/8/008.
 */
public class SimpleRecognize {

    @Test
    public void test() {

        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("D:\\temp\\tessdata");
        try {
                String pathname = "D:\\workspace\\parttime2\\wz-service\\build\\classes\\test\\83416174.jpg";

                File imageFile = new File(pathname);
                String result = tessreact.doOCR(imageFile);
                System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}

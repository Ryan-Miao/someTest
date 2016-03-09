package com.test.java.thread.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * java 并发编程实战
 * 使用callable future
 * 6-13 使用Future等待图像下载
 * Created by mrf on 2016/3/9.
 */
public class FutureRender {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    void renderPage(CharSequence source){
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executorService.submit(task);
        //renderText();
        try{
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                //renderImage(data);
            }
        } catch (InterruptedException e) {
            //重置线程状态
            Thread.currentThread().interrupt();
            //由于不需要结果，因为取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
//            trow  xxx;
        }
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        //根据source获取图片信息
        List<ImageInfo> list = new ArrayList<>();
        return list;
    }

}

/**
 * 图片信息
 */
class ImageInfo{
    String name="";
    String src = "";
    public ImageData downloadImage(){
        return new ImageData();
    }
}

/**
 * 图片
 */
class ImageData{
    String data = "";
}
/**
 * 6-15
 * 使用CompletionService,使页面元素在下载完成后立即显示出来
 * CompletionService和BlockingQueue结合起来
 */
class Renderer{
    private final ExecutorService executorService;

    public Renderer(ExecutorService executorService) {
        this.executorService = executorService;
    }
    void renderPage(CharSequence source){
        List<ImageInfo> infos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
        for (final ImageInfo info : infos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return info.downloadImage();
                }
            });
        }
        //renderText();
        try {
            for (int t = 0; t < infos.size(); t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                //renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<>();
    }

}

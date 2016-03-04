package com.test.java.thread.concurrency;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.Immutable;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.annotation.ThreadSafe;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 车辆追踪器
 * 监视器模式
 * Created by mrf on 2016/3/4.
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = locations;
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String,MutablePoint> result = new HashMap<>();
        for (String id : locations.keySet()) {
            result.put(id,new MutablePoint(locations.get(id)));
        }
        return Collections.unmodifiableMap(result);

    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null?null:new MutablePoint(loc);
    }

    public synchronized void setLocation(String id,int x,int y){
        MutablePoint loc = locations.get(id);
        if (loc == null)
            throw new IllegalArgumentException("No such Id:"+id);
        loc.x = x;
        loc.y = y;
    }


}

@NotThreadSafe
class MutablePoint{
    public int x,y;
    public MutablePoint(){
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}

/**
 * 不可变
 */
@Immutable
class Point{
    public final int x,y;

    Point(int x,int y ) {
        this.x = x;
        this.y = y;
    }
}

/**
 * 委托给线程安全类的车辆追踪器
 */
@ThreadSafe
class DelegatingVehicleTracker{
    private final ConcurrentHashMap<String,Point> locations;
    private final Map<String,Point> unmodifiableMap;

   public  DelegatingVehicleTracker(Map<String,Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String,Point> getLocations(){
        return unmodifiableMap;
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id,int x,int y){
        if(locations.replace(id,new Point(x,y)) == null){
            throw new IllegalArgumentException("invalid vehicle name:"+id);
        }
    }
}

/**
 * 将线程安全委托给多个状态变量
 */
class VisualComponent{
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener){
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener mouseListener){
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListener(KeyListener listener){
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener){
        mouseListeners.remove(listener);
    }

}
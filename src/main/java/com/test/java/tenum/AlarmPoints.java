package com.test.java.tenum;

import java.util.EnumSet;

/**
 * 酱爆传感器位置
 * Created by Administrator on 2016/3/31.
 */
public enum AlarmPoints {
    STAR1,STAR2,LOBBY,OFFICE1,OFFICE2,OFFICE3,OFFICE4,BATHROOM,UTILITY,KITCHEN
}

/**
 * 我们使用enumSet来跟踪警报器的状态
 */
class EnumSets{
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);//Empty set
        points.add(AlarmPoints.BATHROOM);
        System.out.println(points);
        //添加多个
        points.addAll(EnumSet.of(AlarmPoints.STAR1,AlarmPoints.STAR2,AlarmPoints.KITCHEN,AlarmPoints.OFFICE3));
        System.out.println(points);
        //去除office1到office4范围内的
        points.removeAll(EnumSet.range(AlarmPoints.OFFICE1,AlarmPoints.OFFICE4));
        System.out.println(points);
        //其他的
        points = EnumSet.complementOf(points);
        System.out.println(points);
    }
}

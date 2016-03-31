package com.test.java.tenum;

import java.util.EnumMap;
import java.util.Map;

/**

 * Created by Administrator on 2016/3/31.
 */
public class EnumMaps {
    public static void main(String[] args){
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("kitchen fire!");
            }
        });
        em.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> entry : em.entrySet()) {
            System.out.println(entry.getKey()+":");
            entry.getValue().action();
        }
        try {
            em.get(AlarmPoints.UTILITY).action();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
interface Command{
    void action();
}

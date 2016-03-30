package com.test.refactor.ifelse.news.news2;

import com.test.refactor.ifelse.news.SystemState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.test.refactor.ifelse.news.SystemState.*;

/**
 * 从代码中可以看出，每一个performer都含义有一个SystemState，
 * 这个SystemState属性，将只能通过构建器映射方式射入一个 performer的对象实例。
 * 换句话说SystemState只是一个只读属性，而且每一个performer实体类都只负责处理一个enum的实例 （下面马上会解释如何实现的）。
 * 这里使用的Image作为一个例子，它表示用户的每一个状态都可以使用一个图标来表示。performer（）方法将负责 处理具体的逻辑。
 * 这个 SystemStatePerformer的实体子类可以引用任何类型的对象，然后在perform（）方法里面进行调用。
 * 下一步就是编写SystemStatePerformer的实体子类。
 * 我首先想到的是为每一个enum实例编写一个实际的子类，理论上来说是没问题的，
 * 但 是这样做必须编写一大堆的子类，不便于管理。
 * 所以我决定使用Factory + annonymous classes来构建具体的实体子类，让Factory来管理所有的实体子类。
 * 代码如下：
 * Created by Administrator on 2016/3/28.
 */
public class SystemStatePerformerFactory {
    private static SystemStatePerformerFactory instance = new SystemStatePerformerFactory();
    private Map<SystemState,SystemStatePerformer> performers;
    private SystemStatePerformerFactory() {
    }

    public static SystemStatePerformerFactory getInstance(){
        return instance;
    }

    public SystemStatePerformer getSystemStatePerformer(SystemState state) throws Exception{
        return getPerformers().get(state);
    }

    /**
     * 通过反射将@FactoryMethod标记的注解的方法执行
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private synchronized Map<SystemState,SystemStatePerformer> getPerformers() throws InvocationTargetException, IllegalAccessException {
        if(performers==null){
            performers = new HashMap<>();
            //call all @FactoryMethod using reflection
            for (Method method : getClass().getDeclaredMethods()) {
                if (method.getAnnotation(FactoryMethod.class)!=null){
                    System.out.println(method.getName());
                    SystemStatePerformer p = (SystemStatePerformer)method.invoke(this);
                    performers.put(p.getState(),p);
                }
            }
            //make it readonly
            performers = Collections.unmodifiableMap(performers);
        }
        return performers;
    }

    @FactoryMethod
    private  SystemStatePerformer createLoggedInPerformer() {
        return new SystemStatePerformer(SystemState.LOGGEDIN, getImage("loggedin.gif")) {
            @Override
            public void perform() {
                // do something after logging in is successful,
                // for example: show welcome dialog, open the last edit document, etc.
            }
        };
    }

    @FactoryMethod
    private  SystemStatePerformer createLoggedOutPerformer() {
        return new SystemStatePerformer(LOGGEDOUT, getImage("loggedout.gif")) {
            @Override
            public void perform() {
                // do something after logging out is successful,
                // for example: free used resource, dispose GUI components, etc.            }
            }
        };
    }

    @FactoryMethod
    private  SystemStatePerformer createIdlePerformer() {
        return new SystemStatePerformer(IDLE, getImage("idle.gif")) {
            @Override
            public void perform() {
                // do something after the user is idle,
                // for example: save the application state temporarily, lock the application, etc.
            }
        };

    }


    private static Image getImage(String string) {
        return new BufferedImage(10, 10, BufferedImage.TYPE_4BYTE_ABGR);
    }
}

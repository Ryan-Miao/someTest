package com.test.refactor.ifelse.old;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 繁复的ifelse重构
 * 以下是if-else结构
 * <p>
 * 这里我们展示了一个 SystemManager，它负责处理用户在系统中的状态：登入（logged in），登出（logged out），以及空闲（idle）。从代码中可以看到，这个类用了int来定义状态并且因此导致了updatteState（）方法里面出现大量if else.从目前看来这些if else是无法避免的，应为这个类需要针对不同的状态作出反应。随着状态的增加，if else的数量也会继续增加。这个解决方案显然很差。
 那么怎么样才能让这个类更加地面向对象呢？
 * </p>
 * Created by mrf on 2016/3/28.
 */
class SystemManager {
    public static final int LOGGEDIN = 0;
    public static final int LOGGEDOUT = 1;
    public static final int IDEL = 2;
    int state;
    public void login(){
        //call service #login()
        updateState(LOGGEDIN);
    }
    public void logout(){
        //call service #logout()
        updateState(LOGGEDOUT);
    }
    public void idle(){
        //call service #idel()
        updateState(IDEL);
    }

    private void updateState(int state) {
        if(state == LOGGEDIN){
            //do something after login is successful
            //for example:show welcome dialog,open the last edit document,etc.
        }else if(state == LOGGEDOUT){
            //do something after logout is successful
            //for example:free used resource,dispose GUI componets,etc.
        }else if (state == IDEL){
            //do something after the user is idle,
            //for example:save the application state temporarily,lock the application,etc.
        }else {
            throw  new IllegalArgumentException("unknown state");
        }
        this.state = state;
    }

}

package com.test.refactor.ifelse.news;
import com.test.refactor.ifelse.news.news2.SystemStatePerformer;
import com.test.refactor.ifelse.news.news2.SystemStatePerformerFactory;

import static com.test.refactor.ifelse.news.SystemState.*;

/**
 * 1:@see SystemState
 * 2:
 *  然后开始重构SystemManager， 使用SystemState代替SystemManager里的int状态：
 1. 添加 import static de.jingge.refactoring.SystemState.*；
 2. 删除所有的integer常量
 3. 将变量state的类型改为SystemState.
 * Created by Administrator on 2016/3/28.
 */
public class SystemManager {
    SystemStatePerformer statePerformer;
    public void login() {
        // call service#login()
        updateState(LOGGEDIN);
    }

    public void logout() {
        // call service#logout()
        updateState(LOGGEDOUT);
    }

    public void idle() {
        // call some other services
        updateState(IDLE);
    }

    public void updateState(SystemState state) {
        try {
            this.statePerformer = SystemStatePerformerFactory.getInstance().getSystemStatePerformer(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer s = 1234567890;
        Integer s2 = 1234567890;
        System.out.println(s==s2);
    }
}

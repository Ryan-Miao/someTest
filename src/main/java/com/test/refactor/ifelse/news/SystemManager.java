package com.test.refactor.ifelse.news;
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
    SystemState state;
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
        if (state == LOGGEDIN) {
            // do something after logging in is successful,
            // for example: show welcome dialog, open the last edit document, etc.
        } else if (state == LOGGEDOUT) {
            // do something after logging out is successful,
            // for example: free used resource, dispose GUI components, etc.
        } else if (state == IDLE) {
            // do something after the user is idle,
            // for example: save the application state temporarily, lock the application, etc.
        } else {
            throw new IllegalArgumentException("unknown state");
        }
        this.state = state;
    }
}

package com.test.refactor.ifelse.news;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.test.refactor.ifelse.news.SystemState.*;


/**
 * 重构测试类：
 1.    添加import static de.jingge.refactoring.SystemState.*；
 2.    删除所有常量前引用的SystemManager.
 * Created by Administrator on 2016/3/28.
 */
public class SystemManagerTest {
    private static SystemManager manager;
    @BeforeClass
    public static void setUpClass() throws Exception {
        manager = new SystemManager();
        // add some service mock objects
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void login(){
        manager.login();
        assertEquals(manager.statePerformer.getState(),LOGGEDIN);
    }
    @Test
    public void logout() {
        manager.logout();
        assertEquals(manager.statePerformer.getState(), LOGGEDOUT);
    }

    @Test
    public void idle() {
        manager.idle();
        assertEquals(manager.statePerformer.getState(), IDLE);
    }
}

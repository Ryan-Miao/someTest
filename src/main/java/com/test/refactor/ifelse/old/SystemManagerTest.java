package com.test.refactor.ifelse.old;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试if-else
 * 测试代码
 */
public class SystemManagerTest{
    private static SystemManager manager;
    //static的运行前运行，针对所有的测试只运行一次，而@Before会在每个测试方法之前执行
    @BeforeClass
    public static void setUpClass() throws Exception{
        manager = new SystemManager();
        //add some service mock objects
    }
    @AfterClass
    public static void tearDownClass() throws Exception{

    }
    @Test
    public void login(){
        manager.login();
        assertEquals(manager.state,SystemManager.LOGGEDIN);
    }
    @Test
    public void logout(){
        manager.logout();
        assertEquals(manager.state,SystemManager.LOGGEDOUT);
    }
    @Test
    public void idle(){
        manager.idle();
        assertEquals(manager.state,SystemManager.IDEL);
    }

}

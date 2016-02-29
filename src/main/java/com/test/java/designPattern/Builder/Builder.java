package com.test.java.designPattern.Builder;

import com.test.java.designPattern.factory.factoryMethod.MailSender;
import com.test.java.designPattern.factory.factoryMethod.Sender;
import com.test.java.designPattern.factory.factoryMethod.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * Created by mrf on 2016/2/29.
 */
public class Builder {
    private List<Sender> list = new ArrayList<>();

    public List<Sender> produceMailSender(int count){
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
        return list;
    }

    public List<Sender> produceSmsSender(int count){
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
        return list;
    }
}

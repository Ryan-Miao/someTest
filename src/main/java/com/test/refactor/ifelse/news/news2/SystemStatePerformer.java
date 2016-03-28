package com.test.refactor.ifelse.news.news2;

import com.test.refactor.ifelse.news.SystemState;

import java.awt.*;

/**
 * 首先仔细观察一下updateState（）方法，我们会发现，导致该方法内存在大量if else的原因是它的参数仅仅是一个enum.由于enum本身并不含有任何逻辑代码，因此导致处理enum的方法需要使用if else来分析enum然后调用相应的逻辑。明白了这个道理之后，重构的方向就明了了。简单的说，我们需要要将方法参数由enum替换成一个更加强壮的抽 象类，每一个继承该类的子类将具体负责处理一个enum实例，之后再将updateState（）方法中相应的逻辑代码转移到这些子类中。这样处理之后， 令人讨厌的if else就会消失了。
 我们将这个替换enum的抽象类命名为SystemStatePerformer
 * Created by Administrator on 2016/3/28.
 */
public abstract class SystemStatePerformer {
    private final SystemState state;
    private Image image;
    public SystemStatePerformer(SystemState state,Image image){
        this.state = state;
        this.image = image;
    }
    public SystemState getState(){
        return state;
    }
    public Image getImage(){
        return image;
    }
    public abstract void perform();
}

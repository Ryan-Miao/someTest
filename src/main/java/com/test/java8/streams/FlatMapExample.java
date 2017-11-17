package com.test.java8.streams;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ryan Miao on 11/17/17.
 */
public class FlatMapExample {

    @Test
    public void useToFlatTwoList(){
        final List<Integer> a = Lists.newArrayList(1,1);
        final List<Integer> b = Lists.newArrayList(2,2);
        final List<Integer> c = Lists.newArrayList(3,3);

        //目标将三个list合成一个
        List<Integer> rs = Stream.of(a, b, c)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        System.out.println(rs);
        Assert.assertTrue(6 == rs.size());
        Assert.assertTrue(3 == rs.get(5));

        /**
         * 首先，将3个list合并成一个大list包含3个小list，【[1,1], [2,2], [3,3]】
         * 但，这个不是我们想要的结果，我们需要把每个小list的数据打开，拼成新的，
         * 于是， flatMap--->将key转换成一个stream，将每个小list转换成一个stream，
         * 然后，stream之间就会拼接起来？
         * 看来，是这样。
         * 拼接完成之后，收集。
         */
    }
}

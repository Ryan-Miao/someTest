package com.test.java.list;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ryan on 2017/09/28/0028.
 */
public class FilterTwoList {
    /**
     * @author Ryan Miao
     */
    class SocialSecurity{
        private Integer id;//社保号码
        private Integer idCard;//身份证号码
        private String somethingElse;

        public SocialSecurity(Integer id, Integer idCard, String somethingElse) {
            this.id = id;
            this.idCard = idCard;
            this.somethingElse = somethingElse;
        }

        public Integer getId() {
            return id;
        }

        public Integer getIdCard() {
            return idCard;
        }

        public String getSomethingElse() {
            return somethingElse;
        }

        @Override
        public String toString() {
            return "SocialSecurity{" +
                    "id=" + id +
                    ", idCard=" + idCard +
                    ", somethingElse='" + somethingElse + '\'' +
                    '}';
        }
    }

    class IdCard {
        private Integer id;//身份证号码
        private String somethingElse;

        public IdCard(Integer id, String somethingElse) {
            this.id = id;
            this.somethingElse = somethingElse;
        }

        public Integer getId() {
            return id;
        }

        public String getSomethingElse() {
            return somethingElse;
        }

        @Override
        public String toString() {
            return "IdCard{" +
                    "id=" + id +
                    ", somethingElse='" + somethingElse + '\'' +
                    '}';
        }
    }


    private ArrayList<SocialSecurity> socialSecurities;
    private ArrayList<IdCard> idCards;

    @Before
    public void setUp(){
        socialSecurities = Lists.newArrayList(
                new SocialSecurity(1, 12, "小明"),
                new SocialSecurity(2, 13, "小红"),
                new SocialSecurity(3, 14, "小王"),
                new SocialSecurity(4, 15, "小peng")
        );

        idCards = Lists.newArrayList(
                new IdCard(14, "xiaopeng"),
        new IdCard(13, "xiaohong"),
        new IdCard(12, "xiaoming")
        );

        //目标： 从socialSecurities中筛选出idCards中存在的卡片
    }

    @Test
    public void testFilterForEach(){
        List<SocialSecurity> result = new ArrayList<>();
        int count = 0;
        for (SocialSecurity socialSecurity : socialSecurities) {
            for (IdCard idCard : idCards) {
                count++;
                if (socialSecurity.getIdCard().equals(idCard.getId())){
                    result.add(socialSecurity);
                }
            }
        }

        System.out.println(result);
        System.out.println(count);//12 = 3 * 4
        //O(m,n) = m*n;
    }

    @Test
    public void testFilterHash(){
        Set<Integer> ids = idCards
                .stream()
                .map(IdCard::getId)
                .collect(Collectors.toSet());
        List<SocialSecurity> result = socialSecurities
                .stream()
                .filter(e->ids.contains(e.getIdCard()))
                .collect(Collectors.toList());

        System.out.println(result);
        //初始化 hash 3
        //遍历socialSecurities 4
        //从hash中判断key是否存在  4
        //O(m,n)=2m+n=11
    }

    @Test
    public void testCondition(){
        int maxN = 0;
        for (int m = 2; m < 100; m++) {
            for (int n = 3; n < 100; n++) {
                if ((2*m+n)>m*n){
                    System.out.println("m="+m +",n="+n);
                    if (n>maxN){
                        maxN = n;
                    }
                }
            }
        }

        System.out.println(maxN);
    }
}

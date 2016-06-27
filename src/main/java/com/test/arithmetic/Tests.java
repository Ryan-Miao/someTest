package com.test.arithmetic;

import org.apache.commons.collections4.bag.HashBag;

import java.util.*;

/**
 * Created by rmiao on 6/26/2016.
 */
public class Tests {


    private static double FLAG=0.5;


    public static void main(String[] args) {
        String user1 = "a";
        String user2 = "b";
        String user3 = "c";
        Map<String, int[]> map = new HashMap<>();
        map.put(user1, new int[] { 1, 2, 3 });
        map.put(user2, new int[] { 2, 3, 4, 5 });
        map.put(user3, new int[] { 4, 5, 6, 7 });
        Map<String, List> recommendMap = computeRecommend(map);
        printData(recommendMap);
// expect:
// a -> (4,5)
// b -> (1,6,7)
// c -> (2,3)
    }

    public static Map<String, List> computeRecommend(Map<String, int[]> data) {

        Map<String,List> recommed = new HashMap<>();
        String[] nameList = initRecommendResult(data, recommed);


        for (int i = 0; i < nameList.length; i++) {

            for (int j = i+1; j < nameList.length; j++) {

                Map<String, Object> repeatResult = getRepeatCount(data.get(nameList[i]), data.get(nameList[j]));

                Set repeatSet = (Set)repeatResult.get("repeatSet");
                if (repeatSet.size()>0){

                    adviseByPercentage(recommed, nameList[i], nameList[j], repeatResult);

                }
            }
        }

        return recommed;
    }

    public static Map<String, Object> getRepeatCount(int[] M, int[] N) {

        Map<String,Object> result = new HashMap<>();

        if (M.length==0 || N.length==0){
            return result;
        }

        Set<Integer> setM = getUniqueSet(M);
        Set<Integer> setN = getUniqueSet(N);
        HashBag bag=new HashBag();
        bag.addAll(setM);
        bag.addAll(setN);

        Set set = bag.uniqueSet();

        Set repeatSet = new HashSet();

        for (Object o : set) {
            if(bag.getCount(o)>=2){
                repeatSet.add(o);
            }
        }

        result.put("repeatSet",repeatSet);

        Map<String, Object> resultM= new HashMap<>();
        Map<String, Object> resultN= new HashMap<>();
        setM.removeAll(repeatSet);
        setN.removeAll(repeatSet);

        resultM.put("percentage",repeatSet.size()/(double)setM.size());
        resultM.put("unRepeatSet",setM);

        resultN.put("percentage",repeatSet.size()/(double)setN.size());
        resultN.put("unRepeatSet",setN);

        result.put("M",resultM);
        result.put("N",resultN);
        return result;
    }

    private static Set<Integer> getUniqueSet(int[] M) {
        Set<Integer> setM=new HashSet<Integer>();
        for(int m:M)
            setM.add(m);
        return setM;
    }

    private static void adviseByPercentage(Map<String, List> recommed, String i, String j, Map<String, Object> repeatResult) {
        HashMap m = (HashMap)repeatResult.get("M");
        HashMap n = (HashMap)repeatResult.get("N");

        if((double)m.get("percentage")>= FLAG){
            recommed.get(i).addAll((Set)n.get("unRepeatSet"));
        }
        if((double)n.get("percentage")>=FLAG){
            recommed.get(j).addAll((Set)m.get("unRepeatSet"));
        }
    }

    private static String[] initRecommendResult(Map<String, int[]> data, Map<String, List> recommed) {
        Set<String> names = data.keySet();
        String[] nameList = new String[names.size()];

        int index=0;
        for (String s : names) {
            recommed.put(s,new ArrayList());
            nameList[index] = s;
            index++;
        }
        return nameList;
    }

    public static void printData(Map<String, List> data) {
        if (data == null){
            System.out.println("null");
            return;
        }
        for (Map.Entry<String, List> entry : data.entrySet()) {
            System.out.println(entry.getKey()+" -> " + entry.getValue());
        }
    }

}

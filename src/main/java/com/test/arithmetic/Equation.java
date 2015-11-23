package com.test.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equation {

    private static double a = 1;
    private static double b = 1;
    private static double c = 3;
    private static double d = 9;

    /**
     * @param @param args 设定文件
     * @return void 返回类型
     * @throws
     * @Title: main
     * @Description: f(x)=0的一元三次方程在(-100,100)上三个实数根，差值绝对值大于等于1
     * <P>一、枚举算法
     * 题目：形如ax3+bx2+cx+d=0的一个一元三次方程。给出该方程中各项的系数(a，b，c，d均为实数)，并约定该方程存在三个不同实根(根的范围在-100至100之间)，且根与根之差的绝对值大于或等于1。要求由小到大依次在同一行输出这三个实根(根与根之间留有空格)，并精确到小数点后2位。
     * 提示：记方程f(x)=0，若存在2个数x1和x2，且x1<x2，f(x1)*(x2)<0，则在(x1,x2)之间一定有一个根。</P>
     * <p>
     * 根据题目，由于没有给定参数abcd,很难确定这个方程具体只有三个解，
     * 现随意设置系数。若要与题目约定相符合，请给出系数具体数值
     * </p>
     */
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        List<Double> list = stepAndDichotomy(-100, 100, 1, 0.01);
        System.out.println("方程 " + a + "x3 + " + b + " x2 +" + c + "x + " + d
                + "=0");
        if (!list.isEmpty()) {
            System.out.println("在区间（-100,100）的解：");
            Collections.sort(list);
            System.out.println(list);
        } else {
            System.out.println("在区间（-100,100）无解");
        }

        long t2 = System.currentTimeMillis();
        System.out.println("运行时间：" + (t2 - t1));

        System.out
                .println("====================分割线================================");

        // 枚举系数--这里遍历系数区间（-100,100）
        int BEGIN = -100;
        int END = 100;
        enumCoefficient(BEGIN, END);

    }

    /**
     * @return void    返回类型
     * @throws
     * @Title: enumCoefficient
     * @Description: 枚举系数
     */
    public static void enumCoefficient(int BEGIN, int END) {
        for (a = BEGIN; a < END; a++) {
            for (b = BEGIN; b < END; b++) {
                for (c = BEGIN; c < END; c++) {
                    for (d = BEGIN; d < END; d++) {
                        long t3 = System.currentTimeMillis();
                        List<Double> list2 = stepAndDichotomy(-100, 100, 1,
                                0.01);
                        if (list2.size() == 3) {
                            list2 = max(list2);
                            if (list2 != null) {
                                System.out.println("方程 " + a + "x3 + " + b
                                        + " x2 +" + c + "x + " + d + "=0");
                                System.out.println("在区间（-100,100）的解：");
                                System.out.println(list2);
                                System.out.println();
                                long t4 = System.currentTimeMillis();
                                System.out.println("运行时间：" + (t4 - t3));
                                System.out.println();
                            }

                        }
                    }
                }
            }

        }
    }

    public static double f(double x) {
        double p = Math.pow(x, 3);
        double q = Math.pow(x, 2);
        double r = a * p + b * q + c * x + d;
        return r;
    }

    public static List<Double> max(List<Double> list) {
        Double d1 = list.get(0);
        Double d2 = list.get(1);
        Double d3 = list.get(2);
        double abs1 = Math.abs(d1 - d2);
        double abs2 = Math.abs(d3 - d2);
        double abs3 = Math.abs(d1 - d3);
        if (abs1 >= 1 && abs2 >= 1 && abs3 >= 1) {
            Collections.sort(list);
            return list;
        }

        return null;
    }

    /**
     * @param @param p 解区间的 较小值
     * @param @param q 解区间的较大值
     * @param @param accuracy 精度
     * @param @param h 步长
     * @return double 返回x
     * @Title: dichotomy
     * @Description: 二分法 要求解空间内 单调,p<q
     */
    public static double dichotomy(double a1, double b1, double h,
                                   double accuracy) {
        if (h < 0.01) {
            h = 0.01;
        }
        // 1寻找解区间
        while (f(a1) * f(b1) > 0) {
            a1 = b1;
            b1 = a1 + h;
        }
        if (f(a1) * f(b1) == 0) {
            if (f(b1) == 0) {
                return b1;
            }
            //
            return 999999999;
        }
        double x = (b1 + a1) / 2;
        // 2逐步提高精度
        double abs = Math.abs(b1 - a1);
        double abs3 = (int) (abs * 10000) / 10000.0;
        while (abs3 > accuracy) {
            if (f(x) == 0) {
                break;
            }
            if (f(a1) * f(x) < 0) {
                return dichotomy(a1, x, h / 10, accuracy);
            } else {
                return dichotomy(x, b1, h / 10, accuracy);

            }
        }

        return x;
    }

    /**
     * @param @param p 区间左
     * @param @param q 区间右
     * @param @param h 步长,步长内有唯一解，或忽略偶重根
     * @param @param accuracy 精度
     * @return double 返回x
     * @throws
     * @Title: stepAndDichotomy
     * @Description: 等步长结合 二分法
     */
    public static List<Double> stepAndDichotomy(double p, double q, double h,
                                                double accuracy) {
        List<Double> list = new ArrayList<>();
        if (h < 0.01) {
            h = 0.01;
        }
        double a1 = p;
        double b1 = a1 + h;

        while (b1 < q) {
            // 1寻找解区间
            while (f(a1) * f(b1) > 0) {
                a1 = b1;
                b1 = a1 + h;

                if (b1 > q) {
                    return list;
                }
            }
            double r = dichotomy(a1, b1, h / 10, accuracy);
            if (r != 999999999) {
                list.add(r);
            }
            a1 = b1;
            b1 = a1 + h;

        }

        return list;
    }
}

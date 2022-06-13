package history.leetcode.greed;

import java.util.*;

/**
  @author 74281
  @create 2020/09/24
  @description: 广联达笔试 - 机器人在电量... 的情况下,每种动作只做一次,获得最多的愉悦感
  
  输入: 第一行两个以空格隔开的正整数n和C，表示动作数量和机器人剩余电量。
  接下来n行，每行两个以空格隔开的浮点数ci和整数wi，代表第i种动作电量消耗以及愉悦度。
  
  输出: 一个整数，表示愉悦度之和的最大值
  
  input:
  3 15             -  动作数量、机器人剩余电量
  5.00 16  3.2     -  耗电量、  愉悦度、 性价比
  9.00 1   0.11
  8.00 15  1.875
  
  output:31
  
  选择第一个和第三个动作，总电量消耗5.00+8.00=13.00<15，总愉悦度31，可以证明这是最优方案。
  n≤300，C≤30000，0≤c_i≤900000.00，0<w_i≤250
  
  todo 笔试,
  错误的逻辑：
  C >= minC  按性价比排序,
  且当前电量 > 最高性价比的耗电量时, 才扣电量 + 愉悦度
  若当前电量 < 最高性价比的耗电量时
  因为会出现最高性价比的耗电量,超过当前电量, 此时选完电量为负, 直接退出....
  正确的逻辑:
  选择 动作耗电量 <= 当前电量的动作,
  选择里面最高性价比的...
  扣电量... 继续循环
  */

public class MaxHappiness {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
    /*
    3 15             -  动作数量、机器人剩余电量
    5.00 16  3.2     -  耗电量、  愉悦度、 性价比
    
        List<Float, Integer, Float> -> 错误的写法
        List<Number> -> 正确的写法
    */
        int ActionN = scanner.nextInt();
        float ElectricC = scanner.nextFloat();
        List<List<Number>> inputList = new ArrayList<>();

        for (int i=0; i<ActionN; i++){
            float actionC = scanner.nextFloat(); // 耗电量
            int   actionH = scanner.nextInt(); // 愉悦度H - happiness
            float val = actionH / actionC; // 性价比 val
            List<Number> oneLine = new ArrayList<>(Arrays.asList(actionC, actionH, val));
            inputList.add(oneLine);
        }


        System.out.println(inputList);
        //TODO Arrays.sort(inputList, comp) --- 是适合数组的排序... 这个根本用不了...
        // 排了一个多小时的错 ....
        inputList.sort(new Comparator<List<Number>>() {
            @Override
            public int compare(List<Number> o1, List<Number> o2) {
                // 三目运算符 - 简化 if-else 逻辑
                //TODO 排序 intVal - 要有正有负, 1、0 就无法做到排序效果
                //return o1.get(0).floatValue() > o2.get(0).floatValue() ? 1 : 0;
                return o1.get(0).floatValue() > o2.get(0).floatValue() ? 1 : -1;
                // Comparator does not return 0 for equal elements
                // 即相等时, 元素无法返回 0, -1 比如代表大小交换,则此时交换也无妨, 只是会让排序变得 "不稳定", 如快排
            }
        });
        System.out.println(inputList);


    }


}

package history.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 74281
 * @create 2020/09/22
 * @description:
 */
public class findRepeatNumber {
    // 最好想到的解法 -> 遍历、集合存
    public int findRepeatNumber(int[] nums) {
        // key = number, value = count
        Set<Integer> numSet = new HashSet<>();
        int res = 0;
        for (int num: nums ){
            if ( !numSet.contains(num) ){
                numSet.add(num);
            }else{
                res =  num;
                break;
            }
        }
        return res;
    }

    /* 原地置换 - 时间 O(n)、空间 O(1)
        利用了题目中的: 一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
        如果没有一个数字重复的, 那么应该置换完, nums[i] = i, 即索引值与数组值一致
        如果有数字是重复的, 则置换时,就会发现, 原来的位置也是这个数, 直接 return 即可
    */
    public int findRepeatNumber2(int[] nums){
        int tmp = 0;

        for (int i=0; i<nums.length; i++){
            if ( nums[i] != i ){
                if ( nums[nums[i]] == nums[i] ){ return nums[i]; }
                tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }
}

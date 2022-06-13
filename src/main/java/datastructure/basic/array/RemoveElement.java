package datastructure.basic.array;

import java.util.Arrays;

/**
 * 27. 移除元素
 * https://leetcode.cn/problems/remove-element/
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 解题思路: 双指针
 *
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (validIndex(nums, right) && nums[right] == val){
                right--;
            }
            while (validIndex(nums, left) && nums[left] != val){
                left++;
            }
            if (left <= right){
                nums[left] = nums[right];
                nums[right] = val;
            }
        }
        return left;
    }

    public boolean validIndex(int[] nums, int idx){
        return nums != null && idx >= 0 && idx <= nums.length - 1;
    }

    public static void main(String[] args){
        int[] nums = new int[]{2};
        int val = 2;
        new RemoveElement().removeElement(nums, val);
    }
}

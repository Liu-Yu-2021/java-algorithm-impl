package history.leetcode.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 74281
 * @create 2020/10/06
 * @description: 全排列 - 回溯算法
 */
public class PermuteN {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> track = new ArrayList<>();

    private List<List<Integer>> permuteN(int[] nums){
        if ( nums == null || nums.length == 0 ){
            return res;
        }

        backtrack(nums, track);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> track) {
        if ( nums.length == track.size() ){
            res.add(new ArrayList<>(track));
            return;
        }

        for (int num: nums){
            if ( track.contains(num) ){
                continue;
            }

            track.add(num);
            backtrack(nums, track);
            track.remove(track.size()-1);
        }
    }

    @Test
    public void testPermuteN(){
        int[] arr = new int[]{1, 2, 3, 4};
        permuteN(arr);
        System.out.println(res.size());
        System.out.println(res);
    }


}

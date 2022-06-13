package history.leetcode.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 74281
 * @create 2020/10/12
 * @description: 401. 二进制手表
 * https://leetcode-cn.com/problems/binary-watch/
 */
public class ReadBinaryWatch {

    /*
    List<Integer> hours = Arrays.asList(1,2,4,8);
    List<Integer> minutes = Arrays.asList(1,2,4,8,16,32);
    List<String> res = new ArrayList<>();

    这样写还是比较复杂, 回溯时,就需要把它们变成两个 for-each 循环

    观察手表的情况: hour-4个灯, min-6个灯, 10个灯
        可以用一个二进制字符串比较 1000 000001 -> 代表某种亮2个灯的情况

    最终将二进制转化为字符串即可
    */
    final int COUNT = 10;
    StringBuilder track = new StringBuilder("0000000000");
    List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int num) {
        backtrack(num, track, 0);
        return res;
    }

    /*
    Q: 为什么会出现重复搜索 ?

    现象: num = 2, 答案 = 45 -> 实际搜素  = 90
          num = 3, 答案 = 120 -> 实际搜索 = 720

    DEBUG发现: num = 2 时,
        搜索应该是:
            11 101 1001 10001 ..  100000001
            【疑点】之后是 011 0101 01001 ...
        但实际上是:
            【疑点】之后是 01 -> 11 -> 011 .... 就是 01 之后的搜素, charAt(0) 位置不应该再搜索了... 而实际是搜索了的...

        解决办法, 添加一个参数,标记,从哪里继续搜索
     */
    private void backtrack(int num, StringBuilder track, int loopStart) {
        if ( countOne(track) == num && !res.contains(track.toString()) ){  // && !res.contains(track.toString())
            //不加 && !res.contains(track.toString() -> num:3 res.size=720; 默认规模 res.size=120
            res.add(track.toString());
            return;
        }

        for (int i = loopStart; i < COUNT; i++) {
            if ( track.charAt(i) != '0' ) {
                continue;
            }
            track.replace(i, i + 1, "1");

            backtrack(num, track, loopStart+1);

            track.replace(i, i+1, "0");
        }
    }

    private int countOne(StringBuilder track){
        int countOne = 0;
        for (int i=0; i<track.length(); i++){
            countOne += track.charAt(i)=='1'? 1:0;
        }
        return countOne;
    }

    @Test
    public void testWatch(){
        readBinaryWatch(2);
        System.out.println(res.size());
    }


}

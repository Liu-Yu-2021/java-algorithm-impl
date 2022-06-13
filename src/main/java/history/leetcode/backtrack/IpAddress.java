package history.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 74281
 * @create 2020/10/03
 * @description: https://leetcode-cn.com/problems/restore-ip-addresses/
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IpAddress {

    List<String> res = new ArrayList<>();
    List<String> track = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        /*
        由于 "0.011.255.245" 是无效的 IP 地址, 所以 String 长度<4 或 >12, 则一定没有有效的IP地址
         */
        if ( s.length() < 4 || s.length() > 12 ){
            return res;
        }

        //backtrack(track, s);
        return res;
    }

    /**
     *输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * @param track 回溯的路径
     * @param s 待选择的列表
     * @param begin
     * @param splitCount
     */
    private void backtrack(List<String> track, String s, int begin,int splitCount){
        if ( splitCount == 4 ){
            return;
        }

        for (int i=begin; i<s.length(); i++){

        }


    }

}

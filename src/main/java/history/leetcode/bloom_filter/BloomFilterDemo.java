package history.leetcode.bloom_filter;

import org.junit.Test;

//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.PrimitiveSink;

/**
 * @author 74281
 * @create 2020/10/08
 * @description: guava 布隆过滤器 简单使用
 */
public class BloomFilterDemo {

    @Test
    public void testBloomFilter(){
        /*
        Funnel, 这里使用了 lambda 表达式
        exceptedInsertions 插入总数
        fpp: 误判率

        'com.google.common.hash.PrimitiveSink' is marked unstable with @Beta
         */

        /*
        BloomFilter<Integer> bloomFilter = BloomFilter.create(
                // 将任意类型转化为 Java 基础类型, 默认转换为 byte[]
                (Integer from, PrimitiveSink PrimitiveSink) -> PrimitiveSink.putInt(from),
                100000L,
                0.1 // 0 - 1.0
        );

        // 向 BloomFilter 添加元素
        for (int i = 0; i < 10000; i++){
            bloomFilter.put(i);
        }
        // 检测元素是否 "可能" 存在 BloomFilter
        boolean might = bloomFilter.mightContain(66666);
        System.out.println("66666 might in bloomFilter >> "+might);
        */
    }



}

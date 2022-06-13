package history.datastructure;

/**
 * @author 74281
 * @create 2020/09/15
 * @description: 队列的实现 - 数组 / 链表
 *
 * 链表实现队列相对容易:
 *                      入队 = insertToLash
 *                      出队 = remove(index:0)
 *
 * 数组实现队列 - 这里实现循环队列
 * 【判断队列满的条件】 = ( 队尾下标 + 1 ) % 数组长度 = 队头下标
 */

/*
双端循环队列的数组下标 - 逻辑混乱
是 lastIndex 指向下一个赋值的位置,还是队列元素的最后一个,需要考虑清楚,再改代码

解决: 使用队列下一个赋值的位置

【队列满的条件】当队列中,仅有 last 指针是空时,认为队列是满的
【注】因此,队列的最大长度比内部的数组长度 小 1
*/
class testQueueImp{

    public void testQueueImpMethod(){
        /*
        队列的最大长度比内部的数组长度 小 1
        cap: 数组长度
         */
        QueueImp queue = new QueueImp(6);
        queue.inQueue(1);
        queue.inQueue(2);
        queue.inQueue(3);
        queue.inQueue(4);
        queue.inQueue(4);

        System.out.println("frontIdx: "+queue.frontIdx);
        System.out.println("lastIdx: "+queue.lastIdx);

        queue.IterQueue();
        System.out.println("queue is Full ? "+queue.isFull());

        queue.outQueue();
        int tmp = queue.outQueue();
        System.out.println(tmp);

        System.out.println("frontIdx: "+queue.frontIdx);
        System.out.println("lastIdx: "+queue.lastIdx);

        //queue.inQueue(99);
        queue.inQueue(128);
        System.out.println("after inQueue one number...");
        System.out.println("frontIdx: "+queue.frontIdx);
        System.out.println("lastIdx: "+queue.lastIdx);
        System.out.println("queue is Full ? "+queue.isFull());

        queue.IterQueue();
    }

    public static void main(String[] args) {
        testQueueImp test = new testQueueImp();
        test.testQueueImpMethod();
    }
}


public class QueueImp{

    private int[] arr;
    public int frontIdx;
    public int lastIdx;

    /**
     * @param capacity 队列的最大长度
     *                 队列的最大长度比内部的数组长度 小 1
     *                 数组长度 = capacity + 1
     */
    public QueueImp(int capacity){
        //队列的最大长度比内部的数组长度 小 1
        this.arr = new int[capacity];
        frontIdx = 0;
        lastIdx = frontIdx;
    }

    public boolean isFull() {
        /*
        至于是 +1、-1、+0, 跟队尾索引是否有元素有关,也可能不对
        需要自行分析, 或 举例尝试

        【队列满的条件】当队列中,仅有 last 指针是空时,认为队列是满的
        参考自小灰算法 P48
        */
        return ((lastIdx + 1) % arr.length == this.frontIdx);
    }

    private int addIdx(int idx) {
        idx++;
        if (idx == arr.length){
            idx = 0;
        }
        return idx;
    }

    public void inQueue(int element) {
        if (isFull()) {
            throw new RuntimeException("inQueue failed,because the queue is full.");
        }
        arr[lastIdx] = element;
        lastIdx = addIdx(lastIdx);
    }

    public int outQueue(){
        int res = arr[frontIdx];
        frontIdx = addIdx(frontIdx);
        return res;
    }

    public void IterQueue() {
        int start = frontIdx;
        int end = lastIdx;
        if (frontIdx < lastIdx) {
            for (int i = start; i <= end; i++) {
                System.out.println(arr[i]);
            }
        } else {
            for (int i = start; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
            if ( end != 0){
                for (int i = 0; i <= end; i++) {
                    System.out.println(arr[i]);
                }
            }
        }
        
    }
    
}

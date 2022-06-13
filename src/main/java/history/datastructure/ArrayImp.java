package history.datastructure;

import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/09/13
 * @description: 数据结构 - 数组的 CRUD

数组的特点:
    ① 【存储】在内存中如何存储的？ 顺序存储,内存中开辟一块连续空间
    ② 【使用】在使用该结构的特点？
            a.查询 Read - 可以随机访问(根据索引值), O(1), 因为顺序存储的特点
            b.修改 Update - 可以随机修改 O(1)
            c.插入 - 平均情况需要将插入位置后的元素移动 n/2 次, O(n)
            d.删除 - 平均情况需要将插入位置后的元素移动 n/2 次, O(n)

    总结: 因此数组不适合频繁插入、删除的场景, 适合频繁读取、修改值的场景

数组的操作方法:
    - read
    - update
    - insert >>
            尾部插入
            中间插入 - 数组插入点的右部分循环右移一位
            超范围插入 - 抛出异常、扩容
    - delete - 数组的插入点的右部分循环左移
            (被覆盖掉的,未保存数值的元素,就是被删掉的元素)
 */
public class ArrayImp {

    private int[] arr;
    private int size;     //数组的实际长度
    private int capacity; //数组的定义长度


    /**
     * 数组的创建
     * @param capacity 数组创建时的长度 - 当前的最大容量
     */
    public void createArray(int capacity){
        this.arr = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * 数组的初始化传值
     * @param args int类型可变参数数组 - 做初始化使用
     */
    public void initArray(int... args){
        for (int i=0; i<args.length; i++){
            arr[i] = args[i];
        }
        this.size = args.length;
    }

    // 普通代码块,做初始化使用
    {
        createArray(10);          //长度为10
        initArray(3,1,2,4,9,13,22); //7个值
    }

    //@Test
    public void testReadAndUpdate(){
        System.out.printf("Read Array: arr[3] = %d\n", arr[3]);
        arr[3] += 10;
        System.out.printf("Update Array: arr[3] = %d\n", arr[3]);
        System.out.printf("Array size: this.size = %d\n", this.size);
    }

    /**
     * 数组的插入操作
     * @param element 插入的元素值
     * @param index 插入元素的目的索引值
     * 插入元素可分为尾部插入、中间插入(非尾部)、超范围插入
     * 超范围插入,可选抛出异常 or 数组扩容操作
     */
    public void insert(int element, int index) {
        if (index < 0 || index > this.capacity) {
            //异常的索引值 <0 或 >最大容量 capacity
            throw new IndexOutOfBoundsException("index out of array range!");
        } else if (index == this.capacity) {
            //超范围插入 - 扩容后尾插
            //throw new IndexOutOfBoundsException("index out of array range!");
            this.arr = resize(arr);
            arr[size] = element;
        } else if (index == this.size) {
            //尾部插入
            arr[index] = element;
            size += 1;
        } else {
            //非尾部插入
            // 举例:7个元素,索引2处插入值,原2-6(5个)要往后错开
            // size个元素,索引index处插入值,原index->size-1往后错开,共size-index个
            for (int i = size; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = element;
            size += 1;
        }
    }

    public int[] resize(int[] array) {
        //10+10>>1 = 10  ====>  10+10=20 20>>1 右移一位 = /2 = 20/2 = 10
        this.capacity = array.length + (array.length >> 1);
        array = Arrays.copyOf(array, this.capacity); // this.capacity + this.capacity<<1
        return array;
    }

    public static void main(String[] args) {
        ArrayImp testObj = new ArrayImp();

        System.out.printf("capacity = %d, size = %d\n",testObj.capacity,testObj.size);
        System.out.println(Arrays.toString(testObj.arr));
        testObj.insert(0,0);//非尾部插入
        testObj.insert(10,7);//非尾部插入
        testObj.insert(10,testObj.size);//尾部插入

        System.out.println(Arrays.toString(testObj.arr));

        testObj.insert(55,testObj.size);//超范围插入

        System.out.println(Arrays.toString(testObj.arr));

    }

}
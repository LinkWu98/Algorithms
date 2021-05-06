package cn.link.search;

/**
 * 查找算法
 *
 * @author link
 * @version 1.0
 * @date 2021/5/6 9:03 下午
 */
public class SeqSearch {

    /**
     * 线性查找，就是简单的遍历查找
     * <p>
     * 复杂度:O(n)
     *
     * @return 下标
     */
    public static int search(int[] arr, int searchNum) {

        for (int i = 0; i < arr.length; i++) {
            if (searchNum == arr[i]) {
                return i;
            }
        }

        return -1;

    }

}

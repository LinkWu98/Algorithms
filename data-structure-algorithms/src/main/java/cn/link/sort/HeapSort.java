package cn.link.sort;

/**
 * 堆排序
 * <p>
 * 堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 * <p>
 * 堆是具有以下性质的完全二叉树：
 * 大顶堆：每个结点的值都大于或等于其左右孩子结点的值，arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]。
 * 小顶堆：每个结点的值都小于或等于其左右孩子结点的值，arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]。
 * 一般升序采用大顶堆，降序采用小顶堆
 * <p>
 * 注意 : 没有要求结点的左孩子的值和右孩子的值的大小关系。
 *
 * 建堆复杂度 O(N)
 * heapify复杂度 O(logN)
 * 堆排序对N个数进行heapify，复杂度 O(NlogN)
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/27 20:22
 */
public class HeapSort {

    /**
     * 堆排序的基本思想是：
     * 1. 建堆（将数组转化为一个逻辑上的堆 - 完全二叉树），此时，根节点就是最大值
     * 2. 维护堆的性质
     * 2. 将根节点与末尾元素进行交换，此时末尾就为最大值
     * 3. 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
     * <p>
     * 可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了.
     *
     * @param arr
     */
    public static void sort(int[] arr) {

        //1. 建堆，从最后一个非叶子节点开始 (arr.length - 1) / 2，往前维护每个子树(堆的性质)
        for (int i = arr.length / 2 - 1; i >= 0; i--)
            heapify(arr, arr.length, i);


        //2.堆排序，长度从
        for (int i = arr.length - 1; i > 0; i--) {
            //一次维护完毕后，堆顶为最大的元素，也就是下标 0 元素为最大的元素
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

    }

    /**
     * 维护堆的性质
     * <p>
     * 父节点：(i-1)/2
     * 左子节点：2*i+1
     * 右子节点：2*i+2
     *
     * @param arr    源数组
     * @param length 需要维护的长度（根据排序的次数，维护的长度会变短）
     * @param index  当前节点对应数组中的下标
     */
    private static void heapify(int[] arr, int length, int index) {

        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        //此逻辑为大顶堆，小顶堆改为 < 即可
        if (left < length && arr[left] > arr[largest])
            largest = left;

        if (right < length && arr[right] > arr[largest])
            largest = right;

        //如果最大值下标变化，则节点需要交换，同时也破坏了堆的性质，还需递归维护变化的子节点的所属子树
        if (largest != index) {

            swap(arr, largest, index);
            //这里的largest就是对应替换的子节点的下标
            heapify(arr, length, largest);

        }

    }

    private static void swap(int[] arr, int i1, int i2) {

        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;

    }

}

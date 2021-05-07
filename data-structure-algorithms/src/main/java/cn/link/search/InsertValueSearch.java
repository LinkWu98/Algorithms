package cn.link.search;

/**
 * 插值查找
 * <p>
 * 类似二分查找，但在分布较为数据之差较为平均的有序数组中，查找效率会比较高
 * <p>
 * 原理：自适应mid - 从arr[low]和arr[high]之间，找大概的位置，然后带入到具体的数组下标中找的大概的位置
 *
 * @author Link50
 * @version 1.0
 * @date 2021/5/7 20:27
 */
public class InsertValueSearch {

    /**
     * 插值查找
     *
     * 核心公式：mid = left + (searchNum - arr[left]) / (arr[right] - arr[left]) * (right - left)
     *
     * @param arr
     * @param searchNum
     * @return
     */
    public static int search(int[] arr, int searchNum) {

        //不做判断，计算自适应mid下标会越界
        if (searchNum > arr[arr.length - 1] || searchNum < arr[0]) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int mid;

        //自适应mid
        while ((mid = left + (searchNum - arr[left]) / (arr[right] - arr[left]) * (right - left)) != searchNum) {

             if (arr[mid] > searchNum) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if (left > right) {
                return -1;
            }
        }

        return mid;

    }

}

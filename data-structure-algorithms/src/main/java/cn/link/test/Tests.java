package cn.link.test;


import cn.link.common.ArrayUtil;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

public class Tests {

    public void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    public void selectSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int utmostIdx = 0;
            for (int j = 0; j <= i; j++) {
                if (arr[utmostIdx] < arr[j]) {
                     utmostIdx = j;
                }
            }
            if (utmostIdx != i) {
                ArrayUtil.swap(arr, utmostIdx, i);
            }
        }
    }

    public int[] insertionSort(int[] arr) {

        int[] sortedArr = new int[arr.length];
        sortedArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int currentIdx = i;
            while (currentIdx > 0 && sortedArr[currentIdx - 1] > arr[i]) {
                sortedArr[currentIdx] = sortedArr[currentIdx - 1];
                currentIdx--;
            }
            sortedArr[currentIdx] = arr[i];
        }

        return sortedArr;

    }

    public void shellSort(int[] arr) {

        //步长 从length / 2 开始
        for (int pace = arr.length / 2; pace > 0 ; pace /= 2) {
            //每一组的第二个元素开始
            for (int i = pace; i < arr.length; i++) {
                int currentIdx = i;
                int currentNum = arr[currentIdx];
                while (currentIdx - pace >= 0 && currentNum < arr[currentIdx - pace]) {
                    arr[currentIdx] = arr[currentIdx - pace];
                    currentIdx -= pace;
                }
                arr[currentIdx] = currentNum;
            }
        }

    }

    //TODO
    public void quickSort(int[] arr, int left, int right) {

        int pivot = arr[left];
        int currentLeft = left;
        int currentRight = right;

        while (currentLeft < currentRight) {

            while (currentLeft != currentRight && pivot <= arr[currentRight]) {
                currentRight--;
            }

            ArrayUtil.swap(arr, currentLeft, currentRight);

            while (currentLeft != currentRight && pivot >= arr[currentLeft]) {
                currentLeft++;
            }

            ArrayUtil.swap(arr, currentLeft, currentRight);

        }

        arr[currentLeft] = pivot;

        if (currentLeft - left > 1) {
            quickSort(arr, left,  currentLeft - 1);
        }

        if (right - currentRight > 1) {
            quickSort(arr, currentRight + 1,  right);
        }

    }

    @Test
    public void test() throws Exception{
        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //bubbleSort(arr);
        //selectSort(arr);
        //arr = insertionSort(arr);
        //shellSort(arr);
        //quickSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

    }

}

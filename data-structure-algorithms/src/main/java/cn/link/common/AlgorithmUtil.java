package cn.link.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法工具
 *
 * @author link
 * @version 1.0
 * @date 2021/4/19 8:18 下午
 */
public class AlgorithmUtil {

    /**
     * 回溯实现排列组合(不重复)
     *
     * @param nums        需要排列组合的数字
     * @param permutation 最终的排列组合
     * @param tempNums    零时组合
     */
    public static void permute(List<List<Integer>> permutation, List<Integer> tempNums, int[] nums) {

        if (tempNums.size() == nums.length) {
            //完成一种组合
            permutation.add(new ArrayList<>(tempNums));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (tempNums.contains(nums[i])) {
                continue;
            }

            tempNums.add(nums[i]);
            permute(permutation, tempNums, nums);
            //在完成一种组和后执行到这里，会移除当前一轮的那一位数字，从而达到遍历到数组中每一个元素的效果
            tempNums.remove(tempNums.size() - 1);

        }

    }

}

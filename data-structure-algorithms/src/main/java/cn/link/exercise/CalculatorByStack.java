package cn.link.exercise;

import cn.link.data.structure.linkedlist.Stack;

import java.math.BigDecimal;

/**
 * 栈实现综合计算器
 *
 * @author link
 * @version 1.0
 * @date 2021/4/7 4:49 下午
 */
public class CalculatorByStack {

    String numRegex = "^[0-9]*$";

    private final Stack<String> numStack = new Stack<>();

    private final Stack<String> opStack = new Stack<>();

    /*
        (7-2*2-5+1)-6/3-3
        数字栈和符号栈
        首先定义什么是数字，让其识别
     */
    public BigDecimal calculate(String expr) {

        boolean calculateNowFlag = false;
        char[] chars = expr.toCharArray();

        for (char c : chars) {

            if (c == '(') {
                calculateNowFlag = true;
            } else if (c == ')') {
                //先计算
            }


        }

        return null;

    }



}

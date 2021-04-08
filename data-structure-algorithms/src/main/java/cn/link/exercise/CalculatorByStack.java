package cn.link.exercise;

import cn.link.data.structure.linkedlist.Stack;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 栈实现综合计算器
 *
 * @author link
 * @version 1.0
 * @date 2021/4/7 4:49 下午
 */
public class CalculatorByStack {

    private static final String NUM_REGEX = "^[0-9]*$";

    /*
        例如: 6+1+1/3-(7-2*2)

        1. 符号栈为空，直接入符号栈
        2. 符号栈不为空，比较优先级，优先级<=前者，先算前面的，优先级>前者，放入数字后，算后面的
        符号栈size =  1, 数字栈 size = 2 时就可以计算了

     */
    public BigDecimal calculate(String expr) {

        //数字栈
        Stack<String> numStack = new Stack<>();
        //符号栈
        Stack<String> opStack = new Stack<>();
        StringBuilder calculateFirstExpr = new StringBuilder();

        //括号先计算
        boolean calculateFirstFlag = false;
        char[] chars = expr.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            String char2Str = String.valueOf(chars[i]);

            //6+1+1/3
            boolean isNumFlag = isNum(chars[i]);

            if (isNumFlag) {
                //数字直接入栈
                numStack.push(char2Str);
            } else {
                //符号
                if (opStack.size() == 0) {
                    opStack.push(char2Str);
                }

            }

            //1. 括号先计算，都放到一个表达式中
            if (chars[i] == '(') {
                calculateFirstFlag = true;
            } else if (chars[i] == ')') {
                calculateFirstFlag = false;
                BigDecimal result = calculate(calculateFirstExpr.toString());
                //计算结果入栈
                numStack.push(result.toString());
            }

            if (calculateFirstFlag) {
                calculateFirstExpr.append(chars[i]);
                continue;
            }




        }

        return null;

    }

    /**
     * 判断是否为数字
     */
    public boolean isNum(char param) {

        CharSequence charSequence = String.valueOf(param);
        Matcher matcher = Pattern.compile(NUM_REGEX).matcher(charSequence);
        return matcher.matches();

    }

}

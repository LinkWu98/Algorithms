package cn.link.exercise;

import cn.link.data.structure.linkedlist.Stack;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * 优先级 map
     */
    private static final Map<String, Integer> OP_PRIORITY_MAP = new HashMap<>();

    private static final String NUM_REGEX = "^[0-9]*$";

    static {
        OP_PRIORITY_MAP.put("+", 0);
        OP_PRIORITY_MAP.put("-", 0);
        OP_PRIORITY_MAP.put("*", 1);
        OP_PRIORITY_MAP.put("/", 1);
    }

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

            String currentOp = String.valueOf(chars[i]);

            //6+1+1/3-(7-2*2)
            boolean isNumFlag = isNum(chars[i]);

            if (isNumFlag) {

                //数字
                numStack.push(currentOp);
                //当数字达到三个时，需要将后两个数字进行计算 (判断过第二个运算符优先级大于第一个)
                if (numStack.size() == 3) {
                    this.calculateLastTwoAndPush(numStack, opStack.pop());
                }

            } else {

                //符号
                //括号，需先计算，都放到一个表达式中
                if (chars[i] == '(') {
                    calculateFirstFlag = true;
                } else if (chars[i] == ')') {
                    calculateFirstFlag = false;
                    //括号内累加完毕计算结果入栈
                    BigDecimal result = this.calculate(calculateFirstExpr.toString());
                    numStack.push(result.toString());
                }

                //括号内持续累加中...
                if (calculateFirstFlag) {
                    calculateFirstExpr.append(chars[i]);
                    continue;
                }

                if (opStack.size() == 0) {
                    opStack.push(currentOp);
                    continue;
                }

                //已存在符号，进行优先级比较
                String prevOp = opStack.pop();
                //当前的还是要 push 进去的
                opStack.push(currentOp);
                Integer prevPriority = OP_PRIORITY_MAP.get(prevOp);
                Integer currentPriority = OP_PRIORITY_MAP.get(currentOp);

                //<=就直接计算前两个数字,计算完了再放进去
                if (currentPriority <= prevPriority) {
                    this.calculateLastTwoAndPush(numStack, prevOp);
                    continue;
                }

            }

        }

        //最后，有两个数字，一个符号
        return this.executeCalculate(opStack.pop(), numStack.pop(), numStack.pop());

    }

    private void calculateLastTwoAndPush(Stack<String> numStack, String operator) {

        String secondNum = numStack.pop();
        String firstNum = numStack.pop();
        BigDecimal result = this.executeCalculate(operator, secondNum, firstNum);
        //将计算的结果放入数字栈
        numStack.push(result.toString());

    }

    private BigDecimal executeCalculate(String operator, String secondNum, String firstNum) {

        BigDecimal result = new BigDecimal("");

        switch (operator) {

            case "+":
                result = new BigDecimal(firstNum).add(new BigDecimal(secondNum));
                break;
            case "-":
                result = new BigDecimal(firstNum).subtract(new BigDecimal(secondNum));
                break;
            case "*":
                result = new BigDecimal(firstNum).multiply(new BigDecimal(secondNum));
                break;
            case "/":
                result = new BigDecimal(firstNum).divide(new BigDecimal(secondNum), 2, BigDecimal.ROUND_HALF_UP);
                break;

        }

        return result;

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

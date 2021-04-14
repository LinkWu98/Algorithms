package cn.link.exercise;

import cn.link.data.structure.linkedlist.Stack;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 栈实现综合计算器
 * <p>
 * 核心思路：判断符号优先级，<= 前者就前两个数字可以计算了，>前者就先计算后两个数字，括号也同样逻辑即可
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

    /**
     * 括号数量
     */
    private static int calculateFirstFromCount = 0;

    private static int calculateFirstToCount = 0;

    static {
        OP_PRIORITY_MAP.put("+", 0);
        OP_PRIORITY_MAP.put("-", 0);
        OP_PRIORITY_MAP.put("*", 1);
        OP_PRIORITY_MAP.put("/", 1);
    }


    ///**
    // *
    // * 逆波兰计算器
    // * 中缀: 4 * 3 + 2 + 1
    // * 后缀: 4 3 * 2 + 1 +
    // * @param reversePolishNotation 逆波兰表达式
    // * @return
    // */
    //public BigDecimal calculateByRPN(String reversePolishNotation) {
    //
    //
    //
    //}

    /**
     * 中缀表达式转后缀表达式
     * <p>
     * 1+((2+3)*4)-5
     * <p>
     * 如何做到括号先计算的，括号内
     *
     * @param commonExpr 中缀表达式
     * @return
     */
    public String commonToRPN(String commonExpr) {

        Stack<String> opStack = new Stack<>();
        Stack<String> overallStack = new Stack<>();
        boolean bracketFlag = false;

        for (char character : commonExpr.toCharArray()) {

            boolean isNumFlag = isNum(character);
            String currentChar = String.valueOf(character);

            //数字直接入栈
            if (isNumFlag) {
                overallStack.push(currentChar);
                continue;
            }

            //符号做判断
            //1. 为空、(，直接入栈
            if (opStack.size() == 0 || "(".equals(currentChar)) {
                opStack.push(currentChar);
                continue;
            }

            //2. ),开始弹栈到主栈，到(结束
            if (currentChar.equals(")")) {
                while (!"(".equals(opStack.peek())) {
                    overallStack.push(opStack.pop());
                }
                //舍弃 (
                opStack.pop();
                continue;
            }

            //前一个符号是括号，不比较
            if ("(".equals(opStack.peek())) {
                opStack.push(currentChar);
                continue;
            }

            //3. 遇到其他运算符，比较优先级，高入符号栈，<=就将栈顶弹到主栈，然后继续与栈顶比较，重复此比较动作
            this.compareOpPriorityAndPush(currentChar, opStack, overallStack);

        }

        while (opStack.size() > 0) {
            overallStack.push(opStack.pop());
        }

        overallStack.reverse();
        return overallStack.toString();

    }

    /**
     * 逆波兰表达式比较符号优先级操作
     *
     * 优先级高保留，<=就将栈顶弹到主栈，然后继续与栈顶比较，重复此比较动作
     *
     * @param currentOp
     * @param opStack
     * @param overallStack
     */
    private void compareOpPriorityAndPush(String currentOp, Stack<String> opStack, Stack<String> overallStack) {

        while (opStack.size() > 0) {

            if ("(".equals(opStack.peek())) {
                return;
            }

            Integer prevOpPriority = OP_PRIORITY_MAP.get(opStack.peek());
            Integer currentPriority = OP_PRIORITY_MAP.get(currentOp);
            if (currentPriority > prevOpPriority) {
                opStack.push(currentOp);
                return;
            } else {
                overallStack.push(opStack.pop());
            }

        }

        opStack.push(currentOp);

    }

    /**
     * 中缀表达式 -> 后缀表达式
     * <p>
     * 例如: 6+1/3-(7-2*2)
     * 1. 符号栈为空，直接入符号栈
     * 2. 符号栈不为空，比较优先级，优先级<=前者，先算前面的，优先级>前者，放入数字后，算后面的，当符号栈size =  1, 数字栈 size = 2 时就可以计算了
     * <p>
     * 这就是逆波兰表达式的计算思想，Reserve Polish Notation，只不过实际逆波兰只要使用一个栈就可以了
     *
     * @param expr 中缀表达式
     * @return
     */
    public BigDecimal calculate(String expr) {

        //数字栈
        Stack<String> numStack = new Stack<>();
        //符号栈
        Stack<String> opStack = new Stack<>();

        //括号先计算
        StringBuilder calculateFirstExpr = new StringBuilder();
        boolean calculateFirstFlag = false;
        boolean prevCharIsNumFlag = false;
        char[] chars = expr.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            String currentChar = String.valueOf(chars[i]);

            boolean isNumFlag = isNum(chars[i]);

            if (isNumFlag) {

                //若上一个字符就是数字，多位数，拼接
                if (!calculateFirstFlag && prevCharIsNumFlag) {
                    String prevNum = numStack.pop();
                    currentChar = prevNum.concat(currentChar);
                }

                prevCharIsNumFlag = true;

                //括号内持续累加中...
                if (calculateFirstFlag) {
                    calculateFirstExpr.append(currentChar);
                    continue;
                }

                //数字
                numStack.push(currentChar);
                //当数字达到三个时，需要将后两个数字进行计算 (判断过第二个运算符优先级大于第一个时才会继续，放入第三个数字)
                if (numStack.size() == 3) {
                    this.calculateLastTwoAndPush(numStack, opStack.pop());
                }

            } else {

                prevCharIsNumFlag = false;

                //符号
                //遇到括号，需先计算，都放到一个表达式中
                if (chars[i] == '(') {
                    calculateFirstFromCount++;
                    if (calculateFirstFlag) {
                        //括号内括号，继续拼接
                        calculateFirstExpr.append(chars[i]);
                    }
                    calculateFirstFlag = true;
                    continue;
                } else if (chars[i] == ')') {
                    calculateFirstToCount++;
                    //多个前括号，到后括号不能提前结束进入计算
                    if (calculateFirstFromCount != calculateFirstToCount) {
                        calculateFirstExpr.append(chars[i]);
                        continue;
                    }

                    calculateFirstFlag = false;
                    //括号内累加完毕计算结果入栈
                    BigDecimal result = this.calculate(calculateFirstExpr.toString());
                    //括号内表达式计算结束后清空字符串
                    calculateFirstExpr = new StringBuilder("");
                    numStack.push(result.toString());
                    if (numStack.size() == 3) {
                        this.calculateLastTwoAndPush(numStack, opStack.pop());
                    }
                    continue;
                }

                //括号内持续累加中...
                if (calculateFirstFlag) {
                    calculateFirstExpr.append(chars[i]);
                    continue;
                }

                if (opStack.size() == 0) {
                    opStack.push(currentChar);
                    continue;
                }

                //已存在符号，进行优先级比较 (bug记录:此处若pop，然后优先级小于后者，最后计算会缺少运算符，空指针)
                String prevOp = opStack.peek();
                Integer prevPriority = OP_PRIORITY_MAP.get(prevOp);
                Integer currentPriority = OP_PRIORITY_MAP.get(currentChar);

                //<=就直接计算前两个数字,计算完了再放进去
                if (currentPriority <= prevPriority) {
                    this.calculateLastTwoAndPush(numStack, opStack.pop());
                }

                //当前的还是要 push 进去的
                opStack.push(currentChar);

            }

        }

        if (numStack.size() == 1) {
            return new BigDecimal(numStack.pop());
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

        BigDecimal result = new BigDecimal("0");

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

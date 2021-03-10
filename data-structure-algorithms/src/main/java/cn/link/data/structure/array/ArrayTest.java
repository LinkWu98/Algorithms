package cn.link.data.structure.array;

import org.junit.Test;

/**
 * @author Link50
 * @version 1.0
 * @date 2021/3/10 17:25
 */
public class ArrayTest {

    @Test
    public void testStack(){

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }

}

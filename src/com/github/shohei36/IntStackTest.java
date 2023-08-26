package com.github.shohei36;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class IntStackTest {
    @Test
    void testStack() {
        IntStack stack = new IntStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(stack.pop(), 3); // 3
        stack.push(4);
        stack.push(5);
        assertEquals(stack.pop(), 5); // 5
        assertEquals(stack.pop(), 4); // 4
        assertEquals(stack.pop(), 2); // 2
        stack.push(6);
        assertEquals(stack.pop(), 6); // 6
        assertEquals(stack.pop(), 1); // 1
    }
}

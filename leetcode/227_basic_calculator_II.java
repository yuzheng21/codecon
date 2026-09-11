class Solution {
    // solution: one stack
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            // skip trailing whitespace like "3/2 "
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else {
                    // process * and / immediately
                    int prevNumber = stack.pop();
                    if (operation == '*') {
                        stack.push(prevNumber * currentNumber);
                    } else if (operation == '/') {
                        stack.push(prevNumber / currentNumber);
                    }
                }

                // reset state
                currentNumber = 0;
                operation = c;
            }
        }

        int ret = 0;
        while (!stack.isEmpty()) {
            ret += stack.pop();
        }

        return ret;
    }

    // solution2: one stack
    public int calculate(String s) {
        Deque<Integer> number = new ArrayDeque<>();
        char op = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // empty space
            if (c == ' ') continue;

            // number
            if (isNumber(c)) {
                int cur = c - '0';
                // handle multi-digit
                while (i + 1 < s.length() && isNumber(s.charAt(i + 1))) {
                    cur = cur * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }

                // change - to + (-number), so that all operators left are +, which can be evaluated from right to left
                if (op == '-') {
                    cur = -cur;
                    op = 0;
                }

                number.push(cur);

                // eval * and /
                if (op == '*' || op == '/') {
                    int b = number.pop();
                    int a = number.pop();
                    number.push(cal(a, b, op));
                }

            } else {
                // operator
                op = c;
                // evaluate previous + or -
                if ((c == '+' || c == '-') && number.size() > 1) {
                    int b = number.pop();
                    int a = number.pop();
                    number.push(a + b);
                }
            }
        }

        if (number.size() > 1) {
            int b = number.pop();
            int a = number.pop();
            number.push(a + b);
        }

        return number.pop();
    }

    // solution1: two stack
    public int calculate(String s) {
        Deque<Integer> number = new ArrayDeque<>();
        Deque<Character> operator = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // empty space
            if (c == ' ') continue;

            // number
            if (isNumber(c)) {
                int cur = c - '0';
                // handle multi-digit
                while (i + 1 < s.length() && isNumber(s.charAt(i + 1))) {
                    cur = cur * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                number.push(cur);

                // eval * and /
                if (!operator.isEmpty() && (operator.peek() == '*' || operator.peek() == '/')) {
                    int b = number.pop();
                    int a = number.pop();
                    number.push(cal(a, b, operator.pop()));
                }

            } else {
                // current operator is + or -
                if (c == '+' || c == '-') {
                    // eval previous + and -
                    if (!operator.isEmpty() && (operator.peek() == '+' || operator.peek() == '-')) {
                        int b = number.pop();
                        int a = number.pop();
                        number.push(cal(a, b, operator.pop()));
                    }
                }

                // operator
                operator.push(c);
            }
        }

        while (!operator.isEmpty()) {
            int b = number.pop();
            int a = number.pop();
            number.push(cal(a, b, operator.pop()));
        }

        return number.pop();
    }

    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    private int cal(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        return a / b;
    }
}

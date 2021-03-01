public class Evaluation {

    public ArrayList<String> infix_to_suffix(String s) { // convert to postfix ArrayList of tokens using Shunting-Yard
                                                         // Algorithm
        ArrayList<String> RPN = new ArrayList();
        int n = 0, operand = 0;
        Stack<String> ops = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c) && n > 0) {
                RPN.add(Integer.toString(operand));
                n = 0;
                operand = 0;
            }
            if (c == ' ') {
                continue;
            } else if (c == '(') {
                ops.add(Character.toString(c));
            } else if (isOp(Character.toString(c))) { // operator + or -
                while (ops.size() > 0 && isOp(ops.peek())) {
                    RPN.add(ops.pop());
                }
                ops.add(Character.toString(c));
            } else if (c == ')') {
                while (ops.size() > 0 && !(ops.peek().equals("("))) {
                    RPN.add(ops.pop());
                }
                ops.pop();
            } else { // number
                n++;
                operand *= 10;
                operand += (int) c - '0';
            }
        }
        if (n > 0)
            RPN.add(Integer.toString(operand));
        while (ops.size() > 0)
            RPN.add(ops.pop());
        return RPN;
    }

    public int evaluateRPN(ArrayList<String> RPN) {
        Stack<Integer> intStack = new Stack();
        Stack<String> opStack = new Stack();
        for (String c : RPN) {
            if (isOp(c)) {
                int right = intStack.pop();
                int left = intStack.pop();
                if (c.equals("+")) {
                    intStack.push(left + right);
                } else if(c.equals("-")){
                    intStack.push(left - right);
                } else if(c.equals)
            } else {
                intStack.push(Integer.valueOf(c));
            }
        }
        return intStack.pop();
    }

    public boolean isOp(String c) {
        return c.equals("+") || c.equals("-");
    }
}

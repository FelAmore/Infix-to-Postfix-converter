import java.util.Stack;
import java.util.Scanner;

// infix to postfix with operator +,-,*,/
public class Main {
    public static int precedence(char operator) { // setting the operator precedence
        switch(operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static String infixToPostfix(String infix) { // function to convert infix to postfix
        Stack<Character> stack = new Stack<Character>();
        String postfix = new String("");

        for (int i=0; i<infix.length(); i++) {
            char ch = infix.charAt(i);
            if (Character.isLetter(ch))
                postfix += ch;
            else if (ch == '(')
                stack.push(ch);
            else if (ch == ')') {
                while (stack.peek() != '(') {
                    postfix += stack.peek();
                    stack.pop();
                }
                stack.pop();
            }
            else if (ch == ' ') {} // to skip the space
            else {
                while (!stack.empty() && stack.peek() != '(' && precedence(stack.peek()) >= precedence(ch)) {
                    postfix += stack.peek();
                    stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.empty()) { // used when done looping through the infix but stack is not empty yet
            postfix += stack.peek();
            stack.pop();
        }

        return postfix;
    }

    public static boolean check (String infix) { // to check whether the infix expression is valid or not
        int n = 0;
        int m = 0;
        boolean b;
        for (int i = 0; i<infix.length(); i++) {
            char check = infix.charAt(i);
            if (check == '(')
                n++;
            if (check == ')')
                m++;
        }
        if (n != m)
            b = true;
        else
            b = false;
        return b;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String infix = scan.nextLine();
        if (check(infix) == true)
            System.out.println("Expression is not valid");
        else {
            String postfix = infixToPostfix(infix);
            System.out.println("Infix   : " + infix);
            System.out.println("Postfix : " + postfix);
        }
    }
}
def eval(postfix):
    stack = []
    for token in postfix:
        if token == '+':
            a = stack.pop()
            b = stack.pop()

            stack.append(a + b)
        elif token == '-':
            b = stack.pop()
            a = stack.pop()

            stack.append(a - b)
        elif token == '*':
            a = stack.pop()
            b = stack.pop()

            stack.append(a * b)
        elif token == '/':
            b = stack.pop()
            a = stack.pop()

            stack.append(a // b)
        else:
            stack.append(token)

    return stack[-1]

# Use this function to calculate the infix expresssion s
def calculate(s):
    stack = []
    # postfix result
    output = []

    rank = {
        '+': 1,
        '-': 1,
        '*': 2,
        '/': 2,
    }

    # None means no number is parsed
    num = None

    for c in s:
        if c == ' ':
            continue

        if c in "4":
            if num is None:
                num = 0
            num = num * 10 + int(c)
            continue

        # c is opeartor or ()
        # output prev num to output
        if num is not None:
            output.append(num)
            num = None

        if c == '(':
            stack.append('(')
        elif c == ')':
            # pop until (
            while stack[-1] != '(':
                output.append(stack.pop())

            # pop (
            stack.pop()
        else:
            # pop all aperator with higher or equal rank in stack, until meet (
            while stack and stack[-1] != '(' and rank[stack[-1]] >= rank[c]:
                output.append(stack.pop())

            # push cur operator
            stack.append(c)

    # handle leftover
    if num != None:
        output.append(num)
    while stack:
        output.append(stack.pop())

    return eval(output)

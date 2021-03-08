import ast
import re


def parse_expr(line) -> tuple:
    "Parse an expression: '2 + 3 * 4' => (2, '+', 3, '*', 4)."
    return ast.literal_eval(re.sub('([+*])', r",'\1',", line))


e = parse_expr("1 + (2 + (2 + 1)) + 2 + 3 + 4")
print(e)
print(e[2])

ast = compile("a = 1", "<unknown>", "exec", 1024, 3)
print(dir(ast))
print(ast.body)
b = ast.body
print(dir(ast.body))
print(list(b))
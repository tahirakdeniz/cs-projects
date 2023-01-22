import sys

base = 85
exponent = 92
result_str = str(base**exponent)

output = f"{base}^{exponent} = " + result_str

while len(result_str) > 1:

    numbers = [number for number in result_str]
    result_str = str(sum(int(i) for i in numbers))
    output += " = " + " + ".join(numbers) + " = " + result_str


print(output)
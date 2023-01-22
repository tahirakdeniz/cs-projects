import sys

S = sys.argv[1].split(",")

even_numbers = []

sum_even = 0
sum_all = 0

for i in S:
    number = int(i)
    if not number < 0:
        sum_all += number
        if number%2 == 0:
            sum_even += number
            even_numbers.append(i)

print(f"Even Numbers:  \"{','.join(even_numbers)}\" ")
print(f"Sum of Even Numbers: {sum_even}")
print("Even Number Rate: {:.3f} ".format(sum_even/sum_all))
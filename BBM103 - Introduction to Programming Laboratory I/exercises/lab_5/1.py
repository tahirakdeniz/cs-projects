n = int(input("Please enter a number: "))

odd_sum, even_sum = 0, 0

for i in range(1, n+1):
    if i%2 == 0:
        even_sum += i
    else:
        odd_sum += i

even_avg = even_sum / (n//2)

print("odd_sum : ",odd_sum," even_avg = " , even_avg)
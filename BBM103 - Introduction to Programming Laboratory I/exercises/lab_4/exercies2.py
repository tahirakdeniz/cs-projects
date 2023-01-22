a = 1
b = int(input("b: "))
c = int(input("c: "))
# -b - + sqrt(b^2 - 4a*c) / 2a a = 1
d = b**2 - 4*c
if d > 0:
    print( (-b - d**(1/2)) / 2*a)
    print( (-b + d**(1/2)) / 2*a)
elif d == 0:
    print( (-b - d**(1/2)) / 2*a)
else:
    print("There isn't any real root")
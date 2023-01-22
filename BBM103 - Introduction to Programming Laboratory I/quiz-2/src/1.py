import sys

a = int(sys.argv[1])
b = int(sys.argv[2])
c = int(sys.argv[3])

discriminant = b**2 - 4*a*c

if discriminant > 0:
    print("There are two solutions")
    print("Solution(s): {:.2f} {:.2f}".format((-b + discriminant**(1/2))/(2*a), (- b - discriminant**(1/2))/(2*a)))
elif discriminant < 0:
    print("There is no real solution")
else:
    print("There are two repeated solutions")
    print("Solution(s): {:.2f} ".format(-b/(2*a)))
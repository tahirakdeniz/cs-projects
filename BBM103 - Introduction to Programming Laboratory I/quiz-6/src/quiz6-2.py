import sys

size = int(sys.argv[1])
width = 2*size + 1
print("\n".join([((2*i + 1)*"*").center(width) for i in range(size)]))
print("\n".join([((2*i - 1)*"*").center(width) for i in range(size - 1, 0, -1)]))


import sys

def printDiamond(size):
    
    def upperDiamondPrint(n):
        if n == 1:
            return "*".center(width)
        elif n > 1:
            return upperDiamondPrint(n-1).center(width) + "\n" + ((2*n-1)*"*").center(width)
    
    def lowerDiamondPrint(n):
        if n == 1:
            return "*".center(width) 
        elif n > 1:
            return (((2*n-1)*"*").center(width) + "\n" + lowerDiamondPrint(n-1).center(width))

    width = 2*size - 1
    
    print()
    print(upperDiamondPrint(size))
    print(lowerDiamondPrint(size - 1))

printDiamond(int(sys.argv[1]))
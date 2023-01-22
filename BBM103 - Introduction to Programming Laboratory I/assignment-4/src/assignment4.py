import sys

# Define functions: 

def getBoardFromFile():
    """ Returns board matrix (by line by) as an list that includes the lines of board. """
    
    try:
        with open(sys.argv[1]) as inputFile:
            return [line.strip().split(" ") for line in inputFile]
    
    except IOError as err:
        print(f"file: {err.filename} not found.")
    
    except IndexError:
        print("Please enter one system argument")


def isGameContinuing():
    """
    Returns True if the game is continuing, else returns false.
    Condition: If there is no cell which has no neighbor with ball of the same color and also there is no bomb(x) in a cell, it means that the game is over.  
    """

    for rowIndex in range(len(rowsOfBoard)):
        for colIndex in range(len(rowsOfBoard[rowIndex])):
            cellColor = rowsOfBoard[rowIndex][colIndex]
            if cellColor == 'X':
                return True
            elif cellColor != ' ':
                if len(sameColorNeighbours(rowIndex, colIndex)) != 0:
                    return True
    return False
                 

def sameColorNeighbours(rowIndex, colIndex):
    
    """
    returns a set of indexes that includes neighbours of index which is same color.
    """

    NeighboursIndexes = {(rowIndex + 1, colIndex), (rowIndex - 1, colIndex), (rowIndex, colIndex + 1), (rowIndex, colIndex - 1)}
    
    if colIndex == 0:
        NeighboursIndexes = NeighboursIndexes - {(rowIndex, colIndex - 1)}
    
    if colIndex == len(rowsOfBoard[rowIndex]) - 1:
        NeighboursIndexes = NeighboursIndexes - {(rowIndex, colIndex + 1)}
    
    if rowIndex == 0:
        NeighboursIndexes = NeighboursIndexes - {(rowIndex - 1, colIndex)}
    
    if rowIndex == len(rowsOfBoard) - 1:
        NeighboursIndexes = NeighboursIndexes - {(rowIndex + 1, colIndex)}

    for rowI, colI in NeighboursIndexes:
        if rowsOfBoard[rowI][colI] != rowsOfBoard[rowIndex][colIndex]:
            NeighboursIndexes = NeighboursIndexes - {(rowI, colI)}

    return NeighboursIndexes


def indexesOfImmediateNeighbours(*args, indexesToDelete):
    """ Returns a set of indexes of immediate neighbours. """
    
    # Note:
    # Base case: When The index doesn't have any neighbour which is different from other indexes to delete and is same color with the index, functions doesn't returns recursion.
    
    for index in args:
        indexesToDelete.add(index)
        indexesToDelete = indexesToDelete | indexesOfImmediateNeighbours(*(sameColorNeighbours(*index) - indexesToDelete), indexesToDelete=indexesToDelete) 

    return indexesToDelete


def sameRowAndColumns(rowIndex, colIndex):
    """ returns a set that includes all indexes whose row is the same as rowIndex or column is the same as colIndex """
    return {(r, colIndex) for r in range(len(rowsOfBoard)) if rowsOfBoard[r][colIndex] != " " } | {(rowIndex, c) for c in range(len(rowsOfBoard[rowIndex])) if rowsOfBoard[rowIndex][c] != " "}


def indexesOfExplodedCells(*args, indexesToDelete):
    """ Returns indexes of All Exploded Cells by X; when a x is exploded it explodes other x es an so on. """
    
    for rowI, colI in args:
        indexesToDelete.add((rowI, colI))
        if rowsOfBoard[rowI][colI] == "X":
            indexesToDelete = indexesToDelete | indexesOfExplodedCells(*(sameRowAndColumns(rowI, colI) - indexesToDelete), indexesToDelete=indexesToDelete)
    
    return indexesToDelete


def printBoard():
    """ Prints board """
    print()
    for row in rowsOfBoard:
        for item in row:
            print(item, end="  ")
        print()
    

def getValidIndexFromUser():
    """ Get valid index from user and returns index as a tuple. 
    Valid Index: (numbers speparated by space), otherwise the function gives an error message."""
    
    while True:

        try:
            givenRowIndex, givenColumnIndex = map(int, input("Please enter a row and column number: ").strip().split(" "))
            assert rowsOfBoard[givenRowIndex][givenColumnIndex] != " "
        except Exception:
            print("Please enter a valid index.") 
        else:
            break
    
    return (givenRowIndex, givenColumnIndex)


def movedCellsToEmptyOnBoard():

    """ returns new rowsOfBoard whose items fall down and moved to the empty cells. """
    
    def transposeList(_list):
        """returns tranposed list."""
        return list(map(list, zip(*_list)))
    

    def sortedColumnToMoveCells(column):
        """ sort column the way empty lists goes to top"""
        
        def sortingKeyToMoveCells(enumeratedTuple):
            """ if item is empty makes returns -1 otherwise returns their orginal index. """
            
            if enumeratedTuple[1] == " ":
                return -1
            else:
                return enumeratedTuple[0]
        
        return [i[1] for i in sorted(enumerate(column), key=sortingKeyToMoveCells)]


    return transposeList([sortedColumnToMoveCells(column) for column in transposeList(rowsOfBoard)])
    

def deleteCompletelyEmptyRowsAndColums():
    """ Deletes rows and columns that are completely empty, doesn't includes any letter. returns None. """

    # Delete Completely Empty Rows
    rowIndex = 0
    while rowIndex < len(rowsOfBoard):
        if rowsOfBoard[rowIndex].count(" ") == len(rowsOfBoard[0]):
            del rowsOfBoard[rowIndex]
        else:
            rowIndex += 1

    # Delete Completely Empty Columns
    if len(rowsOfBoard) <= 0:
        return None
    
    colIndex = 0
    while colIndex < len(rowsOfBoard[0]):
        
        isFulldCellExist = False
       
        rowIndex = 0
        while rowIndex < len(rowsOfBoard):
            if rowsOfBoard[rowIndex][colIndex] != " ":
                isFulldCellExist = True
            rowIndex += 1
        
        if not isFulldCellExist:
            r = 0
            while r < len(rowsOfBoard):
                del rowsOfBoard[r][colIndex]
                r += 1
        
        else:
            colIndex += 1

# Define variables.
rowsOfBoard = getBoardFromFile()
score = 0
colorWeightForScoring = {"B": 9, "G":8, "W":7, "Y":6, "R":5, "P":4, "O":3, "D":2, "F":1, "X":0}

# Start Game
while True: 
    
    printBoard()
    print("\nYour score is:", score, end="\n\n")

    if not isGameContinuing():
        break    
    
    givenRowIndex, givenColumnIndex = getValidIndexFromUser()
    cellColor = rowsOfBoard[givenRowIndex][givenColumnIndex]

    # find setOfIndexTuplesToDelete
    if cellColor == "X":
        
        setOfIndexTuplesToDelete = indexesOfExplodedCells((givenRowIndex, givenColumnIndex), indexesToDelete=set())
        score += sum(colorWeightForScoring[rowsOfBoard[r_i][c_i]] for r_i, c_i in setOfIndexTuplesToDelete)
    
    else:

        if len(sameColorNeighbours(givenRowIndex, givenColumnIndex)) == 0:
            continue
        
        else:
            setOfIndexTuplesToDelete = indexesOfImmediateNeighbours((givenRowIndex, givenColumnIndex), indexesToDelete=set())
            score += len(setOfIndexTuplesToDelete)*colorWeightForScoring[cellColor]
    
    
    # Replace " " item instead of items in SetOfindexTuplesToDelete  
    for rowI, colI in setOfIndexTuplesToDelete:
        rowsOfBoard[rowI][colI] = " "

    # move cells to empty and delete completely empty rows and columns.
    rowsOfBoard = movedCellsToEmptyOnBoard()
    deleteCompletelyEmptyRowsAndColums()

print("Game Over!")



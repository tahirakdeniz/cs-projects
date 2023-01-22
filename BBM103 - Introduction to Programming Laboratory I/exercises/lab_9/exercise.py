inputList = input("write a list, sparate them with space").strip().split(" ")

def maxNumber(givenList):
    len_list = len(givenList)
    if len_list == 1:
        return givenList[0]
    half = len_list // 2
    secondHalf = maxNumber(givenList[:half])
    firstHalf = maxNumber(givenList[half:])
    if firstHalf > secondHalf:
        return firstHalf
    else:
        return secondHalf


def minNumber(givenList):
    len_list = len(givenList)
    if len_list == 1:
        return givenList[0]
    half = len_list // 2
    secondHalf = maxNumber(givenList[:half])
    firstHalf = maxNumber(givenList[half:])
    if firstHalf < secondHalf:
        return firstHalf
    else:
        return secondHalf

def average(_list_):
    pass




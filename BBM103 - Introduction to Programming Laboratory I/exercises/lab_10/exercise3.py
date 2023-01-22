list1 = [1, 2, 3, 4, 5, 6, 7, 8, 9]
list2 = [2, 3, 5, 6, 8]
print([n**2 for n in list1 if not n in list2])
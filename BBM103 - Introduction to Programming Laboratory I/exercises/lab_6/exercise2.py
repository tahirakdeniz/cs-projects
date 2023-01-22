def find_largest(n, _list):
    
    """
    Return nth biggest number in a list
    """
    
    return sorted(set(_list), reverse = True)[n-1]

# Test Cases
print(find_largest(3, [1,3,5,7,8,7,6,12,11,12,12,12,12,12,13] )) # 11
sorted(list(range(1000000000)))
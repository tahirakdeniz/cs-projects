year = int(input("Year:"))
is_leap_year = False
if year%4 == 0:
    is_leap_year = True
    if year%100 == 0:
        is_leap_year = False
        if year%400 == 0:
            is_leap_year = True
print( "This is leap year" if  is_leap_year else "This isn't leap year")
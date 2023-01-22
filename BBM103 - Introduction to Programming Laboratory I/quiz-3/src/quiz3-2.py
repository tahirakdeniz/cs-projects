import sys

numbers = sorted(set(int(n) for n in sys.argv[1].split(",") if int(n) > 0))

nextSurvivingIndex = 0
while nextSurvivingIndex <= len(numbers) - 1:

    #### UPDATE MEXT SURVIVING NUMBER ####
    nextSurvivingNumber = numbers[nextSurvivingIndex]
    #### ---

    is_one = (nextSurvivingNumber == 1)
    if is_one:
        nextSurvivingNumber = 2

    #### DELETE ITEMS ####
    deleting_index = nextSurvivingNumber - 1
    while deleting_index <= len(numbers) - 1:
        numbers.pop(deleting_index)
        deleting_index += nextSurvivingNumber - 1
    #### ---

    if is_one:
        nextSurvivingNumber = 1

    #### FIND INDEX NEXT SURVIVING NUMBER ####
    if numbers.count(nextSurvivingNumber) != 0:
        nextSurvivingIndex = numbers.index(nextSurvivingNumber) + 1
    #### ---

print(" ".join([str(number) for number in numbers]))


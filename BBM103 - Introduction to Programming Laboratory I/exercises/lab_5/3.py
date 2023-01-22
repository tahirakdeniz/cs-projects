import random
picked_number = random.randint(0,99)

guessed_number = None

while True:
    guessed_number = int(input("hahah guess my number: "))
    if guessed_number == picked_number:
        print("<<TRUE>>")
        break
    print("«increase your number»" if guessed_number < picked_number else "«decrease your number»")
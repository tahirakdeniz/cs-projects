user_input = input("ENTER E-MAIL \n")
print("Valid email" if "@" in user_input and "." in user_input else "Invalid email")
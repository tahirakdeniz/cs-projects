### Define rules and functions ---

def brush_down():
    global is_brush_up 
    is_brush_up = False
    paint()

def brush_up():
    global is_brush_up
    is_brush_up = True


def rotate_right():
    global direction
    direction = (direction + 1) % 4


def rotate_left():
    global direction
    direction = (direction - 1) % 4


def move_up(x):
    
    for _ in range(x):
        change_position(1)
        paint()


def jump():

    change_position(3)
    brush_up()


def reverse_direction():
    global direction
    direction = (direction + 2) % 4


def view_matrix():
    for line in matrix:
        for item in line:
            print(item, end="")
        print("")


rules = [None, brush_down, brush_up, rotate_right, rotate_left, move_up, jump, reverse_direction, view_matrix]

def paint():
    if not is_brush_up:
        matrix[position_line + 1][ position_column + 1] = "*"


def change_position(n):

    global position_line
    global position_column
    
    if direction == 0:
        position_column += n
    elif direction == 1:
        position_line += n
    elif direction == 2:
        position_column -= n
    else:
        position_line -= n

    position_line = position_line%N
    position_column = position_column%N
### -----

############# MENU OF RULES ##############
print('''<-----RULES----->
1. BRUSH DOWN
2. BRUSH UP
3. VEHICLE ROTATES RIGHT
4. VEHICLE ROTATES LEFT
5. MOVE UP TO X
6. JUMP
7. REVERSE DIRECTION
8. VIEW THE MATRIX
0. EXIT
Please enter the commands with a plus sign (+) between them.''')
############# ------------- ##############

############# Check and get commands #############

is_valid_command = False
while not is_valid_command:
    
    commands = input().split("+")
    N  = int(commands.pop(0))
    
    for i in range(len(commands)):
        
        if commands[i][0] == "5":
            if len(commands[i]) < 3:
                is_valid_command = False
            else:
                is_valid_command = True
                commands[i] = tuple(map(int, commands[i].split("_")))

        else:
            commands[i] = int(commands[i])
            if not 0 <= commands[i] <= 8:
                is_valid_command = False
            else:
                is_valid_command = True
        
        if not is_valid_command:
            print("You entered an incorrect command. Please try again!")
            break

############# -------------- #############

############# Run commands ##############

# define variables
is_brush_up = True
position_line = 0 # Line, column
position_column = 0
direction = 0  # Note: direction =  0:right,  1:down,  2:left,  3:up
matrix = [ ["+" if i==0 or i== N+1 or j== 0 or j == N+1 else " " for j in range(N+2)] for i in range(N+2)]
# --
    
# run commands
for command in commands:
    if command == 0 :
        break
    elif type(command) == tuple:
        rules[5](command[1])
        continue
    else:
        rules[command]()       
# --

############# ------------ ##############

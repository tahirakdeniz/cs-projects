import sys

#### Define Command Functions ####
def add_user(name):

    if name in social_graph:
        return "ERROR:Wrong input type for 'ANU'! -- This user already exists!!"
    
    social_graph[name] = set()

    return f"User '{name}' has been added to the social network successfully"


def delete_user(name):
    
    if not name in social_graph:
        return f"ERROR:Wrong input type for 'DEU'! -- There is no user named '{name}'!!"

    for friend in social_graph[name]:
        social_graph[friend] = social_graph[friend] - {name}
    
    del social_graph[name]
    
    return f"User '{name}' and his/her all relations have been deleted successfully"


def add_friend(name1, name2):
    is_name1_ex, is_name2_ex = name1 in social_graph, name2 in social_graph
    
    if is_name1_ex:
        if is_name2_ex:
            if name2 in social_graph[name1]:
                return f"ERROR:A  relation  between '{name1}' and '{name2}' already exists!"
            else:
                social_graph[name1] = social_graph[name1].union({name2})
                social_graph[name2] = social_graph[name2].union([name1])
                return f"Relation  between  '{name1}'  and  '{name2}' has been added successfully"
        else:
            return f"ERROR:Wrong input type! for 'ANF'!--No user named '{name2}' found!!"
    elif is_name2_ex:
        return f"ERROR:Wrong input type! for 'ANF'!--No user named '{name1}' found!!"
    else:
        return f"ERROR:Wrong input type! for 'ANF'!--No user named '{name1}' and '{name2}' found!"


def delete_friend(name1, name2):
    is_name1_ex, is_name2_ex = name1 in social_graph, name2 in social_graph
    
    if is_name1_ex:
        if is_name2_ex:
            if name2 in social_graph[name1]:
                social_graph[name1] = social_graph[name1] - {name2}
                social_graph[name2] = social_graph[name2] - {name1}
                return f"Relation  between  '{name1}'  and  '{name2}' has been deleted successfully"
                
            else:
                return f"ERROR:No relation between '{name1}' and '{name2}' found!!"
        else:
            return f"ERROR:Wrong input type! for 'DEF'!--No user named '{name2}' found!!"
    elif is_name2_ex:
        return f"ERROR:Wrong input type! for 'DEF'!--No user named '{name1}' found!!"
    else:
        return f"ERROR:Wrong input type! for 'DEF'!--No user named '{name1}' and '{name2}' found!"


def count_friend(name):

    if not name in social_graph:
        return f"ERROR:Wrong input type! for 'CF'!--No user named '{name}' found!"
    
    return f"User '{name}' has {len(social_graph[name])} friends"


def find_friend(name, max_distance):

    max_distance = int(max_distance)

    if not name in social_graph:
        return f"ERROR:Wrong input type! for 'FPF'!--No user named '{name}' found!"
    
    if not 1 <= max_distance <= 3:
        return "ERROR:Wrong input type! for 'FPF'!--Maximum distance must be between 1 and 3 (1 and 3 included)"
    
    result = set()
    friendSet = {name}
    for _ in range(max_distance):
        friendSet = set.union(*(social_graph[friend] for friend in friendSet))
        result = result | friendSet

    result = sorted(result - {name})
    if len(result) > 0:
        return f"User '{name}' has {len(result)} possible friends when maximum distance is {max_distance}\nThese possible friends:{result}".replace("[", "{").replace("]", "}")
    else:
        return f"User '{name}' has not any possible friends when maximum distance is {max_distance}"


def suggest_friend(name, mutuality_degree):

    mutuality_degree = int(mutuality_degree)

    if not name in social_graph:
        return f"Error: Wrong input type! for 'SF'!--No user named '{name}' found!!"

    if not 2 <= mutuality_degree <= 3:
        return "Error: Mutually Degree can be just 2 or 3"
    
    if len(social_graph[name]) < mutuality_degree:
        return "Error: User having less friend than Mutality degree isn't acceptable"
    
    result_str = f"Suggestion List for '{name}' (when MD is {mutuality_degree}):\n"
    result = set()
    for mutual_number in range(mutuality_degree, 4):
        mutual_friends = {friend_of_friends for friend_of_friends in set.union( *(social_graph[friend] for friend in social_graph[name])) if len(social_graph[friend_of_friends] & social_graph[name]) == mutual_number} - {name}
        result = result.union(mutual_friends)
        if len(mutual_friends) > 0:
            result_str += f"'{name}' has {mutual_number} mutual friends with {sorted(mutual_friends)}\n".replace("[","").replace("]","")
        else:
            result_str += f"'{name}' has not any {mutual_number} mutual friends.\n"
    
    result_str += f"The suggested friends for '{name}':{sorted(result)}".replace("[","").replace("]","")
    return result_str


commandDict = {
    "ANU": add_user,
    "DEU": delete_user,
    "ANF": add_friend,
    "DEF": delete_friend,
    "CF": count_friend,
    "FPF": find_friend,
    "SF": suggest_friend,
    }
#### ------------------------ ####

smnPath, commandPath, outputPath = sys.argv[1], sys.argv[2], "output.txt"

#### Def Graph ####
with open(smnPath, encoding="utf-8") as smn:  
    social_graph = {line.split(":")[0] : set(line.split(":")[1].strip("\n").strip().split(" ")) for line in smn}
#### --------- ####

#### Run Functions and Write Output ####
with open(commandPath, encoding="utf-8") as commands, open(outputPath, 'w', encoding="utf-8") as output:
    output.write("Welcome to Assignment 3\n" + "-"*30 +"\n")
    for line in commands:
        command_line = line.strip("\n").strip().split()
        try:
            output.write(commandDict[command_line[0]](*(command_line[1:]))+"\n")
        except (TypeError, ValueError) as err:
            output.write(f"ERROR:Wrong input type for '{command_line[0]} Error: {err}'\n")
#### ----------------------------- ####
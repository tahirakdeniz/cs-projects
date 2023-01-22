import sys

path = sys.argv[1]
students = sys.argv[2].split(",")

graph = {}
with open(path, encoding='utf-8') as input_file:
    for line in input_file:
        line_list = line.strip("\n").split(":")
        graph[line_list[0]] = line_list[1]

for s in students:
    try:
        print(f"Name: {s} University: {graph[s]}")
    except KeyError:
        print(f"{s} not found!")
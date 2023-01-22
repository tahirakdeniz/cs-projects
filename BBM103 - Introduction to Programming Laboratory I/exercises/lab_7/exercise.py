grades_dict = {}

with open("grades.txt") as grades_file:
    for line in grades_file:
        grade_list = line.split(':')
        grades_dict[int(grade_list[1])] = grade_list[0]

highest_grade = max(grades_dict)
lowest_grade = min(grades_dict)

output_file = open("class_stats.txt", 'w')
output_file.write(f"Highest grade: {highest_grade} name: {grades_dict[highest_grade]}\n")
output_file.write(f"Lowest grade: {lowest_grade} name: {grades_dict[lowest_grade]}\n")
output_file.write(f"Average grade: {sum(grades_dict) / len(grades_dict)} ")
output_file.close()



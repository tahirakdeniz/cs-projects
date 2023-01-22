import sys
input_file, output_file = sys.argv[1], sys.argv[2]

file_dict = {}
with open(input_file, "r", encoding='utf-8') as fileIn:
    for line in fileIn:
        line_list = line.split("\t")
        msgNumber = int(line_list[0])
        
        if file_dict.get(msgNumber) == None:
            file_dict[msgNumber] = {}
        
        file_dict[msgNumber][int(line_list[1])] = line_list[2].strip("\n")

with open(output_file, 'w', encoding='utf-8') as fileOut:
    i = 1
    for msgN in sorted(file_dict):
        
        fileOut.write(f"Message\t{i}\n")
        
        for pckN in sorted(file_dict[msgN]):
            fileOut.write(f"{msgN}\t{pckN}\t{file_dict[msgN][pckN]}\n")
        
        i += 1
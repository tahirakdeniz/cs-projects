import sys

try:

	OPERANDS, COMPARISON_DATA = sys.argv[1], sys.argv[2]

	with open(OPERANDS) as operandFile, open(COMPARISON_DATA) as comparisonFile:
		
		for operandLine in operandFile:
			
			try:
				print("-"*15)
				
				resultToCompare = comparisonFile.readline().strip()

				givenInput = operandLine.strip().split(" ")
				operandList = list(map(int, map(float, givenInput)))
				div, nondiv, from_, to = operandList[0], operandList[1], operandList[2], operandList[3]

				myResult = " ".join(map(str, [n for n in range(from_, to + 1) if n%div == 0 and n%nondiv != 0]))
				
				print("My results: \t\t", myResult)
				print("Results to compare:\t", resultToCompare)
				
				if myResult == resultToCompare:
					print("Goool!!!")
				else:
					assert False
			
			except ValueError:
				print("ValueError: only numeric input is accepted.")
				print("Given input: ", *givenInput)

			except IndexError:
				print("IndexError: number of operands less than expected.")
				print("Given input: ", *givenInput)

			except ZeroDivisionError:
				print("ZeroDivisionError: You can't divide by 0.")
				print("Given input: ", *givenInput)
			
			except AssertionError:
				print("AssertionError: results don't match.")
			
			except:
				print("kaBOOM: run for your life!.")
		

except IndexError:
	print("IndexError: number of input files less than expected.")

except IOError as err:
	print(f"IOError: cannot open {err.filename}")
		  
finally:
	print("~ Game Over ~")

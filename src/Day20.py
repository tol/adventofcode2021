
import numpy as np
filename = 'inputd20.txt'

with open(filename) as file:
	rules = file.readline().strip()
	rulesMap={}
	#print(rules)
	for idx, char in enumerate(rules):
		#print(char, idx)
		rulesMap[idx]=char

	lines=[]
	for line in file:
		if line.strip() != '':    # Skip the blank line
			lines.append(line.strip())
			print(line)
	image=np.array(list(map(list, lines)))
	#print(image.shape)

	print(image)
	print(image.shape)
	new_image=[]
	print(int("111000000", 2))
	image=np.pad(image, 1, mode='constant',constant_values=('.'))
	for k in range(50):
		print(image)
		print("==================")

		new_image=[]
		for i in range(1, image.shape[0]-1):
			row=[]
			for j in range(1, image.shape[1]-1):
				pointmatrix = image[i-1:i+2, j-1:j+2]
				#print(str(i)+" "+str(j)+" =")
				#print(pointmatrix)

				binary="".join(['1' if elem == '#' else '0' for elem in pointmatrix.flatten()])
				key=int(binary, 2)
				#print(binary+" "+rulesMap.get(key))
				row.append(rulesMap.get(key))
				#print(np.array(row))
				#print("--------")
			new_image.append(row)

		print(len(list(filter(lambda x: (x == '#'), np.array(new_image).flatten()))))
		image=np.array(new_image)
		image=np.pad(image, 2, mode='constant',constant_values=('.'))
		#print(image)
		#image=np.pad(image, [(5, 5), (5, 5)], mode='constant',constant_values=('.'))

	#print(image)
	#print("==================")
	#print(rulesMap)
#18074

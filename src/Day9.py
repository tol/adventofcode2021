from collections import defaultdict
from pprint import pprint
import numpy as np
import sys as sys
data = []

def calculateBasin(i,j,acc):
	data[i][j]=9
	if (data[i-1][j]!=9 and data[i-1][j]!=sys.maxsize) :
		acc=calculateBasin(i-1,j,acc+1)
	if (data[i+1][j]!=9 and data[i+1][j]!=sys.maxsize) :
		acc=calculateBasin(i+1,j,acc+1)
	if (data[i][j-1]!=9 and data[i][j-1]!=sys.maxsize ) :
		acc=calculateBasin(i,j-1,acc+1)
	if ( data[i][j+1]!=9 and data[i][j+1]!=sys.maxsize) :
		acc=calculateBasin(i,j+1,acc+1)
	return acc



with open("../inputd9.txt") as fin:
	raw_data = fin.readline().strip()
	while len(raw_data) > 0:
		row = []
		for i in raw_data:
			row.append(int(i))
		data.append(row)
		raw_data = fin.readline().strip()
	data = np.pad(data, pad_width=1, mode='constant', constant_values=(sys.maxsize ))
	num_rows, num_cols = data.shape
	low_risk=[]
	low_risk_locations=[]
	for i in range(1 , (num_rows-1)):
		for j in  range(1 , (num_cols-1)):
			pprint(data[i][j])
			if (data[i][j]<data[i-1][j] and  data[i][j]<data[i+1][j] and data[i][j]<data[i][j-1] and  data[i][j]<data[i][j+1]) :
				low_risk.append(data[i][j]+1)
				low_risk_locations.append((i,j))
	basins=[]
	for r in range(0, len(low_risk_locations)):
		basins.append(calculateBasin(low_risk_locations[r][0],low_risk_locations[r][1],1))
	basins.sort(reverse=True)

	pprint(low_risk)
	pprint(low_risk_locations)
	pprint(sum(low_risk))
	pprint(basins)
	pprint(basins[0]*basins[1]*basins[2])
	pprint(data)


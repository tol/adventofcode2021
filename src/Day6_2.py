from collections import defaultdict
from pprint import pprint

with open("../inputd6.txt") as fin:
	raw_data = fin.read().strip().split(",")
	freq = defaultdict(int)
	for i in raw_data:
		freq[int(i)] += 1



days = 256

for _ in range(days):

	new_freq = defaultdict(int)

	for key in freq:
		if key == 0:
			new_freq[6] += freq[key]
			new_freq[8] = freq[key]
		else:
			new_freq[key - 1] += freq[key]

	freq = new_freq

ans = 0
pprint(freq)
for key in freq:
	ans += freq[key]
print(ans)


m = [a.strip() for a in open("inputd25.txt")]
h, w = len(m), len(m[0])

a = {(r,c): m[r][c] for r in range(h)
	 for c in range(w)
	 if m[r][c] != '.'}

for t in range(1000):
	def move(f, x): return {f(*p) if
							a[p]==x and f(*p) not in a
							else p: a[p] for p in a}

	b = a.copy()
	a = move(lambda r,c: (r,(c+1)%w), '>')
	a = move(lambda r,c: ((r+1)%h,c), 'v')

	if a == b: print(t+1); break

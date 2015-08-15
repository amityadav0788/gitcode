def sum(a,b):
	return a+b

n = int(raw_input())
for i in range(0,n):	
	x,y = raw_input().split()
	x,y = int(x),int(y)
	z = sum(x,y)
	print z
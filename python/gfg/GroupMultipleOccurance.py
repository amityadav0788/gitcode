T = raw_input()
input = [int (x)for x in T.split(' ')]
a = dict()
for i in range(0,len(input)):
	if a.has_key(input[i]):
		a.update([(input[i],a[input[i]]+1),])
	else: a[input[i]]=1
print a
for i in range(0,len(input)):
	if a.has_key(input[i]):
		for j in range(0,a[input[i]]):
			print input[i]
	del a[input[i]]
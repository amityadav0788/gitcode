def prime(n):
	for x in range(2,n):
		for y in range(2,x):
			if(x%y == 0):
				break
		else:
			print x,' is prime'
			
prime(9)
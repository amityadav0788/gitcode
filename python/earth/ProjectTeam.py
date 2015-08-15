import math

T = int(raw_input())
for i in range(0,T):
	line2 = raw_input()
	size = line2.split(' ')
	size_int = int(size[0])
	if(size_int%2 == 0):
		nums = []
		for x in range(0,size_int):
			nums.append(int(size[x+1]))
		nums.sort()
		sum = []
		for i in range(0,(size_int/2)):
			sum.append(nums[i]+nums[((size_int)-1-i)])
		sum.sort()
		print sum[len(sum)-1]-sum[0]
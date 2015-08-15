import math

T = int(raw_input())
for n in range(0,T):
	N = int(raw_input())
	line2 = raw_input()
	nums = [int (x)for x in line2.split(' ')]
	nums.sort()
	count = 0
	for i in range(0,N):
		count+=1
		for j in range(i+1,N):
			if(nums[i] == nums[j]):
				count+=1
			else:
				break
	print count
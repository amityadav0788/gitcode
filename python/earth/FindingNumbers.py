import math

T = int(raw_input())
for n in range(0,T):
	line2 = raw_input()
	nums = [int (x)for x in line2.split(' ')]
	count = 0
	for i in range(1,int(math.sqrt(nums[0]))):
		temp = nums[0]/i
		if(temp*i == nums[0]):
			if(temp<=nums[1]):
				count = count+1
			if(i<=nums[2] and temp!=i):
				count = count+1
print count
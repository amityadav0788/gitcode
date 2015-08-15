import math

def reverse(input,l,r):
	while(l<=r):
		temp = input[l]
		input[l] = input[r]
		input[r] = temp
		l+=1
		r-=1
		
def min_rad(first,second,l,r):
	maxd = 0
	maxi = 0
	maxj = 0
	#print "l, r",l,r
	for i in range(0,r-l):
		for j in range(i+1,r-l+1):
			d = float(math.sqrt(math.pow(first[l+i]-first[l+j],2)+math.pow(second[l+i]-second[l+j],2)))
			#print "d, maxd i j l+i l+j",d,maxd,i,j,l+i,l+j
			if(d>maxd):
				maxd = d
				maxi = l+i
				maxj = l+j
				#print "this is point inside",maxi,maxj
	#print "this is point firsti first j",maxi,maxj,first[maxi],first[maxj]
	midpoint_x = float((float(first[maxi])+float(first[maxj]))/2)
	midpoint_y = float((float(second[maxi])+float(second[maxj]))/2)
	#print "midpoints",midpoint_x,midpoint_y
	rad = float(math.sqrt(math.pow(first[maxi]-midpoint_x,2)+math.pow(second[maxi]-midpoint_y,2)))
	return rad
	
def swap(first,second,l,r):
	while(l<=r):
		temp = first[l]
		first[l] = second[l]
		second[l] = temp
		l+=1

def swap_con(input,l1,r1,l2,r2,size):
	temp = [size]
	for i in range(0,size-1):
		if(i<l1-1):
			temp[i] = input[i]
		
		


line1  = raw_input()
inputs = [int(n) for n in line1.split()]
N = inputs[0]
M = inputs[1]
zeroth = [N]
first = [N]
line2 = raw_input()
line3 = raw_input()
zeroth = [int(n) for n in line2.split()]
first = [int(n) for n in line3.split()]
for i in range(0,M):
	line = raw_input()
	query = [int(n) for n in line.split()]
	query_type = query[0]
	if(query_type == 1):
		if(query[1] == 1):
			reverse(first,query[2]-1,query[3]-1)
		elif(query[1] == 0):
			reverse(zeroth,query[2]-1,query[3]-1)
	elif(query_type == 4):
		minrad = min_rad(zeroth,first,query[1]-1,query[2]-1)
		print("%.2f" % minrad)
	elif(query_type == 3):
		swap(zeroth,first,query[1]-1,query[2]-1)
	elif(query_type == 2):
		if(query[1] == 1):
			swap_con(first,query[2]-1,query[3]-1,query[4]-1,query[5]-1)
		elif(query[1] == 0):
			swap_con(zeroth,query[2]-1,query[3]-1,query[4]-1,query[5]-1,N)
		
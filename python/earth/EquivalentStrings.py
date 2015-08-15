maxMN = 2002
MOD = 1000000007
dp = [[0 for x in range(maxMN)]for x in range(maxMN)]
sum = [[0 for x in range(maxMN)]for x in range(maxMN)]

def precompute():
	# precompute all classes
	n = 2000
	dp[0][0] = 1
	for i in range(1,n+1):
		for j in range(1,i+1):
			dp[i][j] = ((j*dp[i-1][j])%MOD + dp[i-1][j-1])%MOD
			sum[i][j] = (sum[i][j-1] + dp[i][j])%MOD
			
T = int(raw_input())
precompute()
for x in range(0,T):
	M,N = (int(x) for x in raw_input().split(' '))
	if(M>=1 and M<=2000):
		if(N>=1 and N<=2000):
			ans  = sum[N][min(M,N)]
			print ans
	
	
	
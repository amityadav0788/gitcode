T = int(raw_input())
for i in range(0,T):
	count_t = [256]
	count_p = [256]
	Pattern = raw_input()
	Text = raw_input()
	p_len = len(Pattern)
	t_len = len(Text)
	if(1<=p_len and p_len<=1000):
		if(1<=t_len and t_len<=100000):
			for(j in range(0,t_len)
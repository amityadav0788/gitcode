from collections import deque

stack = []
stack.append(9)
for n in range(0,10):
	stack.append(n)

print stack
print stack.pop()
print stack.pop()

queue = deque(["Amit","Yadav"])
queue.append("good")
print queue.popleft()
print queue
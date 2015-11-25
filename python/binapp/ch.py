#!/usr/bin/python
import math
import random
import matplotlib.pyplot as plt

p0 = None
def get_base_point(pts):
    ymin = pts[0][1]
    imin = 0
    for i in range(1, len(pts)):
        if pts[i][1] < ymin:
            imin = i
            ymin = pts[i][1]
        elif pts[i][1] == ymin and pts[i][0] < pts[imin][0]:
            imin = i
            ymin = pts[i][1]
    return imin

def comparator(pt1, pt2):
    diff = ((pt1[1] - p0[1]) * (pt2[0] - pt1[0]) - 
                (pt1[0] - p0[0]) * (pt2[1] - pt1[1]))
    if diff > 0:
        return 1
    elif diff < 0:
        return -1
    else:
        if (math.hypot(pt1[0] - p0[0], pt1[1] - p0[1]) > 
                math.hypot(pt2[0] - p0[0], pt2[1] - p0[1])):
            return 1
        return -1

def direction(pt1, pt2, pt3):
    return ((pt2[1] - pt1[1]) * (pt3[0] - pt2[0]) -
            (pt2[0] - pt1[0]) * (pt3[1] - pt2[1]))

def graph_path(pts):
    l, m = zip(*pts)
    plt.plot(l, m, 'r--')
    plt.show()
    
def insert_left_overs(path, lo):
    final_path = [x for x in path]
    graph_path(path)
    for x in lo:
        dist = math.hypot(x[0] - path[0][0] , x[1] - path[0][1])
#print dist
        inx = path[0]
        offset = 1
        for i in range(1, len(path)):
            dist2 = math.hypot(x[0] - path[i][0], x[1] - path[i][1])
            if dist2 <= dist:
                offset = 0
                inx = path[i]
                dist = dist2
        index2 = final_path.index(inx)
        final_path.insert(index2 + offset, x)
    print "Final path: ", final_path
    graph_path(final_path)
    return final_path

def print_shortest_path(pts):
    print "all points: ", pts
    global p0
    # Get the base point which is the min of all y.
    # if two points have same lowest y, the one with lowest x will be chosen.
    lowest_index = get_base_point(pts)
    p0 = pts[lowest_index]

    # Treat p0 as base and hence make it p[0]
    pts[0], pts[lowest_index] = pts[lowest_index], pts[0]
    
    # Sort the array in the order of angle made with p0
    pts[1:] = sorted(pts[1:], cmp=comparator)

    # Now the fun begins (non-python use: stack)
    path = []
    path.append(pts[0])
    path.append(pts[1])
    path.append(pts[2])
    for i in range(3, len(pts)):
        while direction(path[-2], path[-1], pts[i]) >= 0:
            path = path[:-1]
        path.append(pts[i])
    
    # Insert the left over elements in the path
    print "convex: ", path
    left_overs = [x for x in pts if x not in path]
    print "insiders: ", left_overs
    final_path = insert_left_overs(path, left_overs)
#print final_path

if __name__ == "__main__":
    pts1 = random.sample(range(100), 10)
    pts2 = random.sample(range(100), 10)
    pts = zip(pts1, pts2)
    print_shortest_path(pts)


NUMS = []
TARGET = 0
CANDIDATES = 0


"""
Returns the amount of permutations that sum to <= TARGET

O(TARGET * N * CANDIDATES) where n is the number of elements to fill and TARGET 
is the target sum we want to go to and CANDIDATES is the number of possible elements
"""


def less(idx, cur_sum):
    if cur_sum > 0:
        return 0
    if idx == len(NUMS):
        return 1 if cur_sum < TARGET else 0
    res = 0
    for i in range(CANDIDATES):
        res += less(idx+1, cur_sum + i)
    return res


def equal(idx, cur_sum):
    if cur_sum > 0:
        return 0
    if idx == len(NUMS):
        return 1 if cur_sum == TARGET else 0
    res = 0
    for i in range(CANDIDATES):
        res += less(idx+1, cur_sum + i)
    return res


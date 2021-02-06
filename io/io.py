# from collections import defaultdict

import io
import os
import math
input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline
# WARNING
# this turns binary strings ex. "0011" to ints by default
# making rr(), read impossible as a string


def rr(): return input()
def rri(): return int(input())
def rrm(): return list(map(int, input().split()))


INF = float('inf')


def solve():
    pass


t = rri()
for _ in range(t):
    print(solve())

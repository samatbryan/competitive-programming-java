STRING = ''

"""
return the amount of strings less than STRING that holds true with the variant
"""


def f(idx, tight, VARIANT):
    if idx == len(STRING):
        if VARIANT HOLDS TRUE:
            return 1
        return 0
    res = 0
    ceil = STRING[idx] if tight else 9
    for i in range(ceil+1):
        new_tight = False if tight and i < STRING[idx] else tight
        res += f(idx+1, new_tight, VARIANT)
    return res

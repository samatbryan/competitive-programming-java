
def mult_inverse(a, m):
    return my_pow(a, m-2, m)


def my_pow(a, k, mod):
    if k == 0:
        return 1
    if k == 1:
        return a

    mp = my_pow(a, k // 2)
    if k % 2 == 0:
        return (mp * mp) % mod
    return ((mp * mp) % mod * a) % mod

def gcd(a, b):
    if a==0:
        return b
    return gcd(b%a, a)
class RabinKarp:
    '''
    The RabinKarp object takes in a string s and is used to calculate the hash of any substring in O(1)
    :param s: The string we want to RabinKarp
    :type s: str

    ex: 
    rb = RabinKarp("hihi")
    hash1 = rb.getHashSubstring(0,1)
    hash2 = rb.getHashSubstring(2,3)
    // hash1 == hash2 
    '''
    PRIME1 = 3137
    PRIME2 = 1009
    MOD1 = 1000000007
    MOD2 = 998244353
    
    def __init__(self, s: str):
        self._s = s
        self._prefixHash = [0] * (len(s) + 1)
        self._powers = [0] * (len(s) + 1)
        self._computeHashAndPow()

    def _computeHashAndPow(self):
        self._powers[0] = 1

        for i in range(1, len(self._s) + 1):
            c = ord(self._s[i - 1]) - ord("a") + 1
            self._prefixHash[i] = (self._prefixHash[i - 1] * self.PRIME1 + c) % self.MOD1
            self._powers[i] = (self._powers[i - 1] * self.PRIME1) % self.MOD1

    def getHashSubstring(self, left: int, right: int):
        '''Calculates in O(1) the hash of the substring[left:right] INCLUSIVE

        :param left: The left index of the substring inclusive
        :type left: int
        :param right: The right index of the substring inclusive
        :type right: int

        :returns: An int of the hash of the substring
        :rtype: int
        '''  
        return (
            self._prefixHash[right + 1]
            - self._prefixHash[left] * self._powers[right - left + 1]
            + self.MOD1
        ) % self.MOD1

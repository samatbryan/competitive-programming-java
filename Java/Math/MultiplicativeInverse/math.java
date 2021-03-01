public int gcd(int a, int b){
    if (a == 0) return b;
    return gcd(b%a, a);
}

// return n C k
static long comb(long n, long k)  
    { 
        long res = 1; 
  
        // Since C(n, k) = C(n, n-k) 
        if (k > n - k) 
            k = n - k; 
  
        // Calculate value of 
        // [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1] 
        for (long i = 0; i < k; ++i) { 
            res *= (n - i); 
            res /= (i + 1); 
        } 
  
        return res; 
    }
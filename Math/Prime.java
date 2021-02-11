
// return the prime factors of n,
static ArrayList<Integer> prime_factors(int n){  // O(sqrt(n))
    int d = 2;
    while(d * d <= n){
        while(n % d == 0){
            n /=d;
            res.add(d);
        }
        d += 1 + (d & 1);
    }
    if(n > 1){
        res.add(n);
    }
    return res;
}
using namespace std;

vector<int> lis(vector<int> &nums)
{
    int n = nums.size();
    vector<int> dp(n);
    vector<int> lis(n + 1);
    lis.push_back(INT_MIN);

    for (int num : nums)
    {
        int left = 0;
        int right = lis.size() - 1;
        int insert_idx = 1;

        // find the number of elements strictly less than num
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (lis[mid] < num)
            {
                insert_idx = max(insert_idx, mid + 1);
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }

        dp.push_back(insert_idx);
        lis[insert_idx] = num;
    }
    return dp;
}

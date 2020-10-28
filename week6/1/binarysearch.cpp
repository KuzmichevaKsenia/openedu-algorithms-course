#include <fstream>
#include <vector>

using namespace std;

int binsearch_right(vector<int> & a, int x)
{
    int left = 0;
    int right = a.size();

    while (left < right)
    {
        int middle = (left + right) / 2;
        if (x < a[middle])
        {
            right = middle;
        }
        else
        {
            left = middle + 1;
        }
    }
    return a[left - 1] == x ? left : -1;
}

int binsearch_left(vector<int> & a, int x)
{
    int left = 0;
    int right = a.size();

    while (left < right)
    {
        int middle = (left + right) / 2;
        if (a[middle] < x)
        {
            left = middle + 1;
        }
        else
        {
            right = middle;
        }
    }
    return a[left] == x ? left + 1 : -1;
}

int main()
{
    ifstream input("input.txt");
    ofstream output("output.txt");

    int n, m = 0;
    input >> n;

    vector<int> a(n);

    for (int i = 0; i < n; ++i)
    {
        input >> a[i];
    }

    input >> m;

    for (int i = 0; i < m; ++i)
    {
        int x = 0;
        input >> x;
        int leftmost = binsearch_left(a, x);
        int rightmost = binsearch_right(a, x);
        output << leftmost << " " << rightmost << "\n";
    }
}

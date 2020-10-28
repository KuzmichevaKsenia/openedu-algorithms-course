#include <vector>
#include <fstream>

using namespace std;

bool is_heap(vector<int> & heap)
{
    int n = heap.size();
    for (int i = 1; i <= n ; ++i)
    {
        if ((2 * i < n) && (heap[2 * i] < heap[i]))
        {
            return false;
        }
        if ((2 * i + 1 < n) && (heap[2 * i + 1] < heap[i]))
        {
            return false;
        }
    }
    return true;
}

int main()
{
    ifstream input("input.txt");
    ofstream output("output.txt");

    int n = 0;
    input >> n;

    vector<int> heap(n + 1);

    for (int i = 1; i <= n; ++i)
    {
        input >> heap[i];
    }

    output << (is_heap(heap) ? "YES" : "NO") << endl;

    return 0;
}

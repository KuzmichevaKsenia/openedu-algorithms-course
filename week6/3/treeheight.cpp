#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <memory>

using namespace std;

struct Node
{
    int key;
    size_t children[2];

    Node(int key, size_t left, size_t right) : key(key)
    {
        children[0] = left;
        children[1] = right;
    }
};

int height(vector<Node*> & tree)
{
    if (tree.empty())
    {
        return 0;
    }

    int max_distance = 0;
    queue<pair<Node*, int>> queue;
    queue.push(make_pair(tree[0], 1));

    while (!queue.empty())
    {
        Node* vertex = queue.front().first;
        int distance = queue.front().second;
        max_distance = max(distance, max_distance);
        for (size_t child : vertex->children)
        {
            if (child != -1)
            {
                queue.push(make_pair(tree[child], distance + 1));
            }
        }
        queue.pop();
    }

    return max_distance;
}

int main()
{
    ifstream input("input.txt");
    ofstream output("output.txt");

    size_t n;
    input >> n;
    vector<Node*> nodes;

    for (size_t i = 0; i < n; i++)
    {
        int key;
        size_t left, right;
        input >> key >> left >> right;
        nodes.push_back(new Node(key, left-1, right-1));
    }

    output << height(nodes);

    return 0;
}
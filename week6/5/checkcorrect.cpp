#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

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

bool check_subtree(vector<Node*> & tree, size_t node, int min_key, int max_key)
{
    if (node == -1)
    {
        return true;
    }
    if (tree[node]->key <= min_key || tree[node]->key >= max_key)
    {
        return false;
    }
    return check_subtree(tree, tree[node]->children[0], min_key, tree[node]->key)
           && check_subtree(tree, tree[node]->children[1], tree[node]->key, max_key);
}

bool is_bst(vector<Node*> & tree)
{
    if (tree.empty())
        return true;
    return check_subtree(tree, 0, -1000000001, 1000000001);
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

    output << (is_bst(nodes) ? "YES" : "NO");

    return 0;
}
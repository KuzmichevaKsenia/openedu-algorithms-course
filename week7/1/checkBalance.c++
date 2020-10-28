#include <fstream>
#include <vector>

struct Node
{
    int key;
    int height;
    int children[2];

    Node(int key, int left, int right) : key(key), height(0)
    {
        children[0] = left;
        children[1] = right;
    }
};

struct AVL_Tree
{
    std::vector<Node *> tree;

    int balance(int v)
    {
        return (tree[v]->children[1] != -1 ? tree[tree[v]->children[1]]->height : 0) -
               (tree[v]->children[0] != -1 ? tree[tree[v]->children[0]]->height : 0);
    }

    void count_height()
    {
        if (tree.empty())
        {
            return ;
        }

        dfs(tree[0]);
    }

    int height_right(Node *v)
    {
        return v->children[1] == -1 ? 0 : tree[v->children[1]] -> height;
    }

    int height_left(Node *v)
    {
        return v->children[0] == -1 ? 0 : tree[v->children[0]] -> height;
    }

    void dfs(Node * v)
    {
        for (int child : v->children)
        {
            if (child != -1)
            {
                dfs(tree[child]);
            }
        }
        v->height = std::max(height_left(v), height_right(v)) + 1;
    }
};

int main()
{
    std::ifstream in("input.txt");
    std::ofstream out("output.txt");

    size_t n;
    in >> n;
    auto avl = new AVL_Tree();

    for (int i = 0; i < n; i++)
    {
        int key;
        int left, right;
        in >> key >> left >> right;
        avl->tree.push_back(new Node(key, left-1, right-1));
    }

    avl->count_height();
    for (int i = 0; i < avl->tree.size(); i++)
    {
        out << avl->balance(i) << std::endl;
    }
}

#include <fstream>

using namespace std;

struct Node
{
    int key;
    Node* children[2];

    Node(int key) : key(key)
    {
        children[0] = children[1] = nullptr;
    }

    ~Node()
    {
        delete children[0];
        delete children[1];
    }
};

class Tree
{
    Node* root = nullptr;

public:
    void addNode(int key) {
        Node* x = root;
        Node* y = nullptr;
        int cmp = 0;
        while (x != nullptr) {
            cmp = x->key - key;
            if (cmp == 0) {
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x->children[1];
                } else {
                    x = x->children[0];
                }
            }
        }
        Node* newNode = new Node(key);
        if (y == nullptr) {
            root = newNode; // key is root
        } else {
            if (cmp > 0) {
                y->children[0] = newNode; // key is left for y
            } else {
                y->children[1] = newNode; // key is right for y
            }
        }
    }

    int removeSubtree(int key) {
        Node* x = root;
        Node* y = nullptr;
        int cmp = 0;
        while (x != nullptr) {
            cmp = x->key - key;
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x->children[1];
                } else {
                    x = x->children[0];
                }
            }
        }
        if (x == nullptr) {
            return 0; // nothing deleted
        }
        int count = nodesCount(x);
        // delete x with count nodes
        if (x->key > y->key) {
            y->children[1] = nullptr;
        } else {
            y->children[0] = nullptr;
        }
        x = nullptr;
        return count;
    }

    int nodesCount(Node* node) {
        if ((node->children[0] == nullptr) && (node->children[1] == nullptr)) {
            return 1;
        }
        int left, right;
        if (node->children[0] != nullptr) {
            left = nodesCount(node->children[0]);
        } else {
            left = 0;
        }
        if (node->children[1] != nullptr) {
            right = nodesCount(node->children[1]);
        } else {
            right = 0;
        }
        return left + right + 1;
    }

    ~Tree()
    {
        delete root;
    }
};

int main()
{
    ifstream input("input.txt");
    ofstream output("output.txt");

    int nodesCount;
    input >> nodesCount;
    int arrayNodes[nodesCount][3];
    for (int i = 0; i < nodesCount; i++) {
        for (int j = 0; j < 3; j++) {
            input >> arrayNodes[i][j];
        }
    }
    int removeCount;
    input >> removeCount;
    int arrayRemove[removeCount];
    for (int i = 0; i < removeCount; i++) {
        input >> arrayRemove[i];
    }

    Tree tree;
    for (int i = 0; i < nodesCount; i++) {
        tree.addNode(arrayNodes[i][0]);
    }

    for (int i = 0; i < removeCount; i++) {
        nodesCount -= tree.removeSubtree(arrayRemove[i]);
        output << nodesCount << "\n";
    }

    return 0;
}

#include <stack>
#include <string>
#include <fstream>

using namespace std;

int main() {
    ifstream in ("input.txt");
    ofstream out("output.txt");
    stack<string> myStack;
    int m;
    string temp;
    getline(in, temp);
    m = stoi(temp);
    for (int i = 1; i <= m; i++) {
        getline(in, temp);
        if (temp[0] == '-') {
            out << myStack.top() + "\n";
            myStack.pop();
        } else myStack.push(temp.substr(2));
    }
    in.close();
    out.close();
    return 0;
}

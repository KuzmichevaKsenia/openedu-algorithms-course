#include <vector>
#include <string>
#include <fstream>

using namespace std;

int main() {
    ifstream in ("input.txt");
    ofstream out("output.txt");
    vector<char> myStack;
    int m;
    bool ok;
    string str;
    getline(in, str);
    m = stoi(str);
    for (int i = 0; i < m; i++) {
        getline(in, str);
        ok = true;
        for (char j : str) {
            if (j == '(' || j == '[') {
                myStack.push_back(j);
            } else if (
                            (j == ')' && myStack.back() == '(')
                         || (j == ']' && myStack.back() == '[')
                       ) {
                            myStack.pop_back();
                    } else {
                        ok = false;
                        break;
                    }
        }
        if (!myStack.empty()) ok = false;
        if (ok) out << "YES\n";
        else out << "NO\n";
        myStack.clear();
    }
    in.close();
    out.close();
    return 0;
}

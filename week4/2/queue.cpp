#include <queue>
#include <string>
#include <fstream>

using namespace std;

int main() {
    ifstream in ("input.txt");
    ofstream out("output.txt");
    queue<string> myQueue;
    int m;
    string temp;
    getline(in, temp);
    m = stoi(temp);
    for (int i = 1; i <= m; i++) {
        getline(in, temp);
        if (temp[0] == '-') {
            out << myQueue.front() + "\n";
            myQueue.pop();
        } else myQueue.push(temp.substr(2));
    }
    in.close();
    out.close();
    return 0;
}

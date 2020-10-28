#include <string>
#include <fstream>
#include <stack>

using namespace std;

int main() {
    ifstream in ("input.txt");
    ofstream out("output.txt");
    int m;
    string temp;
    getline(in, temp);
    m = stoi(temp);

    stack<pair<int, int>> s1, s2;
    for (int i = 1; i <= m; i++) {
        getline(in, temp);
        switch (temp[0]) {
            case '+': {
                int new_element = stoi(temp.substr(2));
                int minima = s1.empty() ? new_element : min(new_element, s1.top().second);
                s1.push(make_pair(new_element, minima));
                break;
            }
            case '-': {
                if (s2.empty())
                    while (!s1.empty()) {
                        int element = s1.top().first;
                        s1.pop();
                        int minima = s2.empty() ? element : min(element, s2.top().second);
                        s2.push(make_pair(element, minima));
                    }
                //result = s2.top().first;
                s2.pop();
                break;
            }
            case '?': {
                int current_minimum;
                if (s1.empty() || s2.empty())
                    current_minimum = s1.empty() ? s2.top().second : s1.top().second;
                else
                    current_minimum = min(s1.top().second, s2.top().second);
                out << current_minimum << "\n";
                break;
            }
        }
    }

    in.close();
    out.close();
    return 0;
}

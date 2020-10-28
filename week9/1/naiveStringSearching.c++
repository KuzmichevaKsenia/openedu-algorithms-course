#include <vector>
#include <fstream>

std::vector<int> findMatches(std::string p, std::string t) {
    std::vector<int> result;
    bool ok;
    for (size_t i = 0; i <= t.size() - p.size(); ++i) {
        ok = true;
        for (size_t j = 0; j < p.size(); ++j) {
            if (t[i + j] != p[j]) {
                ok = false;
                break;
            }
        }
        if (ok) result.push_back(i);
    }
    return result;
}

int main(int argc, char *argv[]) {
    std::ifstream input("input.txt");
    std::ofstream output("output.txt");

    std::string p;
    std::string t;

    getline(input, p);
    getline(input, t);

    if (p.size() > t.size()) {
        output << 0;
        return 0;
    }
    std::vector<int> result = findMatches(p, t);

    output << result.size() << std::endl;
    for (std::vector<int>::const_iterator it = result.begin(); it != result.end(); ++it)
        output << (*it) + 1 << " ";
}

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> prefix_Function(string s) {
	int n = s.length();
	vector<int> pi(n);
	for (int i = 1; i < n; i++) {
		int j = pi[i - 1];
		while (j > 0 && s[i] != s[j])
			j = pi[j - 1];
		if (s[i] == s[j])
			j++;
		pi[i] = j;
	}
	return pi;
}

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	string p, t;
	in >> p >> t;
	int pl = p.length();

	p += "#" + t;
	vector<int> pr = prefix_Function(p);
	vector<int> ans;

	for (int i = 0; i < pr.size(); i++) {
		if (pr[i] == pl) ans.push_back(i - pl - pl);
	}

	out << ans.size() << endl;
	for (int i = 0; i < ans.size(); i++) 
		out << ans[i] + 1 << " ";

	out.close();
	in.close();
	return 0;
}
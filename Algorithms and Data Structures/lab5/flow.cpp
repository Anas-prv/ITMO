#include <iostream>
#include <vector>
#include <algorithm>
 
using namespace std;
vector<vector<int>> g;
vector<int> mark(10001, 0);
vector<int> f(10001, 0);
vector<int> c(10001, 0);
vector<int> edges;
 
void append(int a, int v, int b, int ind) {
    g[a].push_back(ind);
    c[ind] = v;
    edges.push_back(b);
}
 
int dfs(int s, int mD, int n) {
    if (s == n) {
        return mD;
    }
    if (mark[s] != 1) {
        mark[s] = 1;
        for (int i: g[s]) {
            if (c[i] - f[i] > 0) {
                int d = dfs(edges[i], min(c[i] - f[i], mD), n);
                if (d > 0) {
                    if (i % 2 == 0) {
                        f[i + 1] -= d;
                    } else {
                        f[i - 1] -= d;
                    }
                    f[i] += d;
                    return d;
                }
            }
        }
    }
    return 0;
}
 
int main() {
    int n, m, beg, end, val;
    cin >> n;
    cin >> m;
    g.resize(10001);
    int index = 0;
    for (int i = 0; i < m; i++) {
        cin >> beg;
        cin >> end;
        cin >> val;
        append(beg, val, end, index);
        index++;
        append(end, val, beg, index);
        index++;
    }
    int maxi = 0;
    int ans = 1;
    while (ans > 0) {
        mark.assign(n + 1, 0);
        ans = dfs(1, 10000, n);
        maxi += ans;
    }
    cout << maxi << endl;
    for (int i = 0; i < 2 * m; i++) {
        if (i % 2 == 0) {
            cout << f[i] << endl;
        }
    }
    return 0;
}
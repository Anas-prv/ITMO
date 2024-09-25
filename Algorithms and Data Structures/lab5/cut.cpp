#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;
vector<vector<long long int>> g;
vector<long long int> mark(10001, 0);
vector<long long int> mark_end(10001, 0);
vector<long long int> f(10001, 0);
vector<long long int> c(10001, 0);

vector<long long int> edges;
vector<pair<long long int, long long int>> all_edges;

void append(long long int a, long long int v, long long int b, long long int ind) {
    g[a].push_back(ind);
    c[ind] = v;
    edges.push_back(b);
    all_edges[ind] = make_pair(a, b);
}

long long int dfs(long long int s, long long int mD, long long int n) {
    if (s == n) {
        return mD;
    }
    if (mark[s] != 1) {
        mark[s] = 1;
        for (long long int i: g[s]) {
            if (c[i] - f[i] > 0) {
                long long int d = dfs(edges[i], min(c[i] - f[i], mD), n);
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
    all_edges.resize(10001);
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
    long long int maxi = 0;
    long long int ans = 1;
    while (ans > 0) {
        mark.assign(n + 1, 0);
        ans = dfs(1, 10000003, n);
        maxi += ans;
    }
    set<int> v;
    mark_end.assign(n + 1, 0);
    for (int i = 0; i < 2 * m; i++) {
        if (i % 2 == 0) {
            if (mark[all_edges[i].second] == 1 && mark[all_edges[i].first] == 0) {
                v.insert(i);
            } else if (mark[all_edges[i].second] == 0 && mark[all_edges[i].first] == 1) {
                v.insert(i);
            }
        }
    }
    cout << v.size() << " " << maxi << endl;
    for (int i: v) {
        cout << i / 2 + 1 << " ";
    }
    return 0;
}
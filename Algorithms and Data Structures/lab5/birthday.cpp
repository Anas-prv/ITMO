#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<int> last;
vector<int> mark;
vector<int> inv_mark;
vector<int> found;
vector<vector<int>> edges(100001);
vector<vector<int>> inv_edges(100001);
int n, m;

int find(int s);

void my_dfs(bool path, int s) {
    vector<vector<int>> &current_edges = (path ? edges : inv_edges);
    vector<int> &current_mark = (path ? mark : inv_mark);
    if (current_mark[s] == -1) {
        current_mark[s] = 1;
        for (int i = 0; i < current_edges[s].size(); i++) {
            if (current_edges[s][i] == 1) {
                bool p;
                if (!path) {
                    p = i == last[s];
                } else {
                    p = s != last[i];
                }
                if (p) {
                    my_dfs(!path, i);
                }
            }
        }
    }
}

void print_answer() {
    int size = 0;
    int index = 0;
    while (index < m) {
        if (mark[index] != -1) {
            size = size + 1;
        }
        index = index + 1;
    }
    int remember = size;
    for (int i = 0; i < n; i++) {
        if (inv_mark[i] == -1) {
            size = size + 1;
        }
    }
    cout << size << endl;
    cout << remember << " " << size - remember << endl;
    for (int i = 0; i < m; i++) {
        if (mark[i] != -1) {
            cout << i + 1 << " ";
        }
    }
    cout << endl;
    for (int i = 0; i < n; i++) {
        if (inv_mark[i] == -1) {
            cout << i + 1 << " ";
        }
    }
}

void input() {
    int end;
    cin >> m;
    cin >> n;
    mark.assign(m, -1);
    inv_mark.assign(n, -1);
    last.assign(n, -1);
    edges.assign(m, vector<int>(n, 1));
    inv_edges.assign(n, vector<int>(m, 1));
    int begin = 0;
    while (begin < m) {
        cin >> end;
        if (end != 0) {
            end = end - 1;
            edges[begin][end] = 0;
            inv_edges[end][begin] = 0;
        } else {
            begin++;
        }
    }
}

void all() {
    for (int i = 0; i < m; i++) {
        if (find(i)) {
            mark.assign(m, -1);
        }
    }
    mark.assign(m, -1);
    found.assign(m, 0);
    for (int i = 0; i < m; i++) {
        found[i] = i;
    }
    for (int i = 0; i < n; i++) {
        if (last[i] != -1) {
            found[last[i]] = -1;
        }
    }
    for (int i = 0; i < m; i++) {
        if (found[i] != -1) {
            if (mark[i] == -1) {
                my_dfs(true, i);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    int k;
    cin >> k;
    for (int i = 0; i < k; i++) {
        input();
        all();
        print_answer();
        cout << endl;
    }
    return 0;
}

int find(int s) {
    if (mark[s] == -1) {
        mark[s] = 1;
        for (int i = 0; i < edges[s].size(); i++) {
            if (edges[s][i] != 0) {
                if (last[i] == -1 || (find(last[i]) == 1)) {
                    last[i] = s;
                    return 1;
                }
            }
        }
    }
    return 0;
}
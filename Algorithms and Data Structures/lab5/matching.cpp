#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<int> last(252, 0);
vector<int> mark(252, 0);
vector<vector<int>> edges(100001);

int find(int s);

void printAnswer(int n, int size) {
    int index = 1;
    while (index < n + 1) {
        if (last[index] != 0) {
            size = size + 1;
        }
        index = index + 1;
    }
    cout << size << endl;
    for (int i = 1; i < n + 1; i++) {
        if (last[i] != 0) {
            cout << last[i] << " ";
            cout << i << endl;
        }
    }
}

int main() {
    int n, m, end, size = 0;
    cin >> n;
    cin >> m;
    mark.resize(n + 1);
    last.resize(n + 1);
    int begin = 1;
    while (begin < n + 1) {
        cin >> end;
        if (end != 0) {
            edges[begin].emplace_back(end);
        } else {
            begin++;
        }
    }
    for (int i = 1; i < n + 1; i++) {
        mark.assign(n, 0);
        find(i);
    }
    printAnswer(m, size);
    return 0;
}

int find(int s) {
    if (mark[s] != 1) {
        mark[s] = 1;
        for (int i = 0; i < edges[s].size(); i++) {
            if ((find(last[edges[s][i]]) == 1) || last[edges[s][i]] == 0) {
                last[edges[s][i]] = s;
                return 1;
            }
        }
    }
    return 0;
}
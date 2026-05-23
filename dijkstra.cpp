


#include <iostream>
#include <vector>
#include <climits> // для INT_MAX

using namespace std;

class Edge {
public:
    int destination;
    int weight;

    Edge(int dest, int w) : destination(dest), weight(w) {}
};

class Graph {
private:
    int vertices;                
    vector<vector<Edge>> adjList;

public:
    Graph(int v) : vertices(v) {
        adjList.resize(v);
    }

    void addEdge(int from, int to, int weight) {
        adjList[from].push_back(Edge(to, weight));
    }

    void dijkstra(int start) {

        vector<int> dist(vertices, INT_MAX);
        vector<bool> visited(vertices, false);

        dist[start] = 0;

        for (int count = 0; count < vertices - 1; ++count) {
        
            int minDist = INT_MAX, u = -1;
            for (int i = 0; i < vertices; ++i) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }

           
            if (u == -1) break;

         
            visited[u] = true;

      
            for (const Edge& edge : adjList[u]) {
                int v = edge.destination;
                int weight = edge.weight;
                if (!visited[v] && dist[u] != INT_MAX && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

       
        cout << "\n=== result:(start with node" << start << ") ===\n";
        for (int i = 0; i < vertices; ++i) {
            cout << "node" << i << " : ";
            if (dist[i] == INT_MAX)
                cout << "Unattainable\n";
            else
                cout << dist[i] << "\n";
        }
    }

    void printGraph() {
        cout << "list adjacency with weights :\n";
        for (int i = 0; i < vertices; ++i) {
            cout << i << " -> ";
            for (const Edge& e : adjList[i]) {
                cout << "(" << e.destination << ", " << e.weight << ") ";
            }
            cout << endl;
        }
    }
};

int main() {

    Graph g(6);

    g.addEdge(0, 1, 4);
    g.addEdge(0, 2, 2);
    g.addEdge(1, 2, 1);
    g.addEdge(1, 3, 5);
    g.addEdge(2, 3, 8);
    g.addEdge(2, 4, 10);
    g.addEdge(3, 4, 2);
    g.addEdge(3, 5, 6);
    g.addEdge(4, 5, 3);

    g.printGraph();

    g.dijkstra(0);

    return 0;
}

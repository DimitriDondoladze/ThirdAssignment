package edu.sdsu.cs.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class DirectedGraph<V> implements IGraph<V> {

    TreeMap<V, Node> nodesMap;

    public DirectedGraph() {
        nodesMap = new TreeMap<>();
    }

    @Override
    public void add(V vertexName) {
        if (!this.contains(vertexName)) {
            nodesMap.put(vertexName, new Node(vertexName));
        }
    }

    @Override
    public void connect(V start, V destination) {
        Node startVertex = findNodeInMap(start);
        Node endVertex = findNodeInMap(destination);

        if (startVertex != null) {
            if (endVertex != null) {
                if (!startVertex.hasConnectionToNode(destination)) {
                    startVertex.nodes.add(endVertex);
                }
            } else {
                throw new NullPointerException("Destination vertex is not presented");
            }
        } else {
            throw new NullPointerException("Starting vertex vertex is not presented");
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(V label) {
        return false;
    }

    @Override
    public void disconnect(V start, V destination) {

    }

    @Override
    public boolean isConnected(V start, V destination) {
        return false;
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        if (findNodeInMap(vertexName) == null)
            throw new NoSuchElementException("Neighbour Vertex");

        LinkedList<V> neighborNodes = new LinkedList<>();
        for (Node node : (LinkedList<Node>) findNodeInMap(vertexName).nodes)
            neighborNodes.add((V) node.value);

        return neighborNodes;
    }

    @Override
    public void remove(V vertexName) {
        if (nodesMap.containsKey(vertexName))
            nodesMap.remove(vertexName);
        else
            throw new NoSuchElementException("Vertex Name");
    }

    @Override
    public List<V> shortestPath(V start, V destination) {
        return null;
    }

    public int size() {
        return nodesMap.size();
    }

    @Override
    public Iterable<V> vertices() {
        LinkedList<V> labelNodes = new LinkedList<>();

        for (Node node : nodesMap.values()) {
            labelNodes.add((V) node.value);
        }

        return labelNodes;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        Node originNode = findNodeInMap(origin);
        if (originNode == null)
            throw new NoSuchElementException("Origin");

        LinkedList<Node> visitedNodes = new LinkedList<>();
        visitedNodes.add(originNode);
        connectedGraphHelper(originNode, visitedNodes);

        DirectedGraph<V> newGraph = new DirectedGraph<>();
        for (Node edge : visitedNodes)
            newGraph.add(edge);

        return newGraph;
    }

    private LinkedList<Node> connectedGraphHelper(Node origin, LinkedList<Node> visited) {
        for (Node node : (LinkedList<Node>) origin.nodes) {
            if (!visited.contains(node)) {
                connectedGraphHelper(node, visited);
                visited.add(node);
            }
        }

        return visited;
    }

    private void add(Node node) {
        nodesMap.put((V) node.value, node);
    }

    private Node findNodeInMap(V value) {
        return nodesMap.containsKey(value)
                ? nodesMap.get(value)
                : null;
    }

    private class Node<V> {
        public V value;
        public LinkedList<Node> nodes;
        public int shortestPath = Integer.MAX_VALUE;

        public Node(V value) {
            this.value = value;
            nodes = new LinkedList<>();
        }

        public boolean hasConnectionToNode(V destination) {
            for (Node node : nodes) {
                if (node.value.equals(destination))
                    return true;
            }
            return false;
        }

        public boolean removeNode(V nodeToBeRemoved) {
            for (Node node : nodes) {
                if (node.value.equals(nodeToBeRemoved)) {
                    nodes.remove(node);
                    return true;
                }
            }
            return false;
        }
    }
}

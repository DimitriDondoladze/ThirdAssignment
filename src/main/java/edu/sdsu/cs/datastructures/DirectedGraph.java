package edu.sdsu.cs.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class DirectedGraph<V> implements IGraph<V> {

    TreeMap<V, Node> vertices;

    public DirectedGraph() {
        vertices = new TreeMap<>();
    }

    @Override
    public void add(V vertexName) {
        if (!this.contains(vertexName)) {
            vertices.put(vertexName, new Node(vertexName));
        }
    }

    @Override
    public void connect(V start, V destination) {
        Node startVertex = findNodeInVertices(start);
        Node endVertex = findNodeInVertices(destination);

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
        return null;
    }

    @Override
    public void remove(V vertexName) {

    }

    @Override
    public List<V> shortestPath(V start, V destination) {
        return null;
    }

    public int size() {
        return vertices.size();
    }

    @Override
    public Iterable<V> vertices() {
        return null;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        return null;
    }

    private void add(Node node) {
        vertices.put((V) node.value, node);
    }

    private Node findNodeInVertices(V value) {
        return vertices.containsKey(value)
                ? vertices.get(value)
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

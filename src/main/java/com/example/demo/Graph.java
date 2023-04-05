package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

class Graph<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public Map<T, List<T>> map = new HashMap();
    public static String[] array = new String[]{};


    Graph() {
    }
    public void addNewVertex(T s) {

        this.map.put(s, new LinkedList());
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = s.toString();

    }

    public void addNewEdge(T source, T destination, boolean bidirectional) {
        if (!this.map.containsKey(source)) {
            this.addNewVertex(source);
        }

        if (!this.map.containsKey(destination)) {
            this.addNewVertex(destination);
        }

        ((List) this.map.get(source)).add(destination);
        if (bidirectional) {
            ((List) this.map.get(destination)).add(source);
        }

    }

    public void countVertices() {
        System.out.println("Total number of vertices: " + this.map.keySet().size());
    }

    public void countEdges(boolean bidirectional) {
        int count = 0;

        Object v;
        for (Iterator var3 = this.map.keySet().iterator(); var3.hasNext(); count += ((List) this.map.get(v)).size()) {
            v = var3.next();
        }

        if (bidirectional) {
            count /= 2;
        }

        System.out.println("Total number of edges: " + count);
    }

    public boolean containsVertex(T s) {
        if (this.map.containsKey(s)) {
            System.out.println("The graph contains " + s + " as a vertex.");
            return true;
        } else {
            System.out.println("The graph does not contain " + s + " as a vertex.");
            return false;
        }
    }

    public void containsEdge(T s, T d) {
        if (((List) this.map.get(s)).contains(d)) {
            System.out.println("The graph has an edge between " + s + " and " + d + ".");
        } else {
            System.out.println("There is no edge between " + s + " and " + d + ".");
        }
    }

    public List<T> getNeighbors(T node) {
        return map.get(node);
    }

    public void printNeighbors(T node) {
        List<T> neighbors = getNeighbors(node);
        System.out.println(neighbors);
    }
}

package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class GraphImplementation implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Graph graph = new Graph();

    public GraphImplementation() {
    }



    //creates graph at the very start of the program
    //information from a text file
    public static void implementGraph() {
        try {
            File fileGraph = new File("graph.txt");
            Scanner myReader = new Scanner(fileGraph);

            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                int count = line.length() - line.replace(",", "").length();
                String[] tokens = line.split(", ");

                for(int i = 1; i < count + 1; ++i) {
                    graph.addNewEdge(tokens[0], tokens[i], true);
                }
            }

            myReader.close();
        } catch (FileNotFoundException var8) {
            System.out.println("An error occurred.");
            var8.printStackTrace();
        }

        graph.countVertices();
        graph.countEdges(true);
    }


    //calls a function to get neighbouring cities
    /*public static void nextCities() {
        System.out.println("Next cities to explore:");
        graph.printNeighbors(Location.location);
    }*/
}


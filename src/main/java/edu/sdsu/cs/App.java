package edu.sdsu.cs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.sdsu.cs.datastructures.DirectedGraph;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        String path = ValidateArgument(args);

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            DirectedGraph<String> graph = new DirectedGraph<>();
            while (reader.hasNextLine()) {
                String[] strings = reader.nextLine().split(",");
                if (strings.length == 0) {
                    graph.add(strings[0]);
                } else {
                    graph.add(strings[0]);
                    graph.add(strings[1]);
                    graph.connect(strings[0], strings[1]);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String ValidateArgument(String[] args) {
        try {
            ValidateLength(args);
            ValidateExtension(args);
            return args[0];
        } catch (Exception ex) {
            System.out.println("Due to following exception, default path will be used");
            System.out.println("Exception: " + ex.getMessage());
            return "layout.csv";
        }

    }

    private static void ValidateExtension(String[] args) throws Exception {
        String path = args[0];
        int last_index_before_extension = path.lastIndexOf(".");
        String extension = path.substring(last_index_before_extension);

        if (!extension.equals(".csv"))
            throw new Exception("File has an inappropriate extension");
    }

    private static void ValidateLength(String[] args) throws Exception {
        if (args.length == 0)
            throw new Exception("File path not provided");
        else if (args.length > 1)
            throw new Exception("Multiple possible paths provided");
    }
}

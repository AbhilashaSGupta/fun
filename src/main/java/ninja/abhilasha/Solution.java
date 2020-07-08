/*
Binary tree     10 - 7 - 4 - 2


            2
           /  \
         4      9
        /      / \
       7       5   11
      /  \           \
     3   10            8

find the largest node value and return the path to that
find 11: 11 - 9 - 2

 unique value
 largest node value.
 return path largest node to root
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    Node root = new Node(2);
    root.left = new Node(4);
    root.left.left = new Node(7);
    root.left.left.left = new Node(3);
    root.left.left.right = new Node(10);

    root.right = new Node(9);
    root.right.left = new Node(5);
    root.right.right = new Node(11);
    root.right.right.right = new Node(8);


    HighValueCalculator.calculate(root);

  }

  static class HighValueCalculator {

    static void calculate(Node root) {
      List<List<Integer>> allPaths = getPaths(root);
      //Now we have all paths.. lets pick the best path
      List<Integer> highValuePath = new ArrayList<>();
      int highValue = 0;

      for (List<Integer> currentPath : allPaths) {
        for (int i = 0; i < currentPath.size(); i++) {
          Integer nodeVal = currentPath.get(i);
          if (highValue < nodeVal) {
            //we found a path with a higher value.. capture the path till high value
            highValuePath = currentPath.subList(0, i + 1);
            highValue = nodeVal;
          }
        }
      }

      Collections.reverse(highValuePath);
      System.out.println(highValuePath.stream().map(Objects::toString).collect(Collectors.joining("->")));
    }


    static List<List<Integer>> getPaths(Node node) {
      int[] path = new int[1000];
      List<List<Integer>> allPaths = new ArrayList<>();
      getPathsRecur(node, path, 0, allPaths);

      return allPaths;
    }

    static void getPathsRecur(Node node, int[] path, int pathLen, List<List<Integer>> allPaths) {
      if (node == null) {
        return;
      }

      /* append this node to the path array */
      path[pathLen] = node.value;
      pathLen++;

      /* it's a leaf, so print the path that led to here  */
      if (node.left == null && node.right == null) {

        allPaths.add(addToPaths(path, pathLen));
      } else {
        /* otherwise try both subtrees */
        getPathsRecur(node.left, path, pathLen, allPaths);
        getPathsRecur(node.right, path, pathLen, allPaths);
      }
    }

    private static List<Integer> addToPaths(int[] path, int pathLen) {
      List<Integer> pathList = new ArrayList<>();
      int i;
      for (i = 0; i < pathLen; i++) {
        pathList.add(path[i]);
        ;
      }
      return pathList;
    }


  }

  public static class Node {
    //getters and setters
    int value;
    Node left;
    Node right;

    public Node(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }

  }

}

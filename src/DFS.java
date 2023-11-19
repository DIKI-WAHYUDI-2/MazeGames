import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

    class Node {
        int data;
        List<Node> children;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        void addChild(Node child) {
            children.add(child);
        }
    }

    public class DFS {
        public static boolean dfs(Node root, int target) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node current = stack.pop();
                System.out.println("Visiting Node: " + current.data);

                if (current.data == target) {
                    System.out.println("Target found!");
                    return true;
                }

                // Push child nodes to the stack
                for (Node child : current.children) {
                    stack.push(child);
                }
            }

            System.out.println("Target not found!");
            return false;
        }

        public static void main(String[] args) {
            // Buat pohon sebagai contoh
            Node root = new Node(1);
            Node child1 = new Node(2);
            Node child2 = new Node(3);
            Node child3 = new Node(4);
            Node grandchild1 = new Node(5);
            Node grandchild2 = new Node(6);

            root.addChild(child1);
            root.addChild(child2);
            child1.addChild(child3);
            child1.addChild(grandchild1);
            child2.addChild(grandchild2);

            // Lakukan pencarian menggunakan DFS
            int target = 5;
            boolean found = dfs(root, target);

            if (found) {
                System.out.println("Node " + target + " found in the tree!");
            } else {
                System.out.println("Node " + target + " not found in the tree.");
            }
        }
    }

public class BinaryTree<T> {

    public static class TreeNode<T> {
        public T value;
        public TreeNode<T> left;
        public TreeNode<T> right;

        public TreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static <T> int countNodes(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static <T> TreeNode<T> mirror(TreeNode<T> root) {
        if (root == null) {
            return null;
        }
        TreeNode<T> mirroredLeft = mirror(root.left);
        TreeNode<T> mirroredRight = mirror(root.right);

        TreeNode<T> newNode = new TreeNode<>(root.value);
        newNode.left = mirroredRight;
        newNode.right = mirroredLeft;

        return newNode;
    }

    public static <T> LinkedList<T> preorder(TreeNode<T> root) {
        LinkedList<T> list = new LinkedList<>();
        preorderHelper(root, list);
        return list;
    }

    private static <T> void preorderHelper(TreeNode<T> node, LinkedList<T> list) {
        if (node == null) {
            return;
        }
        list.addLast(node.value);
        preorderHelper(node.left, list);
        preorderHelper(node.right, list);
    }

    public static <T> LinkedList<String> findPath(TreeNode<T> root, T target) {
        java.util.List<String> pathList = new java.util.ArrayList<>();
        if (findPathHelper(root, target, pathList)) {
            LinkedList<String> result = new LinkedList<>();
            for (String direction : pathList) {
                result.addLast(direction);
            }
            return result;
        }
        return null;
    }

    private static <T> boolean findPathHelper(TreeNode<T> node, T target, java.util.List<String> path) {
        if (node == null) {
            return false;
        }

        if (node.value.equals(target)) {
            return true;
        }

        path.add("left");
        if (findPathHelper(node.left, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);

        path.add("right");
        if (findPathHelper(node.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);

        return false;
    }
}
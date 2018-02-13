package B_AdvancedDataStructures.binarySearchTree

/*
 Motivation: keep track of a collection of numbers
 Store them sorted, for easy retrieval and iteration

 Using a sorted linked list is O(n)
 We can insert and compare from the middle to cut down on the number of operations


root-->larger ->  larger
    \          \> smaller
     \>smaller -> larger
                \>smaller

Smaller on the left, larger on the right

Trees: contains a value and links to zero or more child nodes, single root node

Specific type of tree:
Binary Tree: each node has links to exactly two children, left and right, either or both may be null

*/

internal class SimpleBinaryTree(var root:Node? = null) {

    data class Node(var value:Int, var leftChild:Node? = null, var rightChild:Node? = null)
}

/*
Specific type of binary tree:
Binary Search Tree: ordered binary tree, every node in the tree is greater than each element in its left sub tree, every
node in the tree is less than each element in its right sub tree

Binary search tree traversal: perform inorder traversal to visit each node in sorted order
Starting at the root:
- visit the left subtree
- visit the node
- visit right subtree

20 > 23 > 27
  \> 10 > 15 > 17
       \> 5
*/

internal fun inorderTraversal(n:SimpleBinaryTree.Node?) {
    if (n == null) return
    inorderTraversal(n.leftChild)
    visit(n)
    inorderTraversal(n.rightChild)
}

internal fun visit(n:SimpleBinaryTree.Node) {
    println(n.value)
}

internal fun binaryTreeSearch(n:SimpleBinaryTree.Node?, v:Int):Boolean = when {
    n == null -> false
    v < n.value -> binaryTreeSearch(n.leftChild, v)
    v > n.value -> binaryTreeSearch(n.rightChild, v)
    else -> true // equal
}

internal fun binaryInsertion(n:SimpleBinaryTree.Node, v:Int):Boolean = when {
    n.value == v -> false
    v < n.value -> if (n.leftChild == null) true.also { n.leftChild = SimpleBinaryTree.Node(v) }
    else binaryInsertion(n.leftChild!!, v)
    n.rightChild == null -> true.also { n.rightChild = SimpleBinaryTree.Node(v) }
    else -> binaryInsertion(n.rightChild!!, v)

}

// we can search for and add elements to a binary search tree knowing that we need to keep the tree ordered
// for any node, smaller values are in its left subtree
// greater values are in its right subtree

internal fun binaryRemoval(n:SimpleBinaryTree.Node?, parent:SimpleBinaryTree.Node, v:Int):Boolean = when {
    n == null -> false
    v < n.value -> binaryRemoval(n.leftChild, n, v)
    v > n.value -> binaryRemoval(n.rightChild, n, v)
    else -> {
        when { // v == n.value
            n.leftChild != null && n.rightChild != null -> {
                n.value = maxValue(n.leftChild!!)
                binaryRemoval(n.leftChild, n, n.value)
            }
            parent.leftChild == n -> parent.leftChild = if (n.leftChild != null) n.leftChild else n.rightChild
        // parent.rightChild == n
            else -> parent.rightChild = if (n.leftChild != null) n.leftChild else n.rightChild
        }
        true
    }
}

internal fun maxValue(node:SimpleBinaryTree.Node):Int = when {
    node.leftChild!!.value > node.rightChild!!.value -> node.leftChild!!.value
    else -> node.rightChild!!.value
}

fun main(args:Array<String>) {
    val t = SimpleBinaryTree(
            SimpleBinaryTree.Node(16,
                    SimpleBinaryTree.Node(4,
                            SimpleBinaryTree.Node(3, SimpleBinaryTree.Node(1), SimpleBinaryTree.Node(5)),
                            SimpleBinaryTree.Node(6, null,
                                    SimpleBinaryTree.Node(8, null,
                                            SimpleBinaryTree.Node(12))))))
    inorderTraversal(t.root)
    binaryRemoval(t.root!!.leftChild, t.root!!, 4)
    println()
    inorderTraversal(t.root)
    println()

}
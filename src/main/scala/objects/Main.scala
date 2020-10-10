package objects

import classes.{BinaryTree, Menu, Node}


object Main{

  var binaryTree: BinaryTree[String] = new BinaryTree[String];
  val menu: Menu = new Menu;

  def main(args: Array[String]): Unit = {
  /*
    menu.chooseAnything();
    binaryTree.add("abcdef");
    binaryTree.add("bcdef");
    //binaryTree.delete("abcdef");
    binaryTree.traverseInOrder(binaryTree.root,0);
*/
    menu.workFunction();
  }

}

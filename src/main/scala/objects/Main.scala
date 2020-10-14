package objects

import classes.{BinaryTree, Menu, Node}


object Main{

  var binaryTree: BinaryTree[String] = new BinaryTree[String];
  val menu: Menu = new Menu;

  def main(args: Array[String]): Unit = {
    menu.workFunction();
  }

}

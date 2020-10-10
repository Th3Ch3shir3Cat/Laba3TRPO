package classes

class Node[T]()(implicit ev$1: T => Ordered[T]){

  private var value: T = _;
  var left: Node[T] = null;
  var right: Node[T] = null;
  private var sizeOfVertices: Int = 0;

  def this(valueNode: T)(implicit ev$1: T => Ordered[T]){
    this();
    value = valueNode;
  }

  def getValue(): T = this.value;
  def getLeft(): Node[T] = this.left;
  def getRight(): Node[T] = this.right;
  def getSizeOfVertices(): Int = this.sizeOfVertices;

  def setSizeOfVertices(number: Int): Unit ={
    this.sizeOfVertices = number;
  }
  def setValue(value: T): Unit ={
    this.value = value;
  }
  def addSizeOfVertices(): Unit ={
    this.sizeOfVertices += 1;
  }

}

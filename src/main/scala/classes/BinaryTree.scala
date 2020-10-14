package classes

class BinaryTree[T] {

  var root: Node[T] = null;
  var sizeBinaryTree: Int = 0;
  var arrayTops: Array[Node[T]] = Array();


  def setArrayTops(node: Node[T]): Unit ={
    if(node == null) return;
    setArrayTops(node.left);
    this.arrayTops = this.arrayTops :+ node;
    setArrayTops(node.right);
  }

  def traverseInOrder(node: Node[T], level: Integer): Unit ={
    if(node == null) return;
    traverseInOrder(node.left, level + 1);
    println(s"Уровень = $level Количество вершин в дереве = ${node.getSizeOfVertices()} Значение = ${node.getValue()}");
    traverseInOrder(node.right, level + 1);
  }

  def addNode(current: Node[T], value: T)(implicit ord: T => Ordered[T]) : Node[T] = {
    if(current == null){
      return new Node[T](value);
    }
    current.addSizeOfVertices();
    if(current.getValue() > value){
      current.left = addNode(current.left, value);
    }
    else if(current.getValue() < value){
      current.right = addNode(current.right, value);
    }else{
      return current;
    }
    current;
  }

  def add(value: T)(implicit ord: T => Ordered[T]): Unit ={
    this.root = addNode(this.root, value);
    this.sizeBinaryTree += 1;
    println(s"Значение $value добавлено");
  }

  def deleteNode(current: Node[T], value: T)(implicit ord: T => Ordered[T]): Node[T] = {
    if (current == null) return null;
    if(current.getValue() == value){
      if(current.left == null && current.right == null){
        return null;
      }
      if(current.right == null){
        return current.left;
      }
      if(current.left == null){
        return current.right;
      }
      var smallestValue: T = findSmallValue(current.right);
      current.setValue(smallestValue);
      current.right = deleteNode(current.right, smallestValue);
      return current;
    }
    if(current.getValue() > value){
      current.left = deleteNode(current.left, value);
    }
    current.right = deleteNode(current.right, value);
    current;
  }

  def delete(value: T)(implicit ord: T => Ordered[T]): Unit ={
    root = deleteNode(root, value);
    sizeBinaryTree -= 1;
    println("Значение удалено");
  }

  def findSmallValue(root: Node[T]): T ={
    if(root.left == null){
      root.getValue();
    }
    else{
      findSmallValue(root.left);
    }
  }

  def updateArrayTops(): Unit ={
    this.arrayTops = Array();
    setArrayTops(this.root);
  }

  def getBalance(start: Integer, finish: Integer)(implicit ord: T => Ordered[T]): Node[T] = {
    if(start > finish) return null;
    var newNode: Node[T] = null;
    if(start == finish){
      newNode = arrayTops(start);
      newNode.left = null;
      newNode.right = null;
      newNode.setSizeOfVertices(1);
      return newNode;
    }
    var middle: Integer = (start+finish)/2;
    newNode = arrayTops(middle);
    newNode.left = getBalance(start, middle - 1);
    newNode.right = getBalance(middle+1, finish);
    newNode.setSizeOfVertices(finish-start+1);
    newNode;
  }


  def getValueFromArrayTops(number: Integer): T = {
    arrayTops(number).getValue();
  }


}
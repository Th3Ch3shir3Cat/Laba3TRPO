package classes

import java.io.File

import javax.swing.JFrame

import scala.io.StdIn.{readInt, readLine}


class Menu {

  def printMenu(): Unit ={
    println("1) Прочитать из файла");
    println("2) Добавить вершину в дерево");
    println("3) Сбалансировать дерево")
    println("4) Удалить вершину по логическому номеру");
    println("5) Удалить вершину по ключу");
    println("6) Показать дерево");
    println("7) Выйти из программы");
    print("Выберите операцию: ");
  }

  def chooseAnything(): Int = {
    printMenu();
    scala.io.StdIn.readLine.toInt;
  }

  def readFile(filename: String): Seq[String] = {
    val bufferedSource = io.Source.fromFile(filename)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

  def workFunction(): Unit ={
    var choose: Int = 0;
    var binaryTree: BinaryTree[String] = new BinaryTree[String];

    do {
      choose = chooseAnything();
      choose match {
        case 1 =>
          val listNodeFromFile = readFile("1.txt");
          for(item <- listNodeFromFile){
            binaryTree.add(item);
          }
        case 2 =>
          var value: String = readLine("Введите значение: ");
          binaryTree.add(value);
          binaryTree.updateArrayTops();
        case 3 =>
          binaryTree.getBalance(0,binaryTree.sizeBinaryTree-1);
          binaryTree.updateArrayTops();
        case 4 =>
          print(s"Введите логический номер(1-${binaryTree.arrayTops.length}): ");
          var number = readInt();
          var valueFromArray: String = binaryTree.getValueFromArrayTops(number-1);
          binaryTree.delete(valueFromArray);
          binaryTree.updateArrayTops();
        case 5 =>
          var value: String = readLine("Введите значение: ");
          binaryTree.delete(value);
          binaryTree.updateArrayTops();
        case 6 =>
          var drawer: DrawTree[String] = new DrawTree[String](binaryTree.root);
          var frame = new JFrame("Дерево"){
            setBounds(100, 100, 700, 500);
            add(drawer);
            setVisible(true);
          }
        case 7 =>
      }
    }while(choose != 7);
  }

}

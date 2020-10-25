package classes

import java.awt.{BorderLayout, Container, Dimension, Font}
import java.awt.event.{ActionEvent, ActionListener}
import java.io.{BufferedReader, File, FileNotFoundException, FileReader, IOException}
import java.util

import javax.swing.{JButton, JComboBox, JFileChooser, JFrame, JLabel, JPanel, JTextField}
import javax.swing.border.EmptyBorder

class TreeGUI() extends JFrame {

  private var binaryTree: BinaryTree[String] = new BinaryTree[String];
  private var nameFunctional: JLabel = null
  private var contentPane: JPanel = null
  private var buttonsPane: JPanel = null
  private var stringForAdd: JTextField = null
  private var buttonBalance: JButton = null
  private var addRandomButton: JButton = null
  private var addStringButton: JButton = null
  private var deleteButtonForNumber: JButton = null
  private var deleteButtonForValue: JButton = null
  private var downloadFromFile: JButton = null
  private var node: (Node[T]) forSome {type T <: Comparable[T]} = null
  private var drawer: DrawTree[String] = new DrawTree[String]();
  private var masForNumber: util.Vector[Integer] = null
  private var comboBoxForNumber: JComboBox[Integer] = null
  private var comboBoxForValue: JComboBox[String] = null
  private var masForValue: util.Vector[String] = null


  setTitle("Лабораотная работа №4")
  setBounds(100, 100, 700, 500)
  val container: Container = getContentPane
  contentPane = new JPanel
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5))
  contentPane.setLayout(new BorderLayout(0, 0))
  masForValue = new util.Vector[String]

  /**
   * Заменить на чтение из файла
   */

  for (i <- 0 until 5) {
    val randomString: RandomString = new RandomString
    val string: String = randomString.getResultString
    binaryTree.add(string)
  }
  binaryTree.traverseInOrder(binaryTree.root, 0)
  binaryTree.setArrayTops(binaryTree.root)
  setMasForNumber()
  setMasForValue()
  nameFunctional = new JLabel("Функционал программы")
  nameFunctional.setFont(new Font("Times New Roman", Font.PLAIN, 16))
  nameFunctional.setSize(new Dimension(180, 30))
  nameFunctional.setLocation(20, 10)
  comboBoxForNumber = new JComboBox[Integer](masForNumber)
  comboBoxForValue = new JComboBox[String](masForValue)
  comboBoxForNumber.setSize(new Dimension(40, 30))
  comboBoxForValue.setSize(new Dimension(80, 30))
  comboBoxForNumber.setLocation(10, 220)
  comboBoxForValue.setLocation(10, 290)
  comboBoxForNumber.setEditable(true)
  comboBoxForValue.setEditable(true)
  this.node = binaryTree.root
  drawer = new DrawTree[String](binaryTree.root);
  contentPane.add(drawer)
  buttonsPane = new JPanel
  buttonsPane.setPreferredSize(new Dimension(200, getHeight))
  buttonsPane.setLayout(null)
  stringForAdd = new JTextField
  stringForAdd.setSize(new Dimension(180, 20))
  stringForAdd.setLocation(20, 50)
  buttonBalance = createButton("Балансировать", 130, 30, 20, 380)
  buttonBalance.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      binaryTree.root = binaryTree.getBalance(0, binaryTree.sizeBinaryTree - 1)
      binaryTree.updateArrayTops()
      updateContentPane()
    }
  })
  addStringButton = createButton("Добавить", 130, 30, 60, 80)
  addStringButton.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      val stringFromLabel: String = stringForAdd.getText
      binaryTree.add(stringFromLabel)
      binaryTree.updateArrayTops()
      updateContentPane()
    }
  })
  addRandomButton = createButton("<html>Добавить<br>случайно<br>сгенерированную<br>строку</html>", 130, 80, 60, 120)
  addRandomButton.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      val randomString: RandomString = new RandomString
      binaryTree.add(randomString.getResultString)
      binaryTree.updateArrayTops()
      updateContentPane()
    }
  })
  deleteButtonForNumber = createButton("<html>Удалить по<br>логическому номеру</html>", 130, 60, 60, 210)
  deleteButtonForNumber.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      binaryTree.delete(binaryTree.getValueFromArrayTops(comboBoxForNumber.getSelectedIndex))
      binaryTree.updateArrayTops()
      updateContentPane()
    }
  })
  deleteButtonForValue = createButton("<html>Удалить по<br>ключу</html>", 90, 60, 100, 280)
  deleteButtonForValue.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      binaryTree.delete(comboBoxForValue.getSelectedItem.toString)
      binaryTree.updateArrayTops()
      updateContentPane()
    }
  })
  downloadFromFile = createButton("Загрузить из файла", 160, 40, 20, 420)
  downloadFromFile.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      createDirectory()
      val file: File = openDialogForChooseFile
      if (file != null) readFromFile(file)
    }
  })
  buttonsPane.add(nameFunctional)
  buttonsPane.add(stringForAdd)
  buttonsPane.add(addStringButton)
  buttonsPane.add(addRandomButton)
  buttonsPane.add(comboBoxForNumber)
  buttonsPane.add(deleteButtonForNumber)
  buttonsPane.add(comboBoxForValue)
  buttonsPane.add(deleteButtonForValue)
  buttonsPane.add(buttonBalance)
  buttonsPane.add(downloadFromFile)
  container.add(contentPane)
  container.add(buttonsPane, BorderLayout.EAST)
  setVisible(true)

  def setMasForNumber(): Unit = {
    masForNumber = new util.Vector[Integer]
    for (i <- 0 until binaryTree.sizeBinaryTree) {
      masForNumber.add(i + 1)
    }
  }

  def setMasForValue(): Unit = {
    masForValue = new util.Vector[String]
    for (i <- 0 until binaryTree.sizeBinaryTree) {
      masForValue.add(binaryTree.getValueFromArrayTops(i))
    }
  }

  def updateContentPane(): Unit = {
    this.contentPane.removeAll()
    updateDataForComboBox()
    drawer = new DrawTree(binaryTree.root)
    contentPane.add(drawer)
    revalidate()
    repaint()
  }

  def updateDataForComboBox(): Unit = {
    setMasForValue()
    setMasForNumber()
    comboBoxForValue.removeAllItems()
    comboBoxForNumber.removeAllItems()
    for (i <- 0 until masForValue.size) {
      comboBoxForValue.insertItemAt(masForValue.get(i), i)
      comboBoxForNumber.insertItemAt(i + 1, i)
    }
    comboBoxForNumber.setSelectedIndex(0)
    comboBoxForValue.setSelectedIndex(0)
  }

  private def createButton(text: String, width: Int, height: Int, locX: Int, locY: Int): JButton = {
    val button: JButton = new JButton(text)
    button.setSize(width, height)
    button.setLocation(locX, locY)
    button
  }

  private def createDirectory(): Unit = {
    val newDirectory: File = new File("files")
    if (!newDirectory.exists) {
      var result: Boolean = false
      try {
        newDirectory.mkdir
        result = true
      } catch {
        case se: SecurityException =>
          System.out.println("Ошибка создания дирректории")
      }
      if (result) System.out.println("Директория создана")
    }
  }

  private def openDialogForChooseFile: File = {
    val fileChooser: JFileChooser = new JFileChooser("files")
    fileChooser.setDialogTitle("Выберите файл")
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY)
    val result: Int = fileChooser.showSaveDialog(TreeGUI.this)
    if (result == JFileChooser.APPROVE_OPTION) {
      val file: File = fileChooser.getSelectedFile
      return file
    }
    null
  }

  def readFromFile(file: File): Unit = {
    try {
      val fileReader: FileReader = new FileReader(file)
      val bufferedReader: BufferedReader = new BufferedReader(fileReader)
      var line: String = bufferedReader.readLine
      binaryTree = new BinaryTree[String];
      while ( {
        line != null
      }) {
        binaryTree.add(line)
        line = bufferedReader.readLine
      }
      binaryTree.traverseInOrder(binaryTree.root, 0)
      binaryTree.setArrayTops(binaryTree.root)
      setMasForNumber()
      setMasForValue()
      updateContentPane()
      updateDataForComboBox()
    } catch {
      case fileNotFoundException: FileNotFoundException =>
        fileNotFoundException.printStackTrace()
      case ioException: IOException =>
        ioException.printStackTrace()
    }
  }
}
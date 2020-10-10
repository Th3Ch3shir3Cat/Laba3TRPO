package classes

import java.awt.{Font, FontMetrics, Graphics, Point}

import javax.swing.JPanel

class DrawTree[T] extends JPanel{
  var node: Node[T] = null;

  def this(node: Node[T]){
    this();
    this.node = node;
  }


  def drawTree(g: Graphics, StartWidth: Int, EndWidth: Int, StartHeight: Int, Level: Int, node: Node[T]): Point = {
    var data: String = String.valueOf(node.getValue());
    g.setFont(new Font("Tahoma", Font.BOLD,20));
    var fm: FontMetrics = g.getFontMetrics();
    var dataWidth: Int = fm.stringWidth(data);

    var textPos: Point = new Point((StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
    g.drawString(data, textPos.x, textPos.y)

    if (node.getLeft != null) {
      var child1 = drawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getLeft)
      drawLine(g, textPos, child1)
    }
    if (node.getRight != null) {
      var child2 = drawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getRight)
      drawLine(g, textPos, child2)
    }

    return textPos;
  }

  override protected def paintComponent(g: Graphics): Unit = {
    g.setFont(new Font("Tahoma", Font.BOLD, 20))
    drawTree(g, 0, getWidth, 0, getHeight / 5, node)
  }

  def drawLine(g: Graphics, p1: Point, p2: Point): Unit = {
    g.drawLine(p1.x, p1.y, p2.x, p2.y)
  }

}

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class Classe {	
	
	
public static void main(String[] args) throws IOException {
	 SVGGraphics2D g2 = new SVGGraphics2D(300, 200);
	 g2.setPaint(Color.RED);
	 g2.draw(new Rectangle(10, 10, 280, 180));
	 String svgElement = g2.getSVGElement();
	 System.out.println(svgElement);
	 String filename = "image" + System.currentTimeMillis() + ".svg";
	 File image = new File(filename);
	 BufferedWriter writer=new BufferedWriter(new FileWriter(image));
	 writer.write(svgElement);
	 writer.close();
}	
}

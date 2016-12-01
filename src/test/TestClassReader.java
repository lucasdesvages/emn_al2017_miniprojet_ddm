package test;

/**
 * Classe de test à dessiner
 * 
 */

public class TestClassReader implements TestInterface {

	private double attr1;
	private int attr2;
	private String attr3;

	public TestClassReader(double attr1, int attr2, String attr3) {
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
	}

	public TestClassReader() {
		this.attr1 = 0.0;
		this.attr2 = 0;
		this.attr3 = "";
	}

	public double getAttr1() {
		return attr1;
	}

	public void setAttr1(double attr1) {
		this.attr1 = attr1;
	}

	public int getAttr2() {
		return attr2;
	}

	public void setAttr2(int attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

}

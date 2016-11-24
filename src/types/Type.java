package types;

public interface Type {

	String getType();

	String getName();

	String getPackage();

	String[] getMethods();

	String[] getInterfaces();

	Class<?> getC();

}

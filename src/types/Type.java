package types;

import java.util.ArrayList;

public interface Type {

	String getType();

	String getName();

	String getPackage();

	String[] getHeader();

	String[] getMethods();

	String[] getInterfaces();

	Class<?> getC();

	ArrayList<String[]> getDescription();

}

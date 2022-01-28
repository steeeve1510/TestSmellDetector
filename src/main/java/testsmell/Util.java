package testsmell;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Util {

    public static boolean isValidTestMethod(MethodDeclaration n) {
        if (n.getAnnotationByName("Ignore").isPresent()) {
            return false;
        }
        //only analyze methods that either have a @test annotation (Junit 4) or the method name starts with 'test'
        if (!n.getAnnotationByName("Test").isPresent() && !n.getNameAsString().toLowerCase().startsWith("test")) {
            return false;
        }
        return !n.getModifiers().contains(Modifier.PRIVATE);

    }

    public static boolean isValidSetupMethod(MethodDeclaration n) {
        if (n.getAnnotationByName("Ignore").isPresent()) {
            return false;
        }
        //only analyze methods that either have a @Before annotation (Junit 4) or the method name is 'setUp'
        if (!n.getAnnotationByName("Before").isPresent() && !n.getNameAsString().equals("setUp")) {
            return false;
        }
        return !n.getModifiers().contains(Modifier.PRIVATE);

    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}

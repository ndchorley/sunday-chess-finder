package common.com.xyphias.sundaychessfinder;

public class FilePaths {
    public static String resourcePathOf(String fileName) {
        return FilePaths.class.getClassLoader().getResource(fileName).getPath();
    }
}

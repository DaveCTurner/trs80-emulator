package dct25.trs80.inMemoryCompiler;

import org.junit.Test;

public class TestCompile {
    @Test public void shouldCompileAndRun() throws Exception {
        InMemorySourceCompiler imsCompiler = new InMemorySourceCompiler("Test",
                "public class Test {" +
                "   public static void main(String args[]) {" +
                "      System.out.println(\"Output of program:\");" +
                "      for (int i=0; i<10; i++) {" +
                "         System.out.println(i);" +
                "      }" +
                "      System.out.println(\"End of output.\");" + 
                "   }" +
        "}");
        imsCompiler.executeMain();
    }
}

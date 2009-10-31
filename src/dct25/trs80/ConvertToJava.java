package dct25.trs80;

import java.io.*;

import dct25.trs80.emulator.BasicToJavaCompiler;
import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.Program;

public class ConvertToJava {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            return;
        }
        String basicFileName = args[0];

        Program p = null;
        FileReader fr = null;
        try {
            fr = new FileReader(basicFileName);
            char[] buf = new char[1024];
            StringBuilder sb = new StringBuilder();
            int numRead = 0;
            while ((numRead = fr.read(buf)) != -1) {
                sb.append(buf, 0, numRead);
            }
            Reader input = new StringReader(sb.toString());
            beaver.Scanner scanner = new TRS80Scanner(input);
            TRS80Parser parser = new TRS80Parser();
            Object o = parser.parse(scanner);
            p = (Program) o;
        } finally {
            if (null != fr) {
                fr.close();
            }
        }
        
        String className = basicFileName.replace('.', '_');
        BasicToJavaCompiler jc = new BasicToJavaCompiler(className, "dct25.trs80.programs");
        System.out.print(jc.generateCode(p));
    }

}

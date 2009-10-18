package dct25.trs80.inMemoryCompiler;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

import sun.tools.javac.*;  
import sun.tools.java.*;

class InMemorySourceCompiler {
    protected String name;
    protected String source;
    protected Class<?> compiledClass;

    public InMemorySourceCompiler(String name, String source) throws Exception {
        this.name = name;
        this.source = source;
        loadClass();
    }

    @SuppressWarnings("deprecation")
    protected void loadClass() throws Exception {
        ClassPath cp = new ClassPath(System.getProperty("java.class.path"));
        OutputStream os = System.out;
        BatchEnvironment be = new BatchEnvironment(os, cp);
        be.flags = 0x41004;
        be.majorVersion = 45;
        be.minorVersion = 3;
        be.covFile = null;
        be.setCharacterEncoding(null);

        be.parseFile(new InMemorySourceClassFile(name+".java", source));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);

        be.flushErrors();
        Vector<Object> v = new Vector<Object>();

        for (Enumeration enm = be.getClasses(); enm.hasMoreElements();) {
            v.addElement(enm.nextElement());
        }

        for (int i=0; i<v.size(); i++) {
            ClassDeclaration cd = (ClassDeclaration) v.elementAt(i);
            Object object = cd.getClassDefinition(be);

            if (object instanceof SourceClass) {
                SourceClass sourceclass = (SourceClass) object;
                cd.setDefinition(sourceclass, 5);
                // SourceClass sourceclass1 = (SourceClass) object;
                baos.reset();
                sourceclass.compile(baos);
            }
            else if (object instanceof BinaryClass) {
                BinaryClass binaryclass = (BinaryClass) object;
                binaryclass.write(be, baos);
            }
            byte[] b = baos.toByteArray();

            InMemorySourceCompilerClassLoader myClassLoader = new InMemorySourceCompilerClassLoader();
            compiledClass = myClassLoader.getClassFromBytes(name, b);
        }
    }

    public void executeMain() throws Exception {
        Method m = compiledClass.getMethod("main", new Class[]{ String[].class });
        m.invoke(null, new Object[]{null});
    }

    static class InMemorySourceCompilerClassLoader extends ClassLoader
    {
        public Class<?> getClassFromBytes(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}

package dct25.trs80.inMemoryCompiler;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

import dct25.trs80.emulator.Executable;

import sun.tools.java.*;
import sun.tools.javac.*;

public class InMemorySourceCompiler {
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
        ClassPath cp = new ClassPath((System.getProperty("java.class.path") 
                + ":/usr/lib/jvm/java-6-openjdk/jre/lib/rt.jar:bin"));
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
            } else {
                throw new Exception("Class definition is not a SourceClass");
            }
            byte[] b = baos.toByteArray();
            if (0 == b.length) { throw new Exception("Class definition is empty"); }
            
            InMemorySourceCompilerClassLoader myClassLoader = new InMemorySourceCompilerClassLoader();
            compiledClass = myClassLoader.getClassFromBytes(null, b);
        }
    }

    public void executeMain() throws Exception {
        Method m = compiledClass.getMethod("main", new Class[]{ String[].class });
        m.invoke(null, new Object[]{null});
    }

    class InMemorySourceCompilerClassLoader extends ClassLoader
    {
        public InMemorySourceCompilerClassLoader() {
            super(InMemorySourceCompilerClassLoader.class.getClassLoader());
        }
        
        public Class<?> getClassFromBytes(String name, byte[] b) throws ClassNotFoundException {
            return defineClass(name, b, 0, b.length);
        }
    }

    public Executable instantiate() throws SecurityException, NoSuchMethodException,
    IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> m = compiledClass.getConstructor(new Class[0]);
        return (Executable) m.newInstance(new Object[0]);
    }
}

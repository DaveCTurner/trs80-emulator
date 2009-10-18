package dct25.trs80.inMemoryCompiler;

import java.util.*;
import java.io.*;

import sun.tools.java.*;

class InMemorySourceClassFile extends ClassFile
{
    private String filename;
    private String text;

    public InMemorySourceClassFile(String filename, String text) {
        super(new File(filename));
        this.filename = filename;
        this.text = text;
    }

    public String getAbsoluteName() {
        return filename;
    }

    public boolean exists() {
        return true;
    }

    @SuppressWarnings("deprecation")
    public InputStream getInputStream() {
        return new StringBufferInputStream(text);
    }

    public String getName() {
        return filename;
    }

    public String getPath() {
        return "";
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isZipped() {
        return false;
    }

    public long lastModified() {
        return new Date().getTime();
    }

    public long length() {
        return text.length();
    }

    public String toString() {
        return filename;
    }
}

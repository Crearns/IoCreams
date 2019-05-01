package com.cms.ioCreams.core.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class FileSystemResource extends AbstractResource {

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public final String getPath() {
        return this.path;
    }

    public boolean exists() {
        return this.file.exists();
    }

    public boolean isReadable() {
        return (this.file.canRead() && !this.file.isDirectory());
    }

    public InputStream getInputStream() throws IOException {
        try {
            return Files.newInputStream(this.file.toPath());
        }
        catch (NoSuchFileException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    public boolean isWritable() {
        return (this.file.canWrite() && !this.file.isDirectory());
    }


    public boolean isFile() {
        return this.file.isFile();
    }

    public File getFile() {
        return this.file;
    }

    public long contentLength() throws IOException {
        return this.file.length();
    }

    @Override
    public String getFilename(){
        return file.getName();
    }
}

package com.cms.ioCreams.core.io.support;

import com.cms.ioCreams.core.io.FileSystemResource;
import com.cms.ioCreams.core.io.InputStreamResource;
import com.cms.ioCreams.core.io.Resource;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class EncodedResource implements InputStreamResource {

    private final Resource resource;


    private final String encoding;


    private final Charset charset;

    public EncodedResource(Resource resource) {
        this.resource = resource;
        encoding = null;
        charset = null;
    }

    public EncodedResource(Resource resource, String encoding) {
        this.resource = resource;
        this.encoding = encoding;
        charset = null;
    }

    public EncodedResource(Resource resource, Charset charset) {
        this.resource = resource;
        encoding = null;
        this.charset = charset;
    }

    public Resource getResource() {
        return resource;
    }

    public String getEncoding() {
        return encoding;
    }

    public Charset getCharset() {
        return charset;
    }

    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }


    @Override
    public int hashCode() {
        return this.resource.hashCode();
    }

    @Override
    public String toString() {
        return this.resource.toString();
    }
}

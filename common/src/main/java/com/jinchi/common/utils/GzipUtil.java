package com.jinchi.common.utils;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.Charsets;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;

public class GzipUtil {

	
	/**
     * 处理 Gizp 压缩的数据.
     * 
     * @param str
     * @return
     * @throws IOException
     */
    public static String conventFromGzip(ResponseEntity<byte[]> response) throws IOException {
    	if(response==null||response.getBody()==null) {
    		return null;
    	}
    	GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(response.getBody()));
    	BufferedReader reader = new BufferedReader(new InputStreamReader(gzip));
    	StringWriter writer = new StringWriter();
    	String line;
    	while ((line = reader.readLine()) != null) {
    	    writer.write(line);
    	}
    	String responseString = writer.toString();
    	return responseString;
    }
    
    public static String conventFromDefault(ResponseEntity<byte[]> response) throws IOException {
    
    	return new String(response.getBody());
    }

}

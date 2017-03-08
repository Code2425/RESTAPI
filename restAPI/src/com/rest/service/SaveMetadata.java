package com.rest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;

public class SaveMetadata {
	
	public void saveMetaData(Path file)
	{
		//Path file1 = Paths.get("d:\\test.jpeg");
        System.out.printf("%nBasic attributes of '%s':%n",file.toString());
      //  PosixFileAttributes attrs = Files.readAttributes(file1, PosixFileAttributes.class);
        PosixFileAttributes attrs = null;
		try {
			attrs = Files.readAttributes(file, PosixFileAttributes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("directory: " + attrs.isDirectory());
        System.out.println("is other : " + attrs.isOther());
        System.out.println("regular  : " + attrs.isRegularFile());
        System.out.println("symlink  : " + attrs.isSymbolicLink());
        System.out.println("size     : " + attrs.size());
        System.out.println("unique id: " + attrs.fileKey());
        System.out.println("access time  : " + attrs.lastAccessTime());
        System.out.println("creation time: " + attrs.creationTime());
        System.out.println("modified time: " + attrs.lastModifiedTime());
       // System.out.println("owner: " + attrs.owner());
        //System.out.printf("group: " + attrs.group());
	}

}

package com.rest.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

import jdk.internal.jfr.events.FileWriteEvent;

public class SaveMetadata {

	public void saveMetadata() throws IOException {

		System.out.println("inside class");
		Path file1 = Paths.get("d:\\test\\Demofile.txt");
		System.out.println("after class");
		BasicFileAttributes attrs = Files.readAttributes(file1,
				BasicFileAttributes.class);// Reading basic attribute/metadata
											// of the file
		System.out.println("directory: " + attrs.isDirectory());
		System.out.println("is other : " + attrs.isOther());
		System.out.println("regular  : " + attrs.isRegularFile());
		System.out.println("symlink  : " + attrs.isSymbolicLink());
		System.out.println("size     : " + attrs.size());
		System.out.println("unique id: " + attrs.fileKey());
		System.out.println("access time  : " + attrs.lastAccessTime());
		System.out.println("creation time: " + attrs.creationTime());
		System.out.println("modified time: " + attrs.lastModifiedTime());

		FileWriter fw = new FileWriter("d:\\test\\Write.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		// String content = "This is the content to write into file\n";
		System.out.println("after bw");
		bw.write(attrs.lastAccessTime().toString());
		bw.newLine();
		bw.write(attrs.creationTime().toString());
		System.out.println("bytes written");
		
	
		bw.newLine();
		bw.write(attrs.lastModifiedTime().toString());
		bw.newLine();
		System.out.println("Done");
		bw.close();
		fw.close();
	}
}
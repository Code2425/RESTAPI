package com.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/files")
public class FileUploadService {

	//To GET The file.
	@GET
	@Path("/getfile")
	@Produces({ "text/plain", "image/png" })
	public Response getFile() {

		File file = new File("d:\\test.jpeg");

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=DisplayName-Demofile.txt");
		return response.build();
	}

	//to upload the file
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@Context HttpHeaders headers,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("path") String path) {
		MediaType mediatype = headers.getMediaType();
		String uploadedFileLocation = path + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		String output = "File uploaded to : " + uploadedFileLocation
				+ "\n referer is :" + mediatype;
		return Response.status(201).entity(output).encoding(output)
				.lastModified(new Date()).build();
	}

	// save the file to local location
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
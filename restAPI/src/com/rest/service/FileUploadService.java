package com.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/files")
public class FileUploadService {

	// To GET The file.
	@GET
	@Path("/getfile")
	@Produces({ "text/plain", "image/png" })
	public Response getFile() throws IOException {

		java.nio.file.Path file1 = Paths.get("d:\\Demofile.txt");
		SaveMetadata saveMetadata = new SaveMetadata();
		saveMetadata.saveMetaData(file1);

		File file = new File("d:\\Demofile.txt");
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=DisplayName-Demofile.txt");
		return response.build();
	}

	// to upload the file
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("path") String path) throws IOException {

		String uploadedFileLocation = path + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		String output = "File uploaded to : " + uploadedFileLocation;				
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
package com.spring.ecommerce.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ecommerce.model.Image;
import com.spring.ecommerce.repository.ImageRepo;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link ImageServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-2-09
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageServiceImpl implements ImageService{
	

	private final ImageRepo repo;
	/*************************************************************************
	 * Upload a new Image
	 * 
	 * @param ob {@link Image} object
	 * @return {@link Image}
	 *************************************************************************/
	@Override
	public Image uplaodImage(MultipartFile file) throws IOException {
		//System.out.println("Original Image Byte Size - " + file.getBytes().length);
				Image img = new Image(file.getOriginalFilename(), file.getContentType(),
						compressBytes(file.getBytes()));
				repo.save(img);
				return repo.save(img);
	}

	/*************************************************************************
	 * Get Image  {@link Image} by id
	 * 
	 * @return {@link Image}
	 *************************************************************************/
	@Override
	public Image getImage(String id) {
		final Optional<Image> retrievedImage = repo.findById(id);
		Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		return img;
	}
	
	// compress the image bytes before storing it in the database
	private static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}

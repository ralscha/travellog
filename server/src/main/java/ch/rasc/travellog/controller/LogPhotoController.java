package ch.rasc.travellog.controller;

import static ch.rasc.travellog.db.tables.Log.LOG;
import static ch.rasc.travellog.db.tables.LogPhoto.LOG_PHOTO;
import static ch.rasc.travellog.db.tables.Travel.TRAVEL;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import org.jooq.DSLContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

import ch.rasc.travellog.Application;
import ch.rasc.travellog.config.security.AppUserDetail;
import ch.rasc.travellog.dto.PhotoDto;
import ch.rasc.travellog.service.PhotoStorageService;

@RestController
@RequestMapping("/be")
public class LogPhotoController {

	private final DSLContext dsl;

	private MessageDigest messageDigest;

	private final PhotoStorageService photoStorageService;
	
	public LogPhotoController(DSLContext dsl, PhotoStorageService photoStorageService) {
		this.dsl = dsl;
		this.photoStorageService = photoStorageService;

		try {
			this.messageDigest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			Application.log.error("get MD5 instance");
		}
	}

	/*
	@GetMapping("fetch_photo/{id}")
	public ResponseEntity<byte[]> fetchPhoto(@PathVariable("id") String id) throws IOException {
		return ResponseEntity.ok(this.photoStorageService.fetch(storageId));
	}
	
	@GetMapping("fetch_thumbnail/{id}")
	public ResponseEntity<byte[]> fetchThumbnail(@PathVariable("id") String id) throws IOException {
		return ResponseEntity.ok(this.photoStorageService.fetch(storageId));
	}
	*/
	
	@PostMapping("list_photo")
	public List<PhotoDto> listPhoto(@AuthenticationPrincipal AppUserDetail userDetails,
			@RequestParam("id") long id) {
	return List.of();
//		this.dsl.select(LOG_PHOTO.ID, LOG_PHOTO.NAME, LOG_PHOTO.MIME_TYPE,
//				LOG_PHOTO.SIZE, LOG_PHOTO.UPDATED)
//				.from(LOG_PHOTO)
//				.join(LOG).onKey()
//                .join(TRAVEL).onKey()
//				.where(LOG_PHOTO.ID.eq(id).and(TRAVEL.APP_USER_ID.eq(userDetails.getAppUserId())))
//				.fetch().stream().map(record -> {
//				        new PhotoDto(
//				        		record.get(LOG_PHOTO.ID),
//				        		record.get(LOG_PHOTO.STORAGE),
//				        		record.get(LOG_PHOTO.NAME),
//				        		record.get(LOG_PHOTO.SIZE),
//				        		record.get(LOG_PHOTO.MIME_TYPE),
//				        		
//				        		record.get(LOG_PHOTO.SIZE),
//				        		record.get(LOG_PHOTO.UPDATED),
//				        		); 
//				        });
		
		
	}
	
	@PostMapping("delete_photo")
	public void deletePhoto(@AuthenticationPrincipal AppUserDetail userDetails,
			@RequestParam("id") long id) {

		Set<Long> travelIds = this.dsl.select(TRAVEL.ID).from(TRAVEL)
				.where(TRAVEL.APP_USER_ID.eq(userDetails.getAppUserId()))
				.fetchSet(TRAVEL.ID);

		Long photoId = this.dsl.select(LOG_PHOTO.ID).from(LOG_PHOTO).join(LOG).onKey()
				.where(LOG_PHOTO.ID.eq(id).and(LOG.TRAVEL_ID.in(travelIds)))
				.fetchOne(LOG_PHOTO.ID);

		if (photoId != null) {
			this.dsl.delete(LOG_PHOTO).where(LOG_PHOTO.ID.eq(photoId)).execute();
		}
	}

	@PostMapping("upload_photo")
	public void uploadPhoto(@AuthenticationPrincipal AppUserDetail userDetails,
			@RequestParam("logId") long logId,
			@RequestParam("file") List<MultipartFile> files)
			throws IOException, ImageProcessingException {

		Set<Long> travelIds = this.dsl.select(TRAVEL.ID).from(TRAVEL)
				.where(TRAVEL.APP_USER_ID.eq(userDetails.getAppUserId()))
				.fetchSet(TRAVEL.ID);

		Long checkLogId = this.dsl.select(LOG.ID).from(LOG)
				.where(LOG.ID.eq(logId).and(LOG.TRAVEL_ID.in(travelIds)))
				.fetchOne(LOG.ID);

		if (checkLogId != null) {
			System.out.println(files.size());
			for (MultipartFile file : files) {
				System.out.println(file.getName());
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getSize());

				Metadata metadata = ImageMetadataReader
						.readMetadata(file.getInputStream());

				GpsDirectory gpsDirectory = metadata
						.getFirstDirectoryOfType(GpsDirectory.class);
				
				GeoLocation geoLocation = gpsDirectory.getGeoLocation(); 
			     if (geoLocation != null && !geoLocation.isZero()) { 
			        System.out.println(geoLocation);
			     } 
			     
					
			}
			//
			// BufferedImage bi = ImageIO.read(file.getInputStream());
			// System.out.println(bi.getHeight());
			// Builder<BufferedImage> thumbnailBuilder = Thumbnails.of(bi);
			//
			//
			// try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			// thumbnailBuilder.height(100).outputFormat("jpg").keepAspectRatio(true).toOutputStream(baos);
			//// ImageIO.write(resizedBi, "jpg", baos);
			//// baos.flush();
			// byte[] ba = baos.toByteArray();
			// System.out.println("ba");
			// System.out.println(ba.length);
			// }

			/*
			 * long id = this.dsl .insertInto(LOG, LOG.CREATED, LOG.LAT, LOG.LNG,
			 * LOG.LOCATION, LOG.REPORT, LOG.UPDATED, LOG.TRAVEL_ID) .values(
			 * LocalDateTime.ofInstant(Instant.ofEpochSecond(clientLog.getCreated()),
			 * ZoneOffset.UTC), clientLog.getLat(), clientLog.getLng(),
			 * clientLog.getLocation(), clientLog.getReport(), now,
			 * clientLog.getTravelId()) .returning(LOG.ID).fetchOne().getId();
			 * 

			 * 
			 * // thumbnail on server
			 * 
			 * InputStream in = new ByteArrayInputStream(ba); BufferedImage bi =
			 * ImageIO.read(in); BufferedImage resizedBi;
			 * 
			 * Builder<BufferedImage> thumbnailBuilder = Thumbnails.of(bi); if (hoehe !=
			 * null) { thumbnailBuilder.height(hoehe); } else if (breite != null) {
			 * thumbnailBuilder.width(breite); }
			 * 
			 * resizedBi = thumbnailBuilder.keepAspectRatio(true).asBufferedImage();
			 * 
			 * try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			 * ImageIO.write(resizedBi, "jpg", baos); baos.flush(); ba =
			 * baos.toByteArray(); return ResponseEntity.ok().contentLength(ba.length)
			 * .contentType(MediaType.parseMediaType(mediaType))
			 * .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)).body(ba); }
			 * 
			 */
		}
	}

}


/*
   source file
		File file = new File("movie.mp4");

		 check content type of the file
		String contentType = Files.probeContentType(file.toPath());

		 read data as byte[]
		byte[] data = Files.readAllBytes(file.toPath());

		 convert byte[] to base64(java7)
		String base64str = DatatypeConverter.printBase64Binary(data);

		 convert byte[] to base64(java8)
		 String base64str = Base64.getEncoder().encodeToString(data);

		 cretate "data URI"
		StringBuilder sb = new StringBuilder();
		sb.append("data:");
		sb.append(contentType);
		sb.append(";base64,");
		sb.append(base64str);

		System.out.println(sb.toString());
*/
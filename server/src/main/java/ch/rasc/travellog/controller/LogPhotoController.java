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

@RestController
@RequestMapping("/be")
public class LogPhotoController {

	private final DSLContext dsl;

	private MessageDigest messageDigest;

	public LogPhotoController(DSLContext dsl) {
		this.dsl = dsl;

		try {
			this.messageDigest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			Application.log.error("get MD5 instance");
		}
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
			 * byte[] thedigest = this.messageDigest
			 * .digest(text.getBytes(StandardCharsets.UTF_8)); String key = sourceLanguage
			 * + "-" + targetLanguage + "-" +
			 * Base64.getEncoder().encodeToString(thedigest);
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

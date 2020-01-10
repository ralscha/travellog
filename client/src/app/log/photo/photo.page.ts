import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-log-photo',
  templateUrl: './photo.page.html',
  styleUrls: ['./photo.page.scss'],
})
export class LogPhotoPage implements OnInit {
  @ViewChild('fileSelector') fileInput: ElementRef;

  private logIdString: string;

  constructor(private readonly httpClient: HttpClient,
              private readonly route: ActivatedRoute,) {
  }

  ngOnInit() {
    this.logIdString = this.route.snapshot.paramMap.get('id');
  }

  clickFileSelector() {
    this.fileInput.nativeElement.click();
  }

  async onFileChange(event) {
    const file = event.target.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append('logId', this.logIdString);
    formData.append('file', file, file.name);
    this.httpClient.post<void>(`/be/upload_photo`, formData)
      .subscribe(ok => {});


    const image = new Image();
    image.onload = () => {
      const th = this.createThumbnail(image);
      console.log(th);
    }
    image.src = URL.createObjectURL(file);

    event.target.value = null;
  }

  private createThumbnail(image: any) {
    const thumbnailMaxWidth = 100;
    const thumbnailMaxHeight = 60;

    // create an off-screen canvas
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');

    // Calculate the size of the thumbnail, to best fit within max/width (cropspadding)
    const thumbnailScale = (image.width / image.height) > (thumbnailMaxWidth / thumbnailMaxHeight) ?
      thumbnailMaxWidth / image.width :
      thumbnailMaxHeight / image.height;
    const thumbnailWidth = image.width * thumbnailScale;
    const thumbnailHeight = image.height * thumbnailScale;

    // set its dimension to target size
    canvas.width = thumbnailWidth;
    canvas.height = thumbnailHeight;

    // draw source image into the off-screen canvas:
    ctx.drawImage(image, 0, 0, thumbnailWidth, thumbnailHeight);

    // encode image to data-uri with base64 version of compressed image
    const thumbnail = new Image();
    thumbnail.src = canvas.toDataURL('image/jpeg', 70);
    return thumbnail;
  }
}

package kr.co.yapp.cafe.domain.file.storage;

import lombok.Value;

@Value(staticConstructor = "of")
public class StorageUploadResponseVo {
    String url;
    String name;
    String contentType;
    Long size;
}

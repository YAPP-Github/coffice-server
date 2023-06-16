package kr.co.yapp._22nd.coffice.domain.file.storage;

import lombok.Value;

@Value(staticConstructor = "of")
public class StorageUploadResponseVo {
    String url;
    String name;
    String contentType;
    Long size;
}

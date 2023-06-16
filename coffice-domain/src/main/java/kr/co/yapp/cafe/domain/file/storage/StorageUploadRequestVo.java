package kr.co.yapp.cafe.domain.file.storage;

import lombok.Value;

@Value
public class StorageUploadRequestVo {
    String contentType;
    Long size;
}

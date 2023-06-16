package kr.co.yapp._22nd.coffice.domain.file.storage;

import lombok.Value;

@Value
public class StorageDownloadRequestVo {
    Long fileId;
    String filename;
}

package kr.co.yapp.cafe.domain.file.storage;

import lombok.Value;

@Value
public class StorageDownloadRequestVo {
    Long fileId;
    String filename;
}

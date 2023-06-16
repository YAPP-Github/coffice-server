package kr.co.yapp.cafe.domain.file.storage;

import java.io.InputStream;
import java.io.OutputStream;

public interface StorageService {
    StorageUploadResponseVo upload(InputStream inputStream, StorageUploadRequestVo storageUploadRequestVo);

    void download(OutputStream outputStream, StorageDownloadRequestVo storageDownloadRequestVo);
}



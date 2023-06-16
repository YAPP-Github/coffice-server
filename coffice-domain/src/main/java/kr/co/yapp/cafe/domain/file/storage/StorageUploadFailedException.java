package kr.co.yapp.cafe.domain.file.storage;

public class StorageUploadFailedException extends RuntimeException {
    public StorageUploadFailedException(Throwable cause) {
        super("Failed to upload file to S3", cause);
    }
}

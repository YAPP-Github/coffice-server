package kr.co.yapp._22nd.coffice.domain.file.storage;

public class StorageUploadFailedException extends RuntimeException {
    public StorageUploadFailedException(Throwable cause) {
        super("Failed to upload file to S3", cause);
    }
}

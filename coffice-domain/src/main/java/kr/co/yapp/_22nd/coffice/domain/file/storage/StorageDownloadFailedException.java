package kr.co.yapp._22nd.coffice.domain.file.storage;

public class StorageDownloadFailedException extends RuntimeException {
    public StorageDownloadFailedException(Throwable cause) {
        super("Failed to download file from S3", cause);
    }
}

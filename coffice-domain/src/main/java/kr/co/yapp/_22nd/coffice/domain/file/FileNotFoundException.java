package kr.co.yapp._22nd.coffice.domain.file;

public class FileNotFoundException extends RuntimeException {
    private final Long fileId;

    public FileNotFoundException(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public String getMessage() {
        return "File not found. fileId: " + fileId;
    }
}

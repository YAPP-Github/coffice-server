package kr.co.yapp._22nd.coffice.domain.file;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException(Long fileId) {
        super("File not found. fileId: " + fileId);
    }
}

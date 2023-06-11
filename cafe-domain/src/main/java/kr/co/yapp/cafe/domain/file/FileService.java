package kr.co.yapp.cafe.domain.file;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FileService {
    File create(FileCreateVo fileCreateVo);

    void delete(Long fileId);

    Page<File> findAll(Pageable pageable);

    Optional<File> findById(Long fileId);
}

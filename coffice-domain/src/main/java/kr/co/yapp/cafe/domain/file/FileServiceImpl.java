package kr.co.yapp.cafe.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public File create(FileCreateVo fileCreateVo) {
        File file = File.from(fileCreateVo);
        return fileRepository.save(file);
    }

    @Override
    @Transactional
    public void delete(Long fileId) {
        fileRepository.findById(fileId).ifPresent(File::delete);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    @Override
    public Optional<File> findById(Long fileId) {
        return fileRepository.findById(fileId);
    }
}

package kr.co.yapp._22nd.coffice.application.report;

import kr.co.yapp._22nd.coffice.domain.file.storage.StorageUploadRequestVo;
import kr.co.yapp._22nd.coffice.domain.file.storage.StorageUploadResponseVo;
import kr.co.yapp._22nd.coffice.infrastructure.aws.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportImageUploadService {
    @Autowired
    private AmazonS3Service amazonS3Service;

    public List<String> uploadReportImage(
            List<MultipartFile> files
    ) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            StorageUploadRequestVo request = new StorageUploadRequestVo(
                    file.getContentType(),
                    file.getSize()
            );
            StorageUploadResponseVo response = amazonS3Service.upload(file.getInputStream(), request);
            urls.add(response.getUrl());
        }
        return urls;
    }
}

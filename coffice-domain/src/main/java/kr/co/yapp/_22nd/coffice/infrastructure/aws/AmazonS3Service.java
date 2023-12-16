package kr.co.yapp._22nd.coffice.infrastructure.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import kr.co.yapp._22nd.coffice.domain.file.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonS3Service implements StorageService {

    private final AmazonS3 amazonS3;

    @Value("${cafe.aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public StorageUploadResponseVo upload(
            InputStream inputStream,
            StorageUploadRequestVo storageUploadRequestVo
    ) {
        String filename = UUID.randomUUID().toString();
        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(storageUploadRequestVo.getContentType());
            objectMetadata.setContentLength(inputStream.available());
            amazonS3.putObject(new PutObjectRequest(bucketName, filename, inputStream, objectMetadata));
        } catch (Exception e) {
            throw new StorageUploadFailedException(e);
        }
        return StorageUploadResponseVo.of(
                "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + filename,
                filename,
                storageUploadRequestVo.getContentType(),
                storageUploadRequestVo.getSize()
        );
    }

    @Override
    public void download(
            OutputStream outputStream,
            StorageDownloadRequestVo storageDownloadRequestVo
    ) {
        try {
            S3Object object = amazonS3.getObject(
                    new GetObjectRequest(
                            bucketName,
                            storageDownloadRequestVo.getFilename()
                    )
            );
            object.getObjectContent().transferTo(outputStream);
        } catch (Exception e) {
            throw new StorageDownloadFailedException(e);
        }
    }
}

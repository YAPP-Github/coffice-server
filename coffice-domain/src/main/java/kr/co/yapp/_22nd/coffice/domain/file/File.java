package kr.co.yapp._22nd.coffice.domain.file;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String url;
    private String name;
    private String contentType;
    private Long size;
    private Boolean deleted;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public static File from(FileCreateVo fileCreateVo) {
        File file = new File();
        file.url = fileCreateVo.getUrl();
        file.name = fileCreateVo.getName();
        file.contentType = fileCreateVo.getContentType();
        file.size = fileCreateVo.getSize();
        return file;
    }

    public void delete() {
        deleted = true;
    }
}

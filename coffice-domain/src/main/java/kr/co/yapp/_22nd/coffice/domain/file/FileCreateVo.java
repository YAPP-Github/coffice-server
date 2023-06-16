package kr.co.yapp._22nd.coffice.domain.file;


import lombok.Value;

@Value
public class FileCreateVo {
    String name;

    String url;
    String contentType;
    Long size;
}

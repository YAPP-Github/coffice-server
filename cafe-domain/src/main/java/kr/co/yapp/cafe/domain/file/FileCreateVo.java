package kr.co.yapp.cafe.domain.file;


import lombok.Value;

@Value
public class FileCreateVo {
    String name;

    String url;
    String contentType;
    Long size;
}

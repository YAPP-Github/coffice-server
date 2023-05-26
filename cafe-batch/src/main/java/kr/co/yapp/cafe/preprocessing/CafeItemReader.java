package kr.co.yapp.cafe.preprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CafeItemReader extends JsonItemReader<Object> {
    public CafeItemReader(String filePath, String dataJsonPath) throws Exception {
        super();
        setJsonObjectReader(new JacksonJsonObjectReader<>(Object.class));
        setResource(new InputStreamResource(getDataInputStream(filePath, dataJsonPath)));
        doOpen();
    }

    private static InputStream getDataInputStream(String filePath, String dataJsonPath) {
        InputStream inputStream;
        try {
            File file = ResourceUtils.getFile(filePath);
            DocumentContext jsonContext = JsonPath.parse(file);
            Object content = jsonContext.read(dataJsonPath);
            inputStream = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputStream;
    }
}

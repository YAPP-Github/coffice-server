package kr.co.yapp.cafe.preprocessing;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import kr.co.yapp.cafe.domain.place.Address;
import kr.co.yapp.cafe.domain.place.Coordinates;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class CafeItemProcessor implements ItemProcessor<Object, ScrappingResultCreateVo> {
    @Value("#{jobParameters['namePrefix'] ?: ''}")
    private String namePrefix;

    @Value("#{jobParameters['namePrefixAfterSpace'] ?: true}")
    private Boolean namePrefixAfterSpace;

    @Value("#{jobParameters['namePostfix'] ?: ''}")
    private String namePostfix;

    @Value("#{jobParameters['nameJsonPath']}")
    private String nameJsonPath;

    @Value("#{jobParameters['addressJsonPath']}")
    private String addressJsonPath;

    @Value("#{jobParameters['contactNumberJsonPath']}")
    private String contactNumberJsonPath;

    @Value("#{jobParameters['latitudeJsonPath'] ?: ''}")
    private String latitudeJsonPath;

    @Value("#{jobParameters['longitudeJsonPath'] ?: ''}")
    private String longitudeJsonPath;

    @Value("#{jobParameters['imageUrlJsonPath']}")
    private String imageUrlJsonPath;

    @Value("#{jobParameters['imageUrlPrefix'] ?: ''}")
    private String imageUrlPrefix;

    @Override
    public ScrappingResultCreateVo process(Object item) throws Exception {
        ReadContext jsonContext = JsonPath.parse(item);
        Object name = readJsonPath(jsonContext, nameJsonPath);
        Object address = readJsonPath(jsonContext, addressJsonPath);
        Object contactNumber = readJsonPath(jsonContext, contactNumberJsonPath);
        Object latitude = readJsonPath(jsonContext, latitudeJsonPath);
        Object longitude = readJsonPath(jsonContext, longitudeJsonPath);
        Object imageUrl = readJsonPath(jsonContext, imageUrlJsonPath);
        return ScrappingResultCreateVo.of(
                name instanceof String && !"null".equalsIgnoreCase((String) name)
                        ? String.join("", namePrefix, namePrefixAfterSpace ? " " : "", ((String) name).trim(), namePostfix).trim()
                        : null,
                // TODO: 도로명주소, 지번주소 구분
                address instanceof String && !"null".equalsIgnoreCase((String) address)
                        ? Address.builder().streetAddress((String) address).build()
                        : null,
                latitude instanceof Double && longitude instanceof Double
                        ? Coordinates.of(((Double) latitude), (Double) longitude)
                        : latitude instanceof String && longitude instanceof String
                        ? Coordinates.of((Double.valueOf((String) latitude)), Double.valueOf((String) longitude))
                        : null,
                contactNumber instanceof List && !((List<?>) contactNumber).isEmpty() && ((List<?>) contactNumber).get(0) instanceof String
                        ? (List<String>) contactNumber
                        : contactNumber instanceof String && !"null".equalsIgnoreCase((String) contactNumber)
                        ? Collections.singletonList((String) contactNumber)
                        : Collections.emptyList(),
                imageUrl instanceof List && !((List<?>) imageUrl).isEmpty() && ((List<?>) imageUrl).get(0) instanceof String
                        ? (List<String>) imageUrl
                        : imageUrl instanceof String && !"null".equalsIgnoreCase((String) imageUrl)
                        ? Collections.singletonList(String.join("", imageUrlPrefix, (String) imageUrl).trim())
                        : Collections.emptyList()
        );
    }

    private Object readJsonPath(ReadContext jsonContext, String jsonPath) {
        if (!StringUtils.hasText(jsonPath)) {
            return null;
        }
        try {
            return jsonContext.read(jsonPath);
        } catch (PathNotFoundException e) {
            return null;
        }
    }
}

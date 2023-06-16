package kr.co.yapp._22nd.coffice.preprocessing;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import kr.co.yapp._22nd.coffice.domain.place.Address;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResultCreateVo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class CafeItemProcessor implements ItemProcessor<Object, ScrappingResultCreateVo> {
    @Value("#{jobParameters['namePrefix'] ?: ''}")
    private String namePrefix;

    @Value("#{jobParameters['namePrefixAfterSpace'] ?: true}")
    private boolean namePrefixAfterSpace;

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
                resolveName(name),
                resolveAddress(address),
                resolveCoordinates(latitude, longitude),
                resolveContactNumbers(contactNumber),
                resolveImageUrls(imageUrl)
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

    private String resolveName(Object name) {
        if (name instanceof String && !"null".equalsIgnoreCase((String) name)) {
            return String.join("", namePrefix, namePrefixAfterSpace ? " " : "", ((String) name).trim(), namePostfix).trim();
        }
        return null;
    }

    // TODO: 도로명주소, 지번주소 구분
    private Address resolveAddress(Object address) {
        if (address instanceof String && !"null".equalsIgnoreCase((String) address)) {
            return Address.builder().streetAddress((String) address).build();
        }
        return null;
    }

    private Coordinates resolveCoordinates(Object latitude, Object longitude) {
        if (latitude instanceof Double && longitude instanceof Double) {
            return Coordinates.of(((Double) latitude), (Double) longitude);
        }
        if (latitude instanceof String && longitude instanceof String) {
            return Coordinates.of((Double.valueOf((String) latitude)), Double.valueOf((String) longitude));
        }
        return null;
    }

    private List<String> resolveContactNumbers(Object contactNumber) {
        if (contactNumber instanceof List && !((List<?>) contactNumber).isEmpty() && ((List<?>) contactNumber).get(0) instanceof String) {
            return (List<String>) contactNumber;
        }
        if (contactNumber instanceof String && !"null".equalsIgnoreCase((String) contactNumber)) {
            return Collections.singletonList((String) contactNumber);
        }
        return Collections.emptyList();
    }

    private List<String> resolveImageUrls(Object imageUrl) {
        if (imageUrl instanceof List && !((List<?>) imageUrl).isEmpty() && ((List<?>) imageUrl).get(0) instanceof String) {
            return (List<String>) imageUrl;
        }
        if (imageUrl instanceof String && !"null".equalsIgnoreCase((String) imageUrl)) {
            return Collections.singletonList(String.join("", imageUrlPrefix, (String) imageUrl).trim());
        }
        return Collections.emptyList();
    }
}

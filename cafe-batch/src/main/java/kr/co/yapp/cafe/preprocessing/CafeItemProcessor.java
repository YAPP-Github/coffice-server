package kr.co.yapp.cafe.preprocessing;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import kr.co.yapp.cafe.domain.place.Address;
import kr.co.yapp.cafe.domain.place.Coordinates;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;

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

    @Value("#{jobParameters['latitudeJsonPath']}")
    private String latitudeJsonPath;

    @Value("#{jobParameters['longitudeJsonPath']}")
    private String longitudeJsonPath;

    @Value("#{jobParameters['imageUrlJsonPath']}")
    private String imageUrlJsonPath;

    @Value("#{jobParameters['imageUrlPrefix'] ?: ''}")
    private String imageUrlPrefix;

    @Override
    public ScrappingResultCreateVo process(Object item) throws Exception {
        DocumentContext jsonContext = JsonPath.parse(item);
        Object name;
        try {
            name = jsonContext.read(nameJsonPath);
        } catch (PathNotFoundException e) {
            name = null;
        }
        Object address;
        try {
            address = jsonContext.read(addressJsonPath);
        } catch (PathNotFoundException e) {
            address = null;
        }
        Object contactNumber;
        try {
            contactNumber = jsonContext.read(contactNumberJsonPath);
        } catch (PathNotFoundException e) {
            contactNumber = null;
        }
        Object latitude;
        try {
            latitude = jsonContext.read(latitudeJsonPath);
        } catch (PathNotFoundException e) {
            latitude = null;
        }
        Object longitude;
        try {
            longitude = jsonContext.read(longitudeJsonPath);
        } catch (PathNotFoundException e) {
            longitude = null;
        }
        Object imageUrl;
        try {
            imageUrl = jsonContext.read(imageUrlJsonPath);
        } catch (PathNotFoundException e) {
            imageUrl = null;
        }
        return ScrappingResultCreateVo.of(
                name instanceof String && !"null".equalsIgnoreCase((String) name)
                        ? String.join("", namePrefix, namePrefixAfterSpace ? " " : "", ((String) name).trim(), namePostfix).trim()
                        : null,
                address instanceof String && !"null".equalsIgnoreCase((String) address)
                        ? Address.builder().streetAddress((String) address).build()
                        : null,
                latitude instanceof Double && longitude instanceof Double
                        ? Coordinates.of(((Double) latitude), (Double) longitude)
                        : latitude instanceof String && longitude instanceof String
                        ? Coordinates.of((Double.valueOf((String) latitude)), Double.valueOf((String) longitude))
                        : null,
                contactNumber instanceof String && !"null".equalsIgnoreCase((String) contactNumber)
                        ? Collections.singletonList((String) contactNumber)
                        : Collections.emptyList(),
                imageUrl instanceof String && !"null".equalsIgnoreCase((String) imageUrl)
                        ? Collections.singletonList(String.join("", imageUrlPrefix, (String) imageUrl).trim())
                        : Collections.emptyList()
        );
    }
}

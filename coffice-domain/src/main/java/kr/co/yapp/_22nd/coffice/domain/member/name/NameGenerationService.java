package kr.co.yapp._22nd.coffice.domain.member.name;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NameGenerationService {
    private static final int RANDOM_NUMBER_BOUNDARY = 1000000;
    private final DeterminerRepository determinerRepository;
    private final NounRepository nounRepository;

    public String generateRandonName() {
        SecureRandom random = new SecureRandom();
        List<Determiner> determiners = determinerRepository.findAll();
        List<Noun> nouns = nounRepository.findAll();
        String determiner = getRandomElement(random, determiners).getValue();
        String noun = getRandomElement(random, nouns).getValue();
        return determiner + " " + noun + random.nextInt(RANDOM_NUMBER_BOUNDARY);
    }

    private <T> T getRandomElement(SecureRandom random, List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}

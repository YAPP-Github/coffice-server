package kr.co.yapp._22nd.coffice.domain.member.name;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NameGenerationService {
    private static final int RANDOM_NUMBER_BOUNDARY = 100;
    private final ModifierRepository modifierRepository;
    private final SubjectRepository subjectRepository;

    public String generateRandomName() {
        SecureRandom random = new SecureRandom();
        List<Modifier> modifiers = modifierRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();
        String determiner = getRandomElement(random, modifiers).getValue();
        String noun = getRandomElement(random, subjects).getValue();
        return determiner + " " + noun + " " + random.nextInt(RANDOM_NUMBER_BOUNDARY);
    }

    private <T> T getRandomElement(SecureRandom random, List<T> list) {
        return Optional.ofNullable(list)
                .filter(it -> !it.isEmpty())
                .map(it -> it.get(random.nextInt(list.size())))
                .orElseThrow(ListElementNotFoundException::new);
    }
}

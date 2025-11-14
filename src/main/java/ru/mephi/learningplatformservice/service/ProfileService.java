package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.model.Profile;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile createProfileForUser(User user) {
        Profile profile = new Profile();
        profile.setUser(user);

        return profileRepository.save(profile);
    }
}

package com.pandacreep.instagram.model.user;

import com.pandacreep.instagram.exception.InstagramUserExistException;
import com.pandacreep.instagram.util.Constants;
import com.pandacreep.instagram.util.Image;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User getByEmailOrNull(String email) {
        var userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public User save(UserDtoRegister form) throws InstagramUserExistException {
        if (isEmailExist(form.getEmail())) {
            String message = "Email " + form.getEmail() + " already exist";
            throw new InstagramUserExistException(message);
        }
        var file = form.getAvatar();
        String imageString;
        Image image = Image.getImage(file);
        if (image == null) {
            imageString = Constants.NO_AVATAR;
        } else {
            imageString = Base64.getEncoder().encodeToString(image.getImage().getData());
        }
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(form.getEmail())
                .name(form.getName())
                .password(encoder.encode(form.getPassword()))
                .avatar(imageString)
                .description(form.getDescription())
                .phoneNumber(form.getPhoneNumber())
                .gender(form.getGender())
                .build();
        return  userRepository.save(user);
    }

    private boolean isEmailExist(String email) {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    public List<UserDto> search(String email,
                                String name,
                                String description,
                                String phoneNumber,
                                Principal principal) {
        List<User> usersAll = userRepository.findAll();
        var usersFiltered = usersAll.stream()
                .filter(u -> u.getEmail().toLowerCase().contains(email.toLowerCase()))
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(u -> u.getDescription().toLowerCase().contains(description.toLowerCase()))
                .filter(u -> u.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()))
                .map(UserDto::from)
                .collect(Collectors.toList());
        checkIfSubscriptionAvailable(principal, usersFiltered);
        return usersFiltered;
    }

    private void checkIfSubscriptionAvailable(Principal principal, List<UserDto> usersFiltered) {
        var user = userRepository.findByEmail(principal.getName()).get();
        List<User> subscriptions = user.getSubscriptions();
        for (int i = 0; i < usersFiltered.size(); i++) {
            var userFilteredEmail = usersFiltered.get(i).getEmail();
            if (user.getEmail().equalsIgnoreCase(userFilteredEmail)) {
                usersFiltered.get(i).setSubscriptionAvailable(false);
            }
            for (int j = 0; j < subscriptions.size(); j++) {
                if (subscriptions.get(j).getEmail().equalsIgnoreCase(userFilteredEmail)) {
                    usersFiltered.get(i).setSubscriptionAvailable(false);
                }

            }
        }
    }

    public void subscribe(String whoEmail, String toWhomEmail) {
        addSubscriberInfo(whoEmail, toWhomEmail);
        addSubscriptionInfo(whoEmail, toWhomEmail);
    }

    public void addSubscriberInfo(String whoEmail, String toWhomEmail) {
        var userSubscriber = userRepository.findByEmail(whoEmail).get();
        var userSubscription = userRepository.findByEmail(toWhomEmail).get();

        List<User> subscriptions = userSubscriber.getSubscriptions();
        subscriptions.add(userSubscription);
        userSubscriber.setSubscriptions(subscriptions);
        userRepository.save(userSubscriber);
    }

    public void addSubscriptionInfo(String whoEmail, String toWhomEmail) {
        var userSubscriber = userRepository.findByEmail(whoEmail).get();
        var userSubscription = userRepository.findByEmail(toWhomEmail).get();

        List<User> subscribers = userSubscription.getSubscribers();
        subscribers.add(userSubscriber);
        userSubscription.setSubscribers(subscribers);
        userRepository.save(userSubscription);
    }
}

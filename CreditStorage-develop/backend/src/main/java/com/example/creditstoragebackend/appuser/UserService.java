package com.example.creditstoragebackend.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @ResponseBody
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same


            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();


        return token;
    }

    public int enableAppUser(String username) {
        return userRepository.enableAppUser(username);
    }


    //below are User business logic methods, above are security based ones

    public Optional<User> getUserById(String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findById(id);
    }

    public User getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

    //TODO: Add updateCreditScore method, need to create formula that also updates storageCapacity
//    public void updateCreditScore();

    public void updateUserSeedCapacity(double seedCapacity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        user.setSeedCapacity(seedCapacity);
        //TODO: Add update to creditScore
        userRepository.save(user); //simply updates that data entry by matching the id's
    }

    public void updateUserStorageCapacity(double capacity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        double remainingCapacity = user.getStorageCapacity() - capacity;

        if (remainingCapacity < 0) {
            throw new IllegalStateException("file larger than remaining storage capacity");
        }

        user.setStorageCapacity(remainingCapacity);
        userRepository.save(user);
    }







}

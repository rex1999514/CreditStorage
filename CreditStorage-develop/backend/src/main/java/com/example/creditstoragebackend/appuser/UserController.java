package com.example.creditstoragebackend.appuser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//  USELESS for now
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<User> getUserInfo(@PathVariable("id") String id){
//        User user = userService.getUserById(id).orElseThrow(() -> new NullPointerException("user not found"));
//
//        return ResponseEntity.ok().body(user);
//
//    }

    @GetMapping
    public User getUserInfo() {
        return userService.getUserInfo();
    }

    //when user agrees to increase seeding capacity
    //must affect seedCapacity, creditScore ----> indirectly increasing storageCapacity
    @PutMapping(path = "/update-seed-capacity")
    public void testUpdateCredit(@RequestParam(name = "capacity") Double seedCapacity) {
        userService.updateUserSeedCapacity(seedCapacity);
    }


}

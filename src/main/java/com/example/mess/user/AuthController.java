package com.example.mess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


//@Controller
//@RequestMapping("api")
//public class AuthController {
//    @GetMapping("login")
//    public String login() {
//        return "hi";
//    }



//}

@Controller
public class AuthController {


    private final UserRepository userRepository;
    @Autowired


    public AuthController(UserRepository userRepository) {

        this.userRepository = userRepository;

    }
    @GetMapping("/login")
    public String home() {
        return "login";
    }




    @PostMapping("/homepage")
    public ModelAndView check(@RequestParam String rollNo, @RequestParam String password, Model model){

        UserEntity user= userRepository.findByRollNo(rollNo);

        if(!user.getPassword().equals(password))
            return new ModelAndView("login");


        if(user.getMess().equals("North")) {

            model.addAttribute("color", "north");
        }
        else
            model.addAttribute("color","south");

        model.addAttribute("mess",user.getMess());
        model.addAttribute("rollNumber",rollNo);
        model.addAttribute("bf",user.getBfCount());
        model.addAttribute("lunch",user.getLunchCount());
        model.addAttribute("snacks",user.getSnacksCount());
        model.addAttribute("dinner",user.getDinnerCount());
        return new ModelAndView("homepage");
    }

    @PostMapping("/updateMealCount")
    public ModelAndView updateMealCount(@RequestParam String rollNo, @RequestParam String mealType, @RequestParam String color, Model model){

        UserEntity user= userRepository.findByRollNo(rollNo);

        if(user==null)
            return new ModelAndView("login");

        int currentCount;
        switch (mealType.toLowerCase()) {
            case "breakfast":
                currentCount = user.getBfCount();
                break;
            case "lunch":
                currentCount = user.getLunchCount();
                break;
            case "snacks":
                currentCount = user.getSnacksCount();
                break;
            case "dinner":
                currentCount = user.getDinnerCount();
                break;
            default:
                throw new IllegalArgumentException("Invalid meal type: " + mealType);
        }

        if (currentCount >= 1) {
            model.addAttribute("errorMessage", "You have already eaten " + mealType + " today!");
            model.addAttribute("mess", user.getMess());
            model.addAttribute("bf",user.getBfCount());
            model.addAttribute("lunch",user.getLunchCount());
            model.addAttribute("snacks",user.getSnacksCount());
            model.addAttribute("dinner",user.getDinnerCount());
            model.addAttribute("color",color);
            model.addAttribute("rollNumber",rollNo);
            return new ModelAndView("homepage");
        }

        switch (mealType.toLowerCase()) {
            case "breakfast":
                user.setBfCount(user.getBfCount() + 1);
                break;
            case "lunch":
                user.setLunchCount(user.getLunchCount() + 1);
                break;
            case "snacks":
                user.setSnacksCount(user.getSnacksCount() + 1);
                break;
            case "dinner":
                user.setDinnerCount(user.getDinnerCount() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid meal type: " + mealType);
        }

        userRepository.save(user);

        model.addAttribute("rollNumber",rollNo);
        model.addAttribute("mess", user.getMess());
        model.addAttribute("bf",user.getBfCount());
        model.addAttribute("lunch",user.getLunchCount());
        model.addAttribute("snacks",user.getSnacksCount());
        model.addAttribute("dinner",user.getDinnerCount());
        model.addAttribute("color",color);
        return new ModelAndView("homepage");
    }
}
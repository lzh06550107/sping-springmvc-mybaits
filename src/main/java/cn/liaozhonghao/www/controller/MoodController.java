package cn.liaozhonghao.www.controller;

import cn.liaozhonghao.www.dto.MoodDTO;
import cn.liaozhonghao.www.service.MoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/mood")
public class MoodController {

    @Resource
    private MoodService moodService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        return "mood";
    }

    @GetMapping("/{moodId}/praise")
    public String praise(Model model, @PathVariable("moodId") String moodId, @RequestParam("userId") String userId) {
        boolean isPraise = moodService.praiseMood(userId, moodId);

        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

    @GetMapping("/{moodId}/praiseForRedis")
    public String praiseForRedis(Model model, @PathVariable("moodId") String moodId, @RequestParam("userId") String userId) {
        Random random = new Random();
        userId = random.nextInt(100) + "";

        boolean isPraise = moodService.praiseMoodForRedis(userId,moodId);
        List<MoodDTO> moodDTOList = moodService.findAllForRedis();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

    @GetMapping("/{moodId}/praiseForQueue")
    public String praiseForQueue(Model model, @PathVariable("moodId") String moodId, @RequestParam("userId") String userId) {
        Random random = new Random();
        userId = random.nextInt(100) + "";

        boolean isPraise = moodService.praiseMoodForQueue(userId,moodId);
        List<MoodDTO> moodDTOList = moodService.findAllForRedis();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }


}

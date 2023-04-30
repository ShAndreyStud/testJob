package work.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String[] test() throws Exception {
       RunAfterStartup temp = new RunAfterStartup();
       String[] text = temp.runAfterStartup();
       return text;
    }
    @GetMapping("/findAge")
    public Integer findAge(@RequestParam("name") String name) throws Exception {
        AllocateAge alAge = new AllocateAge();
        RunAfterStartup temp = new RunAfterStartup();
        String[] text = temp.runAfterStartup();
        int age = alAge.allocateAge(text, name);
    return age;
    }
    @GetMapping("/maxAge")
    public Integer maxAge() throws Exception {
        RunAfterStartup temp = new RunAfterStartup();
        String[] text = temp.runAfterStartup();
        int maxAge = 0, tempAge = 0;
        int i = 0;
        int find = 0;
        for (String str: text) {
            List<Integer> numbers = Pattern.compile("-?\\d+")
                    .matcher(str)
                    .results()
                    .map(MatchResult::group)
                    .map(x -> Integer.parseInt(x))
                    .collect(Collectors.toList());
            tempAge = numbers.get(0);
            if (tempAge > maxAge){
                maxAge = tempAge;
                find = i;
            }
            i++;
        }
        return find;
    }
}


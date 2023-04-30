package work.test;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AllocateAge {
    public int allocateAge(String[] text, String name) throws IOException {
        int age = 0;
        int index = getSearchArray(text, name);
        if(index==-1){
            String url = "https://api.agify.io?name=";
            RestTemplate restTemplate = new RestTemplate();
            var nameage = restTemplate.getForObject(url+name, Object.class);
            String nameageString = (nameage).toString();
            System.out.println(nameageString);
            List<Integer> ageFrom = Pattern.compile("-?\\d+")
                    .matcher(nameageString)
                    .results()
                    .map(MatchResult::group)
                    .map(x -> Integer.parseInt(x))
                    .collect(Collectors.toList());
            age = ageFrom.get(0);

            String newline = name+"_"+age;
            try {
                PrintWriter prWr = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/file/name_age.txt", true)));
                prWr .println(newline);
                prWr .close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            List<Integer> numbers = Pattern.compile("-?\\d+")
                    .matcher(text[index])
                    .results()
                    .map(MatchResult::group)
                    .map(x -> Integer.parseInt(x))
                    .collect(Collectors.toList());
            age = numbers.get(0);
        }
        return age;
    }

    public static int getSearchArray(String[] text, String find) {
        int i = 0;
        int result = -1;
        for (String temp : text) {
            if (temp.contains(find)) {
                 result = i;
            }
            i++;
        }
        return result;
    }
}

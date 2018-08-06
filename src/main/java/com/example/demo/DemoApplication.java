package com.example.demo;

import org.hibernate.validator.constraints.EAN;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/")
    public Object index() {

        List<String> neighborsInput = new ArrayList<>();
        neighborsInput.add("1,John");
        neighborsInput.add("2,Frank");
        neighborsInput.add("3,Nolan");
        neighborsInput.add("4,Monica");
        neighborsInput.add("6,Vera");

        List<String> relationMapping = new ArrayList<>();
        relationMapping.add("1,2");
        relationMapping.add("1,3");
        relationMapping.add("2,4");

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for(int i = 0; i < neighborsInput.size(); i++){

            String[] buf = StringUtils.split(neighborsInput.get(i), ",");

            result.put(buf[0], new ArrayList<>());
        }

        for(int i = 0; i < relationMapping.size(); i++){
            String[] buf = StringUtils.split(relationMapping.get(i), ",");

            List list = result.get(buf[0]);
            if(!list.contains(buf[1])){
                list.add(buf[1]);
            }

            list = result.get(buf[1]);
            if(!list.contains(buf[0])){
                list.add(buf[0]);
            }
        }

        StringBuilder output = new StringBuilder();
        for(String key : result.keySet()){
            if(result.get(key).size() > 0) {
                output.append(key + ": " + String.join(", ", result.get(key)) + " | ");
            }else {
                output.append(key + ": None | ");
            }
        }

        return output;
    }


}

	/*
    From Huawei Li to Everyone: (11:06)
 You are creating a local networking webapp for your neighborhood. You have two data sets to work with.   The first data set is the list of people who live in the neighborhood. The second data set is all the pairs of neighbors who are already friends.  The goal is to write functionality that will be able determine, based on the provided input data, who is friends with

whom. String[] neighborsInput = {       "1,John",       "2,Frank", 
"3,Nolan",       "4,Monica", 
"6,Vera"     };   
String[] friendshipsInput = {       "1,2",       "1,3",       "2,4"     }; 
From Huawei Li to Everyone: (11:06)
 A valid output could look like this:  
# 1: 2, 3     # 2: 1, 4 
# 3: 1     # 4: 2     # 6: None 
	 */



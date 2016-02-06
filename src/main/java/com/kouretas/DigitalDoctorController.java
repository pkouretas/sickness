package com.kouretas;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DigitalDoctorController {

    @RequestMapping("/doctor")
    public String[] index(@RequestParam(value="desc", required=false) String description) {
    	
    	if (description == null){
    		return (new String[] {"Input missing"});
    	}
    	
    	description = description.replaceAll("^\'|\'$", "");  // Remove enclosing single quotes
    	description = description.replaceAll("^\"|\"$", "");  // Remove enclosing double quotes 
    	
    	SicknessExtractor se = new SicknessExtractor();
		List<String> results = se.extractIllnesses(description);
		
		System.out.println(results);
        return results.toArray(new String[results.size()]);
    }

}

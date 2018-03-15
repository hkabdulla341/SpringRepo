package testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testBean
{
    @RequestMapping(value = "/test")
    public String returnStr()
    {
	return "hello world";
    }
}

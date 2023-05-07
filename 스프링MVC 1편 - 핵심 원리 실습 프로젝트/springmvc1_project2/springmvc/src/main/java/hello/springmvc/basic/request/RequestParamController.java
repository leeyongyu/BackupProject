package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username,age);

        response.getWriter().write("ok");
    }
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge)throws IOException {

        log.info("memberName={}, memberAge={}",memberName,memberAge);
        return "ok";
    }
    /**
     * @RequestParam의 값과 변수값이 같으면 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age)throws IOException {

        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }

    /**
     * String, int 등의 단순 타입이면 @RequestParam도 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age)throws IOException {

        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }
    /**
     * @RequestParam의 파라미터가 필수인 경우 required 옵션 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = true) int age) throws IOException {

        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }

    /**
     * @RequestParam의 파라미터가 필수인 경우 required 옵션 사용시 값이 없을 경우
     * dafaultValue 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = true, defaultValue = "-1") int age) throws IOException {

        log.info("memberName={}, memberAge={}",username,age);
        return "ok";
    }

    /**
     * @RequestParam의 파라미터가 필수인 경우 required 옵션 사용시 값이 없을 경우
     * dafaultValue 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) throws IOException {


        log.info("memberName={}, memberAge={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
    /**
     * @ModelAttribute 사용 전,
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1-before")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        log.info("helloData={}",helloData);

        return "OK";
    }
    /**
     * @ModelAttribute 사용 후
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        log.info("helloData={}",helloData);

        return "OK";
    }
    /**
     * @ModelAttribute 생략
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        log.info("helloData={}",helloData);

        return "OK";
    }
}

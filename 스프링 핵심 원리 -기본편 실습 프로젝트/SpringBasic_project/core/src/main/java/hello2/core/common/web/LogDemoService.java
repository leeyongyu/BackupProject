package hello2.core.common.web;

import hello2.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String id) {
        //MyLogger myLogger =  myLoggerProvider.getObject();
        myLogger.log("service id = "+ id);
    }
}

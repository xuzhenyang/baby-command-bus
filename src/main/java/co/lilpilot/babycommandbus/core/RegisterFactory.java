package co.lilpilot.babycommandbus.core;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterFactory {
    @Autowired
    @Qualifier("commandHandlerRegister")
    private IRegister commandHandlerRegister;
    @Autowired
    @Qualifier("preCommandInterceptorRegister")
    private IRegister preCommandInterceptorRegister;
    @Autowired
    @Qualifier("postCommandInterceptorRegister")
    private IRegister postCommandInterceptorRegister;

    public List<IRegister> getRegister(Class<?> targetClass) {
        List<IRegister> registers = Lists.newArrayList();
        if (ICommandHandler.class.isAssignableFrom(targetClass)) {
            registers.add(commandHandlerRegister);
        }
        if (targetClass.isAnnotationPresent(PreCommandInterceptor.class)) {
            registers.add(preCommandInterceptorRegister);
        }
        if (targetClass.isAnnotationPresent(PostCommandInterceptor.class)) {
            registers.add(postCommandInterceptorRegister);
        }
        return registers;
    }
}

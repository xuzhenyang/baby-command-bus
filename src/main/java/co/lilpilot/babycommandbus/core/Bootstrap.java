package co.lilpilot.babycommandbus.core;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Bootstrap {
    @Getter
    @Setter
    private List<String> packages;
    @Autowired
    private RegisterFactory registerFactory;

    public void init() {
        log.info("baby-command-bus init...");
        Set<Class<?>> classSet = new HashSet<>(scanClasses(packages));
        registerClasses(classSet);
    }

    private List<Class<?>> scanClasses(List<String> packages) {
        if (CollectionUtils.isEmpty(packages)) {
            throw new RuntimeException("package is empty");
        }
        List<Class<?>> classes = Lists.newArrayList();
        for (String aPackage : packages) {
            classes.addAll(ClassUtil.getClasses(aPackage));
        }
        return classes;
    }

    private void registerClasses(Set<Class<?>> classes) {
        for (Class<?> aClass : classes) {
            List<IRegister> register = registerFactory.getRegister(aClass);
            if (!CollectionUtils.isEmpty(register)) {
                for (IRegister iRegister : register) {
                    iRegister.register(aClass);
                }
            }
        }
    }
}

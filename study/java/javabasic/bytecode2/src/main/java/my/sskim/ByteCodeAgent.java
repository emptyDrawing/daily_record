package my.sskim;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteCodeAgent {

    // premain 을 오버라이딩 한거
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
            .type(ElementMatchers.any())
            // 조작을  transform 쪽에 넣어주면 되는데...
            .transform((builder, typeDescription, classLoader, module) -> builder.method(ElementMatchers.named("pullout")).intercept(FixedValue.value("NotEmpty"))).installOn(inst);
    }
}

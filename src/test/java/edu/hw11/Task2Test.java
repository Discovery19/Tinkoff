package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForDelegation.to;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {
    @Test
    @Disabled
    @DisplayName("Task2Test sum to multiply use fix value")
    void testSumToMultiply(){
        var val1 = 3;
        var val2 = 4;
        ByteBuddyAgent.install();
        new ByteBuddy().redefine(Task2.class)
            .method(ElementMatchers.named("sum"))
            .intercept(FixedValue.value(val1 * val2))
            .make()
            .load(Task2.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent());
        int res = Task2.sum(val1, val2);
        assertEquals(12, res);
    }
    @Test
    @Disabled
    @DisplayName("Task2Test sum to sub use delegate")
    void testSumToSubDelegate(){
        var val1 = 3;
        var val2 = 4;
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(Task2.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Task2Delegate.class))  // Делегируем к самому классу Task2
            .make()
            .load(Task2.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        int result = Task2.sum(val1, val2);
        assertEquals(-1, result);
    }
}

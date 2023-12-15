package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ReflectionBenchmark {
    private Student student;
    private Method method;
    private MethodHandle handle;
    private Supplier<?> supplier;

    //CHECKSTYLE:OFF: checkstyle:MagicNumber
    //CHECKSTYLE:OFF: checkstyle:UncommentedMain
    @Setup
    public void setup() throws Throwable {
        student = new Student("Student", "StudentSurname");

        method = Student.class.getMethod("name");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        handle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class)),
            MethodType.methodType(String.class)
        );
        supplier = (Supplier<?>) site.getTarget().invokeExact(student);

    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }

    @Benchmark
    public void method(Blackhole blackhole) throws Exception {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void handler(Blackhole blackhole) throws Throwable {
        String name = (String) handle.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void factory(Blackhole blackhole) {
        String name = (String) supplier.get();
        blackhole.consume(name);

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

}

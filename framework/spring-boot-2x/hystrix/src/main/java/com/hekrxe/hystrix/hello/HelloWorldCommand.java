package com.hekrxe.hystrix.hello;

import com.netflix.hystrix.*;

import java.util.Random;

/**
 * @author tanhuayou on 2019/04/08
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    public ThreadLocal<String> local = new ThreadLocal<>();


    protected HelloWorldCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {

        int sleep = new Random().nextInt(3000);
        System.out.println("Sleep: " + sleep);
        System.out.println("Local: " + local.get());
        Thread.sleep(sleep);

        return "OK";
    }


    private static Setter genSetter() {
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("groupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("commandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPoolKey"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter() // 熔断条件
                                // 需要重载getCacheKey()，返回null时不缓存
                                .withRequestCacheEnabled(false)

                                // =====Execution
                                // 隔离策略，默认是Thread, 可选 Thread｜Semaphore
                                // Thread:
                                // 简单来说，最大的好处，就是资源隔离
                                // 调用线程和执行线程不是同一个(线程切换有开销)
                                // Semaphore:
                                // 信号量隔离的方式是限制了总的并发数，每一次请求过来，请求线程和调用依赖服务的线程是同一个线程，
                                // 那么如果不涉及远程RPC调用（没有网络开销）则使用信号量来隔离，更为轻量，开销更小
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                                // 执行是否启用超时，默认启用true
                                .withExecutionTimeoutEnabled(true)
                                // 命令执行超时时间，超过此时间，HystrixCommand被标记为TIMEOUT，并执行回退逻辑
                                .withExecutionTimeoutInMilliseconds(1000)
                                //  发生超时是否中断，默认true
                                .withExecutionIsolationThreadInterruptOnTimeout(true)
                                // 执行取消运行是否中断，默认false
                                .withExecutionIsolationThreadInterruptOnFutureCancel(false)
                                // 最大并发请求数，默认10，该参数当使用ExecutionIsolationStrategy.SEMAPHORE策略时才有效。
                                // 如果达到最大并发请求数，请求会被拒绝。理论上选择semaphore size的原则和选择thread size一致，但选用semaphore时每次执行的单元要比较小且执行速度快（ms级别），否则的话应该用thread。
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)

                                // ===== Fallback
                                // 当执行失败或者请求被拒绝，是否会尝试调用hystrixCommand.getFallback() 。默认true
                                .withFallbackEnabled(true)
                                // 如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用。默认10
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)

                                // =====Circuit Breaker
                                // 熔断开关开启
                                // 用来跟踪circuit的健康性，如果未达标则让request短路。默认true
                                .withCircuitBreakerEnabled(true)
                                // 一个rolling window内最小的请求数。如果设为20，那么当一个rolling window的时间内（比如说1个rolling window是10秒）收到19个请求，即使19个请求都失败，也不会触发circuit break。默认20
                                .withCircuitBreakerRequestVolumeThreshold(20)
                                // 熔断后sleepWindowInMilliseconds毫秒会放入一个请求，如果请求处理成功，熔断器关闭，否则熔断器打开，继续等待sleepWindowInMilliseconds
                                // 触发短路的时间值，当该值设为5000时，则当触发circuit break后的5000毫秒内都会拒绝request，也就是5000毫秒后才会关闭circuit。默认5000
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
                                // 超过 x%错误 触发熔断
                                // 错误比率阀值，如果错误率>=该值，circuit会被打开，并短路所有请求触发fallback。默认50
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                // 强制打开熔断器，如果打开这个开关，那么拒绝所有request，默认false
                                .withCircuitBreakerForceOpen(false)
                                // 强制关闭熔断器
                                .withCircuitBreakerForceClosed(false)

                                // =====Metrics
                                // 设置统计的时间窗口值的毫秒值，circuit break 的打开会根据1个 rolling window的统计来计算。
                                // 若rolling window被设为10000毫秒，则rolling window会被分成n个buckets，每个bucket包含success，failure，timeout，rejection的次数的统计信息。默认10000。
                                .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                                // 设置一个rolling window被划分的数量，若numBuckets＝10，rolling window＝10000，那么一个bucket的时间即1秒。
                                // 必须符合rolling window % numberBuckets == 0，否则会抛出异常。默认10
                                .withMetricsRollingStatisticalWindowBuckets(10)
                                // 执行时是否enable指标的计算和跟踪，默认true
                                .withMetricsRollingPercentileEnabled(true)
                                // 设置rolling percentile window的时间，默认60000
                                .withMetricsRollingPercentileWindowInMilliseconds(60000)
                                .withMetricsRollingPercentileWindowBuckets(6)
                                // 如果bucket size＝100，window＝10s，若这10s里有500次执行，只有最后100次执行会被统计到bucket里去。增加该值会增加内存开销以及排序的开销。默认100
                                .withMetricsRollingPercentileBucketSize(100)
                                // 记录Metrics（用来统计成功和错误）的间隔，默认500ms
                                .withMetricsHealthSnapshotIntervalInMilliseconds(500))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                // 核心线程池大小,默认10
                                .withCoreSize(10)
                                // 线程池最大值,默认10
                                .withMaximumSize(10)
                                // 设为－1,会使用 SynchronousQueue
                                // 为正数时使用 LinkedBlockingQueue
                                // 该设置只会在初始化时有效, 之后不能修改. 除非reinitialising thread executor。
                                // 默认－1
                                .withMaxQueueSize(-1)
                                // 即使maxQueueSize没有达到，达到queueSizeRejectionThreshold该值后，请求也会被拒绝
                                // 因为maxQueueSize不能被动态修改，这个参数将允许我们动态设置该值
                                // 默认5
                                .withQueueSizeRejectionThreshold(5)
                                // 如果corePoolSize和maxPoolSize设成一样（默认实现）该设置无效。如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间。默认1（分钟）
                                .withKeepAliveTimeMinutes(1)
                                // 该属性允许maximumSize起作用。属性值可以等于或者大于coreSize值，设置coreSize小于maximumSize的线程池能够支持maximumSize的并发数，但是会将不活跃的线程返回到系统中去。
                                .withAllowMaximumSizeToDivergeFromCoreSize(false)
                                // 设置统计的窗口的时间段大小。该属性是线程池保持指标时间的长短。默认值 10000
                                .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                                .withMetricsRollingStatisticalWindowBuckets(10));
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }

    public static void main(String[] args) {
        HelloWorldCommand cmd = new HelloWorldCommand(genSetter());
        cmd.local.set("Hello!");
        String execute = cmd.execute();
        System.out.println("execute: " + execute);
        System.out.println("Local: " + cmd.local.get());
    }
}
package com.aliware.tianchi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {
    final static PositiveAtomicCounter counter = new PositiveAtomicCounter();
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        try {
            int rr = counter.incrementAndGet() % invokers.size();
            System.out.println("Message Sending to" + invokers.get(rr));
            return invokers.get(rr);
        } catch (final Exception e) {
            return invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()));
        }
    }
}
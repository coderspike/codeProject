package rpc;

/**
 * 描述:服务Echo接口类
 * Created by bysocket on 16/2/28.
 */
/*
RPC是一个协议
RPC 的主要目的是为组件提供一种相互通信的方式
远程过程调用（RPC）是一个协议，程序可以使用这个协议请求网络中另一台计算机上某程序的服务而不需知道网络细节。
RPC使用client/server模型。请求程序是client，而服务提供程序则为server。
 */
public interface EchoService {
    String echo(String ping);
}


# Java Code Demo

## blockio

- SingleThreadedServer :单线程的服务器不是服务器的最佳设计，但是很好地说明了服务器的生命周期。
> WorkerRunnable 公共任务类,创建任务线程
- MutiThreadedServer :多线程服务器，accpet之外花费的时间比单线程少，长时间的客户端请求不会阻塞整个服务器，过多的请求，服务器开销会很大。
- ThreadPooledServer: 线程池模型，不为每个接入的请求创建线程，而是将连接包装在Runnable下传给线程池

## nio

- FastFileCopyUtils：使用nio进行快速文件拷贝

## id-generate

- SnowflakeIdWorker,SnowflakeIdTest : Twitter 雪花算法生成唯一序列号

## 字符串处理

- FieldUnderlineConvertHump：数据库下划线转DTO驼峰，一般数据库字段如demo_x1下划线分割,java程序dto使用demoX1驼峰形式

## queue

- BlockingQueueProducerConsumer：ArrayBlockingQueue实现生产者消费者

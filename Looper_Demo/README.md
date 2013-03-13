
# demo两个Looper线程，以及其Handler #

- 如何理解多线程。
- 怎么创建Looper线程。
- 怎么绑定Handler到Looper线程进行MQ的处理。
- 怎么利用Handler处理MQ（MessageQueue）,包括添加、处理Message。
- 怎么利用CountDownLatch，在本线程获取另一个线程保证已经实例化的对象

在程序开发中（尤其是GUI开发中），我们经常会需要一个线程不断循环，一旦有新任务则执行，执行完继续等待下一个任务，
这就是Looper线程。

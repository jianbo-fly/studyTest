package com.tangjianbo.concurrent;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author 18482
 */
public class TestFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new MyCallAble();
        FutureTask futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("-----------" + futureTask.get());


        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(futureTask);
        Future futureTask1 = service.submit(callable);
        try {
            Studet studet = (Studet) futureTask.get();
            System.out.println("futureTask"+studet.getAge());
            Studet studet1 = (Studet) futureTask1.get();
            System.out.println("future" + studet1.getAge() );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }


    static class MyCallAble implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("122");
            List list = Lists.newArrayList();
            Studet s = new Studet();
            s.setAge(1);
            s.setName("1");
            list.add(1);
            list.add(2);
            return s;
        }
    }

    static class MyRunable implements Runnable {
        @Override
        public void run() {

        }
    }


    static class Studet {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Studet studet = (Studet) o;
            return age == studet.age &&
                    Objects.equal(name, studet.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, age);
        }
    }
}

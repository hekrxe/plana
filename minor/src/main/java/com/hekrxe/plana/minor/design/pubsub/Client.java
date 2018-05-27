package com.hekrxe.plana.minor.design.pubsub;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new TeacherSubject();
        StudentA studentA = new StudentA();
        StudentB studentB = new StudentB();
        subject.attach(studentA);
        subject.attach(studentB);

        subject.notice();

    }
}

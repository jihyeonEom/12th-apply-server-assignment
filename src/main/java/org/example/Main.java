package org.example;

public class Main {
    public static void main(String[] args) {
        StudyRoom test = new StudyRoom();

        int num = test.work_choice();

        while(0 < num && num < 5 ) num = test.work_choice();
    }
}
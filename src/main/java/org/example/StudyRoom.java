package org.example;

import java.util.*;

public class StudyRoom {
    // 스터디룸
    char[] roomA = {'X','X','X','X','X','X','X','X','X','X','X','X','X'};
    char[] roomB = {'X','X','X','X','X','X','X','X','X','X','X','X','X'};
    char[] roomC = {'X','X','X','X','X','X','X','X','X','X','X','X','X'};

    Map<String, String> questionList = new HashMap<>(); // 문의 아이디와 문의 내용
    char bookingRoom = ' '; // 현재 예약하려는 스터디룸
    int start = 0; // 스터디룸 사용 시작 시간
    int end = 0; // 스터디룸 사용 종료 시간

    int work_choice() {
        System.out.println("----- 작업 -----\n");
        System.out.println("1. 스터디룸 예약");
        System.out.println("2. 예약 현황 조회");
        System.out.println("3. 문의 남기기");
        System.out.println("4. 문의 리스트 보기");
        System.out.println("5. 프로그램 종료");

        Scanner scan = new Scanner(System.in);
        System.out.print("\n작업을 선택하세요: ");
        int num = scan.nextInt();

        if (num == 1) { // 스터디룸 예약
            book_studyRoom();
        } else if (num == 2) { // 예약 현황 조회
            show_room_list();
        } else if (num == 3) { // 문의 남기기
            customer_question();
        } else if (num == 4) { // 문의 리스트 보기
            show_customer_question();
        } else { // 프로그램 종료
            terminate();
        }
        return num;
    }

    boolean is_room_empty(char room) {
        // 사용하려는 스터디룸이 그 시간대에 비어있는지 체크
        if(room == 'A'){
            for(int i = start-10; i < end-9; i++) {
                if(roomA[i] == 'O') return false;
            }
        } else if(room == 'B') {
            for(int i = start-10; i < end-9; i++) {
                if(roomB[i] == 'O') return false;
            }
        } else {
            for(int i = start-10; i < end-9; i++) {
                if(roomC[i] == 'O') return false;
            }
        }
        return true;
    }

    void book_studyRoom() {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n----- 스터디룸 예약 -----\n");
        System.out.print("예약할 스터디룸: ");
        bookingRoom = scan.next().charAt(0);
        System.out.print("사용 시작 시간: ");
        start = scan.nextInt();
        System.out.print("사용 종료 시간: ");
        end = scan.nextInt();

        if(bookingRoom != 'A' && bookingRoom != 'B' && bookingRoom != 'C') {
            // A,B,C 룸이 아닌 다른 룸을 예약한 경우
            System.out.printf("\n스터디룸 %c는 존재하지 않습니다.\n", bookingRoom);
            System.out.print("예약에 실패했습니다.\n");
        } else if(start < 10 || 22 < end) {
            // 사용 가능 시간을 넘긴 경우
            System.out.print("\n예약 시간이 스터디룸 운영 시간을 벗어난 시간입니다.\n");
            System.out.print("예약에 실패했습니다.\n");
        } else if(is_room_empty(bookingRoom)) {
            // 룸이 사용 가능한 경우
            if(bookingRoom == 'A') {
                for(int i=start-10; i<end-9; i++) roomA[i] = 'O';
            } else if(bookingRoom == 'B') {
                for(int i=start-10; i<end-9; i++) roomB[i] = 'O';
            } else {
                for(int i=start-10; i<end-9; i++) roomC[i] = 'O';
            }
            System.out.print("\n예약이 완료되었습니다!\n");
        } else {
            // 룸이 이미 예약된 경우
            System.out.printf("\n스터디룸 %s는 해당 시간에 이미 예약되어 있습니다.\n", bookingRoom);
            System.out.print("예약에 실패했습니다.\n");
        }
        System.out.println();
    }

    void show_room_list() {
        System.out.print("\n----- 예약 현황 -----\n");
        System.out.print("| A | B | C |\n");
        for(int i=0; i<13; i++) {
            if(i+10 < 13) {
                System.out.printf("오전 %02d시", i+10);
                System.out.printf("| %c | %c | %c |\n", roomA[i], roomB[i], roomC[i]);
            } else {
                System.out.printf("오후 %02d시", i-2);
                System.out.printf("| %c | %c | %c |\n", roomA[i], roomB[i], roomC[i]);
            }
        }
        System.out.println();
    }

    void customer_question() {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n----- 문의 남기기 -----\n");
        String userID, userInquiry;
        System.out.print("문의 아이디: ");
        userID = scan.nextLine();
        System.out.print("문의 내용: ");
        userInquiry = scan.nextLine();

        questionList.put(userID, userInquiry);

        System.out.print("\n문의가 저장되었습니다!\n");
        System.out.println();
    }

    void show_customer_question() {
        System.out.print("\n----- 문의 리스트 보기 -----\n");
        Set<String> IDs = questionList.keySet();
        for(String ID:IDs) {
            System.out.printf("문의 아이디: %s\n", ID);
            System.out.printf("문의 내용: %s\n", questionList.get(ID));
            System.out.println();
        }
    }

    void terminate() {
        System.out.print("\n프로그램을 종료합니다...\n");
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 로또 당첨 프로그램
 작성자    장재훈
 */
public class Lotto {

    public static void main(String[] args) {
        result();   //로또 입력과 출력
    }

    public static void result() {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> numbers = new ArrayList<>(); // 로또 당첨 번호를 담기 위한 배열 생성
        int buy;    // 로또 구매 수량
        char orderN = 'A'; // 맨 앞의 기호 초기값
        int[] outputN;     // 출력 번호
        int[] winningN;    //당첨 번호

        try {
            System.out.println("[로또 당첨 프로그램]\n");
            System.out.print("로또 개수를 입력해 주세요.(숫자 1 ~ 10): ");
            buy = sc.nextInt();
            if (!(buy > 0 && buy < 11)) {
                System.out.println("1~10 사이의 숫자만 입력해주세요 : ");
                return;
            }
        } catch (Exception e) {
            System.out.println("1~10 사이의 숫자만 입력해주세요.");
            return;
        }

        /* 구매한 수량만큼(입력한 숫자만큼) 루프문을 실행하며,
           로또 번호 개수를 생성하여, 배열에 추가함.
         */
        for (int i = 0; i < buy; i++) {

            //6개의 랜덤 로또 번호 생성하여 배열에 추가함.
            numbers.add(i, lottoNumberCreate());

        }

        // 생성된 로또 번호 출력
        for (int i = 0; i < numbers.size(); i++) {
            outputN = new int[6];
            for (int j = 0; j < outputN.length; j++) {
                outputN = numbers.get(i);
                if (j == 0) {
                    System.out.printf("%s\t%02d", orderN, outputN[j]);
                } else if (j < outputN.length - 1) {
                    System.out.printf(",%02d", outputN[j]);
                } else {
                    System.out.printf(",%02d\n", outputN[j]);
                }
            }
            if (i < numbers.size() - 1) {
                orderN++;
            } else {
                orderN = 'A';
                System.out.println();
            }
        }

        //로또 당첨 번호 출력
        System.out.println("[로또 발표]");
        // 당첨 번호 랜덤 생성
        winningN = lottoNumberCreate();

        for (int i = 0; i < winningN.length; i++) {
            if (i == 0) {
                System.out.printf(" \t%02d", winningN[i]);
            } else if (i < winningN.length - 1) {
                System.out.printf(",%02d", winningN[i]);
            } else {
                System.out.printf(",%02d\n\n", winningN[i]);
            }
        }

        // 당첨 번호와 구매 로또 번호를 비교하여 숫자 일치 여부 판단
        System.out.println("[내 로또 결과]");
        for (int i = 0; i < numbers.size(); i++) {
            int cnt = 0;
            outputN = numbers.get(i);

            for (int j = 0; j < outputN.length; j++) {
                for (int k = 0; k < outputN.length; k++) {
                    if (outputN[j] == winningN[k]) {
                        cnt++;
                    }
                }
                if (j == 0) {
                    System.out.printf("%s\t%02d", orderN, outputN[j]);
                } else if (j < outputN.length - 1) {
                    System.out.printf(",%02d", outputN[j]);
                } else {
                    System.out.printf(",%02d => %d개 일치\n", outputN[j], cnt);
                }
            }
            if (i < numbers.size() - 1) {
                orderN++;
            } else {
                orderN = 'A';
                System.out.println();
            }
        }
    }

    //로또 번호 자동 생성
    public static int[] lottoNumberCreate() {
        int[] inputN = new int[6];

        for (int i = 0; i < inputN.length; i++) {
            inputN[i] = (int) (Math.random() * 45) + 1;
            // 증복 제거
            for (int j = 0; j < i; j++) {
                if (inputN[i] == inputN[j]) {
                    i--;
                    break;
                }
            }
        }
        Arrays.sort(inputN);
        return inputN;
    }


}

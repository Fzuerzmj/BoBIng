package P1;
import java.util.Random;
public class Dice {   //骰子类，num表示骰子的点数，设置为
    int num;
    Dice() {
        num = -1;
    }

    int Run() {
        Random CreatNum = new Random();
        num = CreatNum.nextInt(6) + 1;
        return num;
    }

    void Run(int dice_cnt,int[] dice_num) {   //自定义骰子个数骰子，dice_num是一个一维7数组，用来记录骰子点数
        for(int i = 0; i < dice_cnt; i++) {
            dice_num[Run()]++;
        }
    }

    int GetNum() {
        return num;
    }

}
